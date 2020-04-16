package com.aman.security.constant;

public class SecurityConstant {
    public static final class ApiFailureMessage {
        public static final String BLANK_NAME = "Name cannot be blank";
        public static final String BLANK_PASSWORD = "Password cannot be blank";

        public static final String EMAIL_EXIST = "Duplicate email address. Please choose different email";
    }

    public static final class Token {
        public static final String ISSUER = "FINCITY";
    }

    public static final class SystemLogMessages {
        public static final String EMAIL_EXIST = "Account with email : {0} already exist";

    }
}
