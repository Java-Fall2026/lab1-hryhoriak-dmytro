package ua.clinic.util;

import java.util.Locale;

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

    static String formatMoney(double amount) {
        return String.format(Locale.US, "%.2f UAH", amount);
    }

    static String capitalize(String text) {
        if (text == null || text.isBlank()) return text;
        String normalized = normalizeString(text);
        return Character.toUpperCase(normalized.charAt(0)) + normalized.substring(1).toLowerCase();
    }
}