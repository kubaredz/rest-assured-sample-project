package rest.assured.sample.project.stepDefinition.pet;

import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import io.cucumber.java.en.Given;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import rest.assured.sample.project.model.pet.Pet;
import rest.assured.sample.project.clients.PetClient;
import rest.assured.sample.project.context.PetContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Feature("Update pet")
public class UpdatePetSteps {

    @Autowired
    private PetClient petClient;

    @Autowired
    private PetContext petContext;

    @Given("I update the pet with the following data")
    public void i_update_the_pet_with_the_following_data(DataTable dataTable) {
        Pet petToUpdate = petContext.getRequestPet();
        Map<String, String> updates = dataTable.asMap(String.class, String.class);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> petToUpdate.setName(value);
                case "status" -> petToUpdate.setStatus(value);
            }
        });

        petContext.setRequestPet(petToUpdate);
    }

    @When("I send PUT request to update the pet")
    public void i_send_put_request_to_update_the_pet() {
        Pet updatedPet = petContext.getRequestPet();
        Response response = petClient.updatePet(updatedPet);

        petContext.setResponse(response);
        petContext.setParsedResponsePet(response.as(Pet.class));
    }
}