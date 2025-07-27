@regression @get @pet
Feature: GET Pet method tests

  As a Pet Store API user
  I want to get a pet
  So that I can get all details of pet properly

  Background: Successfully create a new pet
    Given I prepare a pet with the following data
      | id           | 110                     |
      | name         | Reksio                  |
      | status       | Available               |
      | categoryId   | 10                      |
      | categoryName | Dog                     |
      | photoUrls    | https://dog.com/dog.jpg |
      | tagId        | 25                      |
      | tagName      | German Shepherd         |
    When I send POST request to create the pet

  @JIRA-13 @smoke
  Scenario: [Jira-13] Successfully GET Pet by Id with details
    Given I use the pet with ID 110
    When I retrieve the pet by ID using a GET method
    Then The "pet" response status code should be 200
    And The pet response should contain
      | id     | 110       |
      | name   | Reksio    |
      | status | Available |

  @JIRA-14 @negative @blocked
  Scenario: [Jira-14] Attempt to get Pet with non-existing ID
    Given I use the pet with ID 9999
    When I retrieve the pet by ID using a GET method
    Then The "pet" response status code should be 404
    And The "pet" response should contain error message "Pet not found"