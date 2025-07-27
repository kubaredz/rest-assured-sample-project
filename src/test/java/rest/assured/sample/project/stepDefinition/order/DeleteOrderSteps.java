package rest.assured.sample.project.stepDefinition.order;

import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import rest.assured.sample.project.clients.OrderClient;
import rest.assured.sample.project.context.OrderContext;
import org.springframework.beans.factory.annotation.Autowired;

import static rest.assured.sample.project.utils.ApiWaitUtils.waitUntilResourceDeleted;

@Feature("Delete order")
public class DeleteOrderSteps {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private OrderContext orderContext;

    @When("I delete the order by ID")
    public void i_delete_the_order_by_id() {
        long id = orderContext.getOrderId();

        Response response = orderClient.deleteOrderById(id);
        orderContext.setResponse(response);

        waitUntilResourceDeleted(() -> orderClient.getOrderById(id), 3000);
    }
}