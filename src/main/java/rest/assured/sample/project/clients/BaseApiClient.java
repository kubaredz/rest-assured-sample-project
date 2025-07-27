package rest.assured.sample.project.clients;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import rest.assured.sample.project.properties.PetStoreProperties;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BaseApiClient {

    @Autowired
    protected PetStoreProperties petStoreProperties;

    static {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config = RestAssured.config()
                .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
    }

    protected RequestSpecification baseRequest() {
        return given()
                .baseUri(petStoreProperties.getBaseUrl())
                .contentType(JSON)
                .log().all();
    }
}