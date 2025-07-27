package rest.assured.sample.project.stepDefinition.order;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.datatable.DataTable;
import rest.assured.sample.project.model.order.Order;
import rest.assured.sample.project.clients.OrderClient;
import rest.assured.sample.project.context.OrderContext;
import rest.assured.sample.project.factory.OrderFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static rest.assured.sample.project.assertions.OrderAssertions.assertOrderMatches;

public class CommonOrderSteps {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private OrderContext orderContext;

    @Given("I prepare an order with the following data")
    public void i_prepare_an_order_with_the_following_data(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        Order order = OrderFactory.buildValidOrder(data);
        orderContext.setRequestOrder(order);
    }

    @Given("I prepare an invalid order with the following data")
    public void i_prepare_a_invalid_order_with_the_following_data(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        Order order = OrderFactory.buildInvalidOrder(data);
        orderContext.setRequestOrder(order);
    }

    @Given("I use the order with ID {long}")
    @Given("I have an order with ID {long}")
    public void i_have_an_order_with_id(long orderId) {
        orderContext.setOrderId(orderId);
    }

    @Then("The order response status code should not be {int}")
    public void the_order_response_status_code_should_not_be(int expectedStatusCode) {
        assertNotEquals(expectedStatusCode, orderContext.getResponse().getStatusCode(), "Unexpected status code");
    }

    @Then("The order response should contain")
    public void the_order_response_should_contain(DataTable dataTable) {
        Order actualOrder = orderContext.getParsedResponseOrder();
        Map<String, String> expectedValues = dataTable.asMap(String.class, String.class);

        assertOrderMatches(actualOrder, expectedValues);
    }

    @Then("The order should contain fields: {string}")
    public void the_order_should_contain_fields(String commaSeparatedFields) {
        Map<String, Object> order = orderContext.getResponse().jsonPath().getMap("$");

        List<String> fields = Arrays.stream(commaSeparatedFields.split(","))
                .map(String::trim)
                .filter(field -> !field.isEmpty())
                .toList();

        for (String field : fields) {
            assertTrue(order.containsKey(field), "Missing field: " + field);
        }
    }
}