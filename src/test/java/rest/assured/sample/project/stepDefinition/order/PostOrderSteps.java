package rest.assured.sample.project.stepDefinition.order;

import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import rest.assured.sample.project.model.order.Order;
import rest.assured.sample.project.clients.OrderClient;
import rest.assured.sample.project.context.OrderContext;
import org.springframework.beans.factory.annotation.Autowired;

import static rest.assured.sample.project.utils.ApiWaitUtils.waitUntilResourceExists;

@Feature("Post order")
public class PostOrderSteps {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private OrderContext orderContext;

    @When("I send POST request to place the order")
    public void i_send_post_request_to_place_the_order() {
        Response response = orderClient.createOrder(orderContext.getRequestOrder());
        orderContext.setResponse(response);

        Order order = response.as(Order.class);
        orderContext.setParsedResponseOrder(order);

        waitUntilResourceExists(() -> orderClient.getOrderById(order.getId()), 3000);
    }
}