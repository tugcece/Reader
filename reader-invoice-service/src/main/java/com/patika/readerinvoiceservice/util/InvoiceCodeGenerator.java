package com.patika.readerinvoiceservice.util;

import java.security.SecureRandom;

public class InvoiceCodeGenerator {
    // alphabet
    private static final String CHARACTERS = "0123456789";
    private static final int CODE_LENGTH = 10;
    //Provide random numbers with secure number...
    private static final SecureRandom RANDOM = new SecureRandom();
    // Random numbers and alphabet characters combined then return code...
    public static String generateOrderCode() {
        StringBuilder orderCode = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            orderCode.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return orderCode.toString();
    }
}
