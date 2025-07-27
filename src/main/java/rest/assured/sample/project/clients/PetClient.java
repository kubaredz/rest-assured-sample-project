package rest.assured.sample.project.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

import rest.assured.sample.project.model.pet.Pet;

import java.io.File;

@Component
public class PetClient extends BaseApiClient {

    @Step("Create pet with ID {pet.id}")
    public Response createPet(Pet pet) {
        return baseRequest()
                .accept("application/json")
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Get pet by ID {id}")
    public Response getPetById(long id) {
        return baseRequest()
                .pathParam("id", id)
                .when()
                .get("/pet/{id}")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Update pet with ID {pet.id}")
    public Response updatePet(Pet pet) {
        return baseRequest()
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Delete pet by ID {id}")
    public Response deletePetById(long id) {
        return baseRequest()
                .pathParam("id", id)
                .when()
                .delete("/pet/{id}")
                .then()
                .log().all()
                .extract().response();

    }

    @Step("Upload image for pet with ID {id}")
    public Response postPetImage(long id, File file) {
        return baseRequest()
                .accept("application/json")
                .contentType("multipart/form-data")
                .multiPart("additionalMetadata", "asdasda")
                .multiPart("file", file, "image/png")
                .pathParam("id", id)
                .when()
                .post("/pet/{id}/uploadImage")
                .then()
                .log().all()
                .extract().response();
    }
}