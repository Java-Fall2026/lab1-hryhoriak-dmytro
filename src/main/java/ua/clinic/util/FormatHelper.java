package ua.clinic.util;

class FormatHelper {
    private FormatHelper() {}

    static String normalizeString(String input) {
        if (input == null) return null;
        return input.trim().replaceAll("\\s+", " ");
    }

    static String normalizeEnum(String input) {
        if (input == null) return null;
        return input.trim().toUpperCase();
    }
}