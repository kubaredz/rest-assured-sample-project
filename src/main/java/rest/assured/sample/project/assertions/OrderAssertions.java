package rest.assured.sample.project.assertions;

import rest.assured.sample.project.model.order.Order;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static rest.assured.sample.project.utils.DateUtils.assertIsoDatesEqual;

public class OrderAssertions {

    public static void assertOrderMatches(Order actualOrder, Map<String, String> expectedValues) {
        assertNotNull(actualOrder, "Order object is null");

        expectedValues.forEach((key, expectedValue) -> {
            String actual = switch (key) {
                case "id" -> String.valueOf(actualOrder.getId());
                case "petId" -> String.valueOf(actualOrder.getPetId());
                case "quantity" -> String.valueOf(actualOrder.getQuantity());
                case "shipDate" -> actualOrder.getShipDate();
                case "status" -> actualOrder.getStatus();
                case "complete" -> String.valueOf(actualOrder.isComplete());
                default -> throw new IllegalArgumentException("Unsupported field: " + key);
            };

            if ("shipDate".equals(key)) {
                assertIsoDatesEqual(expectedValue, actual, key);
            } else {
                assertEquals(expectedValue, actual,
                        String.format("Field '%s' mismatch: expected='%s', actual='%s'", key, expectedValue, actual));
            }
        });
    }
}


