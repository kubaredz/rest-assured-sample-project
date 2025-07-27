@regression @post @pet @imageUpload
Feature: Upload Image in the PetStore API - POST by Pet ID image

  As a Pet Store API user
  I want to upload image for an existing pet by ID
  So that the Pet has image in the Pet Store system uploaded

  @JIRA-9 @smoke
  Scenario: [Jira-9] Upload image for existing pet
    Given I prepare image from path "src/test/resources/images/pets.jpg" for a pet with ID 1
    When I upload the image for that pet
    Then The "pet" response status code should be 200

  @JIRA-10 @negative @blocked
  Scenario: [Jira-10] Fail to upload image for a non-existent pet
    Given I prepare image from path "src/test/resources/images/pets.jpg" for a pet with ID 99999999
    When I upload the image for that pet
    Then The "pet" response status code should be 404
    And The "pet" response should contain error message "Pet not found"