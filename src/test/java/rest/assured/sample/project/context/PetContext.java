package rest.assured.sample.project.context;

import lombok.Getter;
import lombok.Setter;
import io.restassured.response.Response;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;
import rest.assured.sample.project.model.pet.Pet;

/**
 * Holds contextual data for Pet-related test steps within a single Cucumber scenario.
 */
@Getter
@Setter
@Component
@ScenarioScope
public class PetContext {
    private Pet requestPet;
    private Pet parsedResponsePet;
    private Response response;
    private long petId;
}