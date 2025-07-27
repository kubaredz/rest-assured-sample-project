package rest.assured.sample.project.factory;

import rest.assured.sample.project.model.order.Order;

import java.util.Map;

import static rest.assured.sample.project.utils.FieldsParsingUtils.*;

public class OrderFactory {

    public static Order buildValidOrder(Map<String, String> data) {
        return Order.builder()
                .id(parseLongOrThrow(data.get("id"), "id"))
                .petId(parseLongOrThrow(data.get("petId"), "petId"))
                .quantity(parseIntOrZero(data.get("quantity")))
                .shipDate(requireField(data, "shipDate"))
                .status(data.get("status"))
                .complete(parseBooleanOrFalse(data.get("complete")))
                .build();
    }

    public static Order buildInvalidOrder(Map<String, String> data) {
        Order order = new Order();
        order.setId(parseLongOrZero(data.get("id")));
        order.setPetId(parseLongOrZero(data.get("petId")));
        order.setQuantity(parseIntOrZero(data.get("quantity")));
        order.setShipDate(data.get("shipDate"));
        order.setStatus(data.get("status"));
        order.setComplete(parseBooleanOrFalse(data.get("complete")));
        return order;
    }
}