package rest.assured.sample.project.assertions;

import rest.assured.sample.project.model.pet.Pet;

import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PetAssertions {

    public static void assertPetMatches(Pet actualPet, Map<String, String> expectedValues) {
        assertNotNull(actualPet, "Pet object is null");

        expectedValues.forEach((key, expectedValue) -> {
            String actual = switch (key) {
                case "id" -> String.valueOf(actualPet.getId());
                case "name" -> actualPet.getName();
                case "status" -> actualPet.getStatus();

                case "categoryId" -> actualPet.getCategory() != null
                        ? String.valueOf(actualPet.getCategory().getId()) : null;

                case "categoryName" -> actualPet.getCategory() != null
                        ? actualPet.getCategory().getName() : null;

                case "photoUrls" -> {
                    List<String> urls = actualPet.getPhotoUrls();
                    yield (urls != null && !urls.isEmpty()) ? String.join(", ", urls) : null;
                }

                case "tagId" -> {
                    var tags = actualPet.getTags();
                    yield (tags != null && !tags.isEmpty() && tags.get(0) != null)
                            ? String.valueOf(tags.get(0).getId()) : null;
                }

                case "tagName" -> {
                    var tags = actualPet.getTags();
                    yield (tags != null && !tags.isEmpty() && tags.get(0) != null)
                            ? tags.get(0).getName() : null;
                }

                default -> throw new IllegalArgumentException("Unsupported field: " + key);
            };

            assertEquals(expectedValue, actual,
                    String.format("Field '%s' mismatch: expected='%s', actual='%s'", key, expectedValue, actual));
        });
    }
}