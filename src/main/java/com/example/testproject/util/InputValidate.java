package com.example.testproject.util;

import java.util.regex.Pattern;

public class InputValidate {

    // only for russian phone numbers
    public static boolean validatePhoneNumber(String phoneNumber) {
        String phoneRegex = "(\\+7|8)\\s?\\(\\d{3}\\)\\s\\d{3}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|ru)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static boolean validateName(String name) {
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(name).matches();
    }

    public static boolean validateId(Long id) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(String.valueOf(id).trim()).matches();
    }

    public static boolean validateContactType(String contactType) {
        String regex = "^(EMAIL|PHONE)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(contactType).matches();
    }
}
