package rest.assured.sample.project.utils;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtils {

    /**
     * Compares two ISO 8601 date strings, ignoring differences in time zone format,
     * such as "Z" vs "+0000".
     *
     * @param expected  ISO 8601 expected date string (e.g., "2025-07-24T12:42:44.528Z")
     * @param actual    ISO 8601 actual date string (e.g., "2025-07-24T12:42:44.528+0000")
     * @param fieldName Name of the field being compared (used in assertion message)
     */
    public static void assertIsoDatesEqual(String expected, String actual, String fieldName) {
        if (expected == null || actual == null) {
            throw new IllegalArgumentException("Date values must not be null");
        }

        Instant expectedInstant = Instant.parse(normalizeIsoDate(expected));
        Instant actualInstant = Instant.parse(normalizeIsoDate(actual));

        assertEquals(expectedInstant, actualInstant,
                String.format("Expected %s='%s' but was '%s'", fieldName, expected, actual));
    }

    /**
     * Normalizes ISO date format by converting "+0000" to "Z"
     * so it can be parsed correctly by Instant.
     *
     * @param date ISO 8601 date string
     * @return normalized date string
     */
    private static String normalizeIsoDate(String date) {
        if (date.endsWith("+0000")) {
            return date.replace("+0000", "Z");
        }
        return date;
    }
}