package rest.assured.sample.project.stepDefinition.order;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.qameta.allure.Feature;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import rest.assured.sample.project.clients.OrderClient;
import rest.assured.sample.project.context.OrderContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Get order")
public class GetOrderSteps {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private OrderContext orderContext;

    @When("I send a GET request to retrieve the order by ID {long}")
    public void i_send_a_get_request_to_retrieve_the_order_by_id(long id) {
        Response response = orderClient.getOrderById(id);
        orderContext.setResponse(response);
    }

    @When("I send a GET request to fetch inventory by status")
    public void i_send_a_get_request_to_fetch_inventory_by_status() {
        Response response = orderClient.getInventoryByStatus();
        orderContext.setResponse(response);
    }

    @Then("The response should contain the order details with ID {long}")
    public void the_response_should_contain_the_order_details_with_id(long expectedId) {
        int actualId = orderContext.getResponse().jsonPath().getInt("id");
        assertEquals(expectedId, actualId, "Returned orderId does not match expected ID");
    }

    @Then("The response should contain inventory counts for each status")
    public void the_response_should_contain_inventory_counts_for_each_status() {
        Map<String, Object> inventory = orderContext.getResponse().jsonPath().getMap("$");

        Assertions.assertNotNull(inventory, "Inventory response is null");
        Assertions.assertFalse(inventory.isEmpty(), "Inventory is empty");

        Assertions.assertTrue(
                inventory.containsKey("sold") || inventory.containsKey("available") ||
                        inventory.containsKey("pending"), "Expected status keys not found in inventory");
    }

    @Then("The response should contain the following status keys")
    public void the_response_should_contain_the_following_status_keys(DataTable dataTable) {
        Map<String, Object> inventory = orderContext.getResponse().jsonPath().getMap("$");

        List<String> expectedKeys = dataTable.asList();

        for (String key : expectedKeys) {
            Assertions.assertTrue(inventory.containsKey(key),
                    "Expected status key not found: " + key);
        }
    }
}