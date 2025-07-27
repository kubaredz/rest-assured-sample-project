package rest.assured.sample.project.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import rest.assured.sample.project.clients.PetClient;
import rest.assured.sample.project.context.PetContext;
import rest.assured.sample.project.clients.OrderClient;
import rest.assured.sample.project.context.OrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import rest.assured.sample.project.model.SystemMessageResponse;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommonSteps {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private OrderContext orderContext;

    @Autowired
    private PetClient petClient;

    @Autowired
    private PetContext petContext;

    /**
     * Validates that the response from the given context contains the expected system message fields.
     */
    @Then("The {string} response should contain the following system message")
    public void the_response_should_contain_the_following_system_message(String contextName, DataTable dataTable) {
        Map<String, String> expected = dataTable.asMap(String.class, String.class);

        if (!expected.containsKey("code") || !expected.containsKey("type") || !expected.containsKey("message")) {
            throw new IllegalArgumentException("DataTable must contain keys: code, type, message");
        }

        Response response = getResponseByContext(contextName);
        SystemMessageResponse actual = response.as(SystemMessageResponse.class);

        assertEquals(Integer.parseInt(expected.get("code")), actual.getCode(), "Unexpected code");
        assertEquals(expected.get("type"), actual.getType(), "Unexpected type");
        assertEquals(expected.get("message"), actual.getMessage(), "Unexpected message");
    }

    /**
     * Verifies the HTTP status code for the given context response.
     */
    @Then("The {string} response status code should be {int}")
    public void the_response_status_code_should_be(String contextName, int expectedStatusCode) {
        Response response = getResponseByContext(contextName);
        assertEquals(expectedStatusCode, response.getStatusCode(), "Unexpected status code");
    }

    /**
     * Verifies that the response contains the expected error message under "message" key.
     */
    @Then("The {string} response should contain error message {string}")
    public void the_response_should_contain_error_message(String contextName, String expectedMessage) {
        Response response = getResponseByContext(contextName);
        String actualMessage = response.jsonPath().getString("message");

        assertNotNull(actualMessage, "Message field not found in response");
        assertEquals(expectedMessage, actualMessage, "Unexpected error message in response");
    }

    /**
     * Retrieves the proper Response object based on the context name provided.
     */
    private Response getResponseByContext(String contextName) {
        return switch (contextName.trim().toLowerCase()) {
            case "order" -> orderContext.getResponse();
            case "pet" -> petContext.getResponse();
            default -> throw new IllegalArgumentException("Unsupported context: " + contextName);
        };
    }
}