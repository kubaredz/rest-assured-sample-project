package rest.assured.sample.project.stepDefinition.pet;

import io.cucumber.java.en.When;
import io.qameta.allure.Feature;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import rest.assured.sample.project.clients.PetClient;
import rest.assured.sample.project.context.PetContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

@Feature("Post pet image")
public class PostPetImageSteps {

    @Autowired
    private PetClient petClient;

    @Autowired
    private PetContext petContext;

    private File imageFile;

    @Given("I prepare image from path {string} for a pet with ID {long}")
    public void i_prepare_image_from_path_for_a_pet_with_id(String path, long id) {
        imageFile = new File(path);
        petContext.setPetId(id);

        if (!imageFile.exists()) {
            throw new IllegalArgumentException("Image not found at path: " + path);
        }
    }

    @When("I upload the image for that pet")
    public void i_upload_the_image_for_that_pet() {
        if (imageFile == null || petContext.getPetId() == 0) {
            throw new IllegalStateException("Image file or pet ID not initialized. Did you forget to call the Given step?");
        }

        Response response = petClient.postPetImage(petContext.getPetId(), imageFile);
        petContext.setResponse(response);
    }
}