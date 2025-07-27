package rest.assured.sample.project.stepDefinition.pet;

import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import rest.assured.sample.project.model.pet.Pet;
import rest.assured.sample.project.clients.PetClient;
import rest.assured.sample.project.context.PetContext;
import org.springframework.beans.factory.annotation.Autowired;

@Feature("Get pet")
public class GetPetSteps {

    @Autowired
    private PetClient petClient;

    @Autowired
    private PetContext petContext;

    @When("I retrieve the pet by ID using a GET method")
    public void i_retrieve_the_pet_by_id_using_a_get_method() {
        long id = petContext.getPetId();
        Response response = petClient.getPetById(id);

        petContext.setResponse(response);

        if (response.getStatusCode() == 200) {
            petContext.setParsedResponsePet(response.as(Pet.class));
        }
    }
}