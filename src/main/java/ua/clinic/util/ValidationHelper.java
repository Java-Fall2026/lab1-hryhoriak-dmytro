package ua.clinic.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

class ValidationHelper {
    private ValidationHelper() {}

    static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank, got: " + value);
        }
    }

    static void requireNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
    }

    static void requireNotFutureDate(LocalDate date, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(fieldName + " cannot be in the future, got: " + date);
        }
    }

    static void requireValidTimeRange(LocalTime startTime, LocalTime endTime) {
        requireNotNull(startTime, "startTime");
        requireNotNull(endTime, "endTime");
        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("endTime must be strictly after startTime. Start: " + startTime + ", End: " + endTime);
        }
    }

    static void requirePositiveOrZero(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " must be >= 0, got: " + value);
        }
    }

    static void requireValidEnum(String value, Set<String> allowedValues, String fieldName) {
        requireNotBlank(value, fieldName);
        if (!allowedValues.contains(value.toUpperCase())) {
            throw new IllegalArgumentException(fieldName + " must be one of " + allowedValues + ", got: " + value);
        }
    }
}