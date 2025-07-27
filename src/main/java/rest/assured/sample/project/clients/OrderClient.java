package rest.assured.sample.project.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;
import rest.assured.sample.project.model.order.Order;

@Component
public class OrderClient extends BaseApiClient {

    @Step("Create order with ID {order.id}")
    public Response createOrder(Order order) {
        return baseRequest()
                .body(order)
                .when()
                .post("/store/order")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Get inventory by status")
    public Response getInventoryByStatus() {
        return baseRequest()
                .when()
                .get("/store/inventory")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Get order by ID {id}")
    public Response getOrderById(long id) {
        return baseRequest()
                .pathParam("id", id)
                .when()
                .get("/store/order/{id}")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Delete order by ID {id}")
    public Response deleteOrderById(long id) {
        return baseRequest()
                .pathParam("id", id)
                .when()
                .delete("/store/order/{id}")
                .then()
                .log().all()
                .extract().response();
    }
}