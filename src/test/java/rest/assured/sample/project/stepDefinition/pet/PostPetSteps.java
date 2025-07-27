package rest.assured.sample.project.stepDefinition.pet;

import io.cucumber.java.en.*;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import rest.assured.sample.project.model.pet.Pet;
import rest.assured.sample.project.clients.PetClient;
import rest.assured.sample.project.context.PetContext;
import org.springframework.beans.factory.annotation.Autowired;

import static rest.assured.sample.project.utils.ApiWaitUtils.waitUntilResourceExists;

@Feature("Post pet")
public class PostPetSteps {

    @Autowired
    private PetClient petClient;

    @Autowired
    private PetContext petContext;

    @When("I send POST request to create the pet")
    public void i_send_post_request_to_create_the_pet() {
        Pet pet = petContext.getRequestPet();
        Response response = petClient.createPet(pet);

        petContext.setResponse(response);
        petContext.setParsedResponsePet(response.as(Pet.class));

        waitUntilResourceExists(() -> petClient.getPetById(pet.getId()), 3000);
    }
}