package rest.assured.sample.project.stepDefinition.pet;

import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import rest.assured.sample.project.clients.PetClient;
import rest.assured.sample.project.context.PetContext;
import org.springframework.beans.factory.annotation.Autowired;

import static rest.assured.sample.project.utils.ApiWaitUtils.waitUntilResourceDeleted;

@Feature("Delete pet")
public class DeletePetSteps {

    @Autowired
    private PetClient petClient;

    @Autowired
    private PetContext petContext;

    @When("I delete the pet by ID")
    public void i_delete_the_pet_by_id() {
        long id = petContext.getPetId();
        Response response = petClient.deletePetById(id);

        petContext.setResponse(response);

        waitUntilResourceDeleted(() -> petClient.getPetById(id), 3000);
    }
}