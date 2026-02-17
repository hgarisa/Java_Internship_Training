package com.hrudhay.kyc_api.customer.service;

import com.hrudhay.kyc_api.audit.service.AuditService;

import com.hrudhay.kyc_api.common.exception.DuplicateEmailException;
import com.hrudhay.kyc_api.common.exception.ResourceNotFoundException;
import com.hrudhay.kyc_api.customer.dto.*;
import com.hrudhay.kyc_api.customer.entity.Customer;
import com.hrudhay.kyc_api.customer.entity.CustomerFetchAudit;
import com.hrudhay.kyc_api.customer.repo.CustomerFetchAuditRepository;
import com.hrudhay.kyc_api.customer.repo.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.List;
import java.util.UUID;
import com.hrudhay.kyc_api.customer.dto.CustomerPrecheckRequest;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    @Autowired
    private  CustomerFetchAuditRepository fetchAuditRepo; // stays in primary DB (kyc_db)
    @Autowired
    private  AuditService auditService; // writes to audit DB (kyc_audit_db)



    public Customer createCustomer(CustomerCreateRequest request) {
        try {
            if (repo.existsByEmail(request.email())) {
                throw new DuplicateEmailException("Email already exists: " + request.email());
            }
            if (repo.existsByIdNumber(request.idNumber())) {
                throw new RuntimeException("ID Number already exists: " + request.idNumber());
            }

            Customer customer = new Customer();
            customer.setIdNumber(request.idNumber());
            customer.setPhoneNumber(toE164ZA(request.phoneNumber()));
            customer.setFirstName(request.firstName());
            customer.setLastName(request.lastName());
            customer.setEmail(request.email());

            Customer saved = repo.save(customer);

            auditService.logChange(
                    "CREATE",
                    saved.getIdNumber(),
                    null,
                    saved,
                    "SUCCESS",
                    "Customer created successfully"
            );

            return saved;

        } catch (RuntimeException ex) {
            try {
                auditService.logChange("CREATE", request.idNumber(), null, request, "FAILED", ex.getMessage());
            } catch (Exception ignored) {}
            throw ex;
        }
    }

    public List<Customer> getAll() {
        try {
            if (!isAdmin()) {
                throw new ResourceNotFoundException("Customer not found");
            }

            List<Customer> customers = repo.findAll();

            auditService.logRead("GET_ALL", null, customers.size(), "SUCCESS", "Fetched all customers");
            return customers;

        } catch (RuntimeException ex) {
            try { auditService.logRead("GET_ALL", null, null, "FAILED", ex.getMessage()); } catch (Exception ignored) {}
            throw ex;
        }
    }



    public Customer getById(Long id) {
        try {
            Customer customer = repo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            enforceOwnerOrAdmin(customer);

            auditService.logRead("GET_BY_ID", customer.getIdNumber(), 1, "SUCCESS", "Fetched customer by id");
            return customer;

        } catch (RuntimeException ex) {
            try { auditService.logRead("GET_BY_ID", null, 0, "FAILED", ex.getMessage()); } catch (Exception ignored) {}
            throw ex;
        }
    }




    public Customer getByEmail(String email) {
        try {
            if (!isAdmin()) {
                String tokenEmail = getTokenEmail();
                if (tokenEmail == null || !tokenEmail.equalsIgnoreCase(email)) {
                    throw new ResourceNotFoundException("Customer not found");
                }
            }

            Customer customer = repo.findByEmailIgnoreCase(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            enforceOwnerOrAdmin(customer);

            auditService.logRead("GET_BY_EMAIL", customer.getIdNumber(), 1, "SUCCESS", "Fetched customer by email");
            return customer;

        } catch (RuntimeException ex) {
            try { auditService.logRead("GET_BY_EMAIL", null, 0, "FAILED", ex.getMessage()); } catch (Exception ignored) {}
            throw ex;
        }
    }



    public Customer getByIdNumber(String idNumber) {
        try {
            if (!isAdmin()) {
                throw new ResourceNotFoundException("Customer not found");
            }

            Customer customer = repo.findByIdNumber(idNumber)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            auditService.logRead("GET_BY_ID_NUMBER", customer.getIdNumber(), 1, "SUCCESS", "Fetched customer by idNumber");
            return customer;

        } catch (RuntimeException ex) {
            try { auditService.logRead("GET_BY_ID_NUMBER", idNumber, 0, "FAILED", ex.getMessage()); } catch (Exception ignored) {}
            throw ex;
        }
    }



    public Customer update(Long id, CustomerUpdateRequest request) {
        try {
            Customer existing = repo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            enforceOwnerOrAdmin(existing);

            Customer oldCopy = new Customer();
            oldCopy.setId(existing.getId());
            oldCopy.setIdNumber(existing.getIdNumber());
            oldCopy.setPhoneNumber(existing.getPhoneNumber());
            oldCopy.setFirstName(existing.getFirstName());
            oldCopy.setLastName(existing.getLastName());
            oldCopy.setEmail(existing.getEmail());
            oldCopy.setCreatedAt(existing.getCreatedAt());

            existing.setIdNumber(request.idNumber());
            existing.setPhoneNumber(toE164ZA(request.phoneNumber()));
            existing.setFirstName(request.firstName());
            existing.setLastName(request.lastName());
            existing.setEmail(request.email());

            Customer saved = repo.save(existing);

            auditService.logChange("UPDATE", saved.getIdNumber(), oldCopy, saved, "SUCCESS", "Customer updated successfully");
            return saved;

        } catch (RuntimeException ex) {
            try { auditService.logChange("UPDATE", request.idNumber(), null, request, "FAILED", ex.getMessage()); } catch (Exception ignored) {}
            throw ex;
        }
    }



    public void deleteById(Long id) {
        try {
            Customer existing = repo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

            enforceOwnerOrAdmin(existing);

            auditService.logChange("DELETE", existing.getIdNumber(), existing, null, "SUCCESS", "Customer deleted successfully");
            repo.delete(existing);

        } catch (RuntimeException ex) {
            try { auditService.logChange("DELETE", null, null, null, "FAILED", ex.getMessage()); } catch (Exception ignored) {}
            throw ex;
        }
    }

    // Your existing /search audit (customer_fetch_audit) stays in kyc_db
    public Customer getByFirstLastEmail(String firstName, String lastName, String email) {
        Customer customer = repo.findByFirstNameAndLastNameAndEmail(firstName, lastName, email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        String desc = "record was fetched successfully with email " + customer.getEmail()
                + " firstName : " + customer.getFirstName()
                + " and lastName " + customer.getLastName() + ".";

        CustomerFetchAudit audit = new CustomerFetchAudit(UUID.randomUUID().toString(), desc);
        fetchAuditRepo.save(audit);

        return customer;
    }

    public Customer verifyCustomer(@Valid CustomerVerifyRequest request) {
        try {
            Customer customer = repo.findByIdNumberIgnoreCaseAndPhoneNumber(
                            request.idNumber(),
                            request.phoneNumber()
                    )
                    .orElseThrow(() -> new ResourceNotFoundException("Invalid ID Number or Phone Number"));

            String tokenEmail = getTokenEmail(); // from JWT
            if (tokenEmail == null || !tokenEmail.equalsIgnoreCase(customer.getEmail())) {
                throw new ResourceNotFoundException("Invalid ID Number or Phone Number");
                // (intentionally generic to avoid leaking info)
            }

            auditService.logRead(
                    "VERIFY",
                    customer.getIdNumber(),
                    1,
                    "SUCCESS",
                    "Customer verified successfully"
            );

            return customer;

        } catch (RuntimeException ex) {
            try {
                auditService.logRead("VERIFY", request.idNumber(), 0, "FAILED", ex.getMessage());
            } catch (Exception ignored) {}
            throw ex;
        }
    }

    private String getTokenEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();

            String email = jwt.getClaimAsString("email");
            if (email == null || email.isBlank()) email = jwt.getClaimAsString("preferred_username");
            if (email == null || email.isBlank()) email = jwt.getClaimAsString("username");
            if (email == null || email.isBlank()) email = jwt.getSubject(); // sub

            return (email != null && !email.isBlank()) ? email : null;
        }
        return null;
    }




    private boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_admin"));
    }


    private void enforceOwnerOrAdmin(Customer customer) {
        if (isAdmin()) return;

        String tokenEmail = getTokenEmail();
        if (tokenEmail == null || !tokenEmail.equalsIgnoreCase(customer.getEmail())) {
            // Generic not found avoids leaking existence
            throw new ResourceNotFoundException("Customer not found");
        }
    }


    @Autowired private OtpRedisService otpRedisService;

    private String normalizePhone(String phone) {
        if (phone == null) return "";
        String p = phone.trim().replaceAll("\\s+", "");

        if (p.startsWith("+")) return p;

        // 0847416260 -> +27847416260
        if (p.startsWith("0")) p = p.substring(1);

        return "+27" + p;
    }


    private String toE164ZA(String phone) {
        if (phone == null) return null;

        String p = phone.trim().replaceAll("\\s+", "");
        if (p.startsWith("+")) return p;

        // example: 0847416260 -> +27847416260
        if (p.startsWith("0")) p = p.substring(1);
        return "+27" + p;
    }


    public Customer findByEmail(String email) {
        return repo.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }


    public void precheckIdNumber(CustomerPrecheckRequest request) {

        String tokenEmail = getTokenEmail();

        Customer customer = repo.findByIdNumberIgnoreCase(request.idNumber().trim())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID Number or Phone Number"));

        // must belong to logged-in user
        if (tokenEmail == null || !tokenEmail.equalsIgnoreCase(customer.getEmail())) {
            throw new ResourceNotFoundException("Invalid ID Number or Phone Number");
        }

        // phone must match (normalize both)
        String inputPhone = normalizePhone(request.phoneNumber());
        String dbPhone = normalizePhone(customer.getPhoneNumber());

        if (!dbPhone.equals(inputPhone)) {
            throw new ResourceNotFoundException("Invalid ID Number or Phone Number");
        }
    }



    public void precheckIdNumberOwnership(String idNumber) {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof JwtAuthenticationToken jwtAuth)) {
            throw new RuntimeException("Unauthorized");
        }

        boolean isAdmin = jwtAuth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_admin"));

        if (isAdmin) return; // admin can proceed

        String email = jwtAuth.getToken().getClaimAsString("email");
        if (email == null) email = jwtAuth.getToken().getClaimAsString("preferred_username");
        if (email == null) throw new RuntimeException("Email not found in token");

        Customer c = repo.findByIdNumber(idNumber)
                .orElseThrow(() -> new RuntimeException("ID Number not found"));

        if (!c.getEmail().equalsIgnoreCase(email)) {
            throw new RuntimeException("ID Number does not belong to this user");
        }
    }

    public Customer getMyCustomer(Jwt jwt) {
        String tokenEmail = getTokenEmail();
        if (tokenEmail == null) throw new ResourceNotFoundException("Customer not found");

        // customer_user can only fetch their own record
        return repo.findByEmailIgnoreCase(tokenEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }




}

