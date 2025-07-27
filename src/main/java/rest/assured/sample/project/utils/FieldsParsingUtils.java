package rest.assured.sample.project.utils;

import java.util.Map;
import java.util.Optional;

/**
 * Utility class for safely parsing and validating fields from input data (e.g., Cucumber DataTables).
 */
public class FieldsParsingUtils {

    /**
     * Attempts to parse a string value as a long.
     * Throws an IllegalArgumentException if parsing fails or the value is null/empty.
     *
     * @param value     the string to parse
     * @param fieldName the name of the field (used in exception messages)
     * @return parsed long value
     */
    public static long parseLongOrThrow(String value, String fieldName) {
        if (isBlank(value)) {
            throw new IllegalArgumentException("Missing or invalid field: " + fieldName);
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid long value for field '" + fieldName + "': " + value);
        }
    }

    /**
     * Parses a string as a long or returns 0 if the value is null, empty, or invalid.
     *
     * @param value the string to parse
     * @return parsed long value or 0 if parsing fails
     */
    public static long parseLongOrZero(String value) {
        try {
            return Optional.ofNullable(value)
                    .map(String::trim)
                    .map(Long::parseLong)
                    .orElse(0L);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    /**
     * Parses a string as an int or returns 0 if the value is null, empty, or invalid.
     *
     * @param value the string to parse
     * @return parsed int value or 0 if parsing fails
     */
    public static int parseIntOrZero(String value) {
        try {
            return Optional.ofNullable(value)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .orElse(0);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Parses a string as a boolean. Returns false if value is null or not a valid boolean string.
     * Accepts: "true", "false" (case-insensitive).
     *
     * @param value the string to parse
     * @return boolean result or false if input is null/invalid
     */
    public static boolean parseBooleanOrFalse(String value) {
        return Optional.ofNullable(value)
                .map(String::trim)
                .map(Boolean::parseBoolean)
                .orElse(false);
    }

    /**
     * Validates that a required field exists in the data map and is not null/empty.
     * Throws an exception if the field is missing or empty.
     *
     * @param data      the input map
     * @param fieldName the required field name
     * @return the value of the required field
     */
    public static String requireField(Map<String, String> data, String fieldName) {
        String value = data.get(fieldName);
        if (isBlank(value)) {
            throw new IllegalArgumentException("Missing required field: " + fieldName);
        }
        return value;
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}