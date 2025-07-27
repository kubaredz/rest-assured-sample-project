package rest.assured.sample.project.factory;

import rest.assured.sample.project.model.pet.Pet;
import rest.assured.sample.project.model.pet.Tag;
import rest.assured.sample.project.model.pet.Category;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static rest.assured.sample.project.utils.FieldsParsingUtils.*;

public class PetFactory {

    public static Pet buildValidPet(Map<String, String> data) {
        return Pet.builder()
                .id(parseLongOrThrow(data.get("id"), "id"))
                .name(requireField(data, "name"))
                .category(buildCategory(data))
                .photoUrls(buildPhotoUrls(data))
                .tags(buildTags(data))
                .status(data.get("status"))
                .build();
    }

    public static Pet buildInvalidPet(Map<String, String> data) {
        Pet pet = new Pet();
        pet.setId(parseLongOrZero(data.get("id")));
        pet.setName(data.get("name"));
        pet.setCategory(buildCategory(data));
        pet.setPhotoUrls(buildPhotoUrls(data));
        pet.setTags(buildTags(data));
        pet.setStatus(data.get("status"));
        return pet;
    }

    private static Category buildCategory(Map<String, String> data) {
        String categoryId = data.get("categoryId");
        String categoryName = data.get("categoryName");
        if (categoryId != null && categoryName != null) {
            return new Category(parseLongOrThrow(categoryId, "categoryId"), categoryName);
        }
        return null;
    }

    private static List<String> buildPhotoUrls(Map<String, String> data) {
        String urls = data.get("photoUrls");
        return (urls != null && !urls.isBlank())
                ? Arrays.stream(urls.split(",")).map(String::trim).toList()
                : Collections.emptyList();
    }

    private static List<Tag> buildTags(Map<String, String> data) {
        String tagId = data.get("tagId");
        String tagName = data.get("tagName");
        if (tagId != null && tagName != null) {
            return List.of(new Tag(parseLongOrThrow(tagId, "tagId"), tagName));
        }
        return Collections.emptyList();
    }
}