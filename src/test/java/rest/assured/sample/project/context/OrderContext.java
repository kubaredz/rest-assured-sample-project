package rest.assured.sample.project.context;

import lombok.Getter;
import lombok.Setter;
import io.restassured.response.Response;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;
import rest.assured.sample.project.model.order.Order;

/**
 * Holds contextual data for Order-related test steps in a single Cucumber scenario.
 */
@Getter
@Setter
@Component
@ScenarioScope
public class OrderContext {

    private Order requestOrder;
    private Order parsedResponseOrder;
    private Response response;
    private long orderId;
}