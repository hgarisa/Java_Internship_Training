package com.hrudhay.kyc_api.customer.repo;

import com.hrudhay.kyc_api.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    boolean existsByEmail(String email);


    Optional<Customer> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);

    Optional<Customer> findByEmailIgnoreCase(String email);

    boolean existsByIdNumber(String idNumber);
    Optional<Customer> findByIdNumber(String idNumber);

    Optional<Customer> findByIdNumberIgnoreCase(String idNumber);
    Optional<Customer> findByIdNumberIgnoreCaseAndPhoneNumber(String idNumber, String phoneNumber);

    Optional<Customer> findByEmail(String email);

}
