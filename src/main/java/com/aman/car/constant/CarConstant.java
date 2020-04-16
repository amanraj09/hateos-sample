package com.aman.car.constant;

public class CarConstant {
    public static class ApiFailureMessage {
        public static final String BLANK_CAR_NAME = "Car name cannot be blank";
        public static final String BLANK_MANUFACTURER_NAME = "Manufacturer name cannot be blank";
        public static final String BLANK_MANUFACTURE_YEAR = "Manufacture year cannot be blank";
        public static final String BLANK_CAR_MODEL = "Car model cannot be blank";
        public static final String BLANK_CAR_COLOR = "Car color cannot be blank";

        public static final String CAR_NOT_FOUND = "Car does not exist";
    }

    public static class ApiDefaultConstants {
        public static final Integer DEFAULT_LIMIT = 10;
        public static final Integer DEFAULT_PAGE = 0;

    }

    public static final class SystemLogMessages {
        public static final String CAR_NOT_FOUND = "Car with id : {0} could not be found";
    }
}
