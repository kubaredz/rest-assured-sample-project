package rest.assured.sample.project.stepDefinition.pet;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.datatable.DataTable;
import rest.assured.sample.project.model.pet.Pet;
import rest.assured.sample.project.clients.PetClient;
import rest.assured.sample.project.context.PetContext;
import rest.assured.sample.project.factory.PetFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rest.assured.sample.project.assertions.PetAssertions.assertPetMatches;

public class CommonPetSteps {

    @Autowired
    private PetClient petClient;

    @Autowired
    private PetContext petContext;

    @Given("I prepare a pet with the following data")
    public void i_prepare_a_pet_with_the_following_data(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        Pet pet = PetFactory.buildValidPet(data);
        petContext.setRequestPet(pet);
    }

    @Given("I prepare an invalid pet with the following data")
    public void i_prepare_an_invalid_pet_with_the_following_data(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        Pet pet = PetFactory.buildInvalidPet(data);
        petContext.setRequestPet(pet);
    }

    @Given("I use the pet with ID {long}")
    public void i_use_the_pet_with_id(long petId) {
        petContext.setPetId(petId);
    }

    @Then("The pet response should contain")
    public void the_pet_response_should_contain(DataTable dataTable) {
        Pet actualPet = petContext.getParsedResponsePet();
        Map<String, String> expectedValues = dataTable.asMap(String.class, String.class);

        assertPetMatches(actualPet, expectedValues);
    }
}