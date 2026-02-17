package com.hrudhay.kyc_api.customer.util;

public class PhoneUtil {

    // very simple normalizer for SA-style numbers
    public static String normalize(String phone) {
        if (phone == null) return null;

        String p = phone.trim().replaceAll("\\s+", "");

        // if starts with 0 (e.g. 0847...), convert to +27...
        if (p.startsWith("0")) {
            p = "+27" + p.substring(1);
        }

        // if starts with 27 (no plus), convert to +27...
        if (p.startsWith("27") && !p.startsWith("+")) {
            p = "+" + p;
        }

        return p;
    }
}
