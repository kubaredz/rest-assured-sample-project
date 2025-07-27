@regression @delete @pet
Feature: Delete Pet method tests

  As a Pet Store API user
  I want to delete a pet
  So that the pet is properly removed from the system

  Background: Successfully create a new pet
    Given I prepare a pet with the following data
      | id           | 111                     |
      | name         | Reksio                  |
      | status       | Available               |
      | categoryId   | 10                      |
      | categoryName | Dog                     |
      | photoUrls    | https://dog.com/dog.jpg |
      | tagId        | 25                      |
      | tagName      | German Shepherd         |
    When I send POST request to create the pet

  @JIRA-11 @smoke
  Scenario Outline: [Jira-11] Successfully delete Pet by Id
    Given I use the pet with ID <id>
    When I delete the pet by ID
    Then The "pet" response status code should be <code>
    And The "pet" response should contain the following system message
      | code    | <code>  |
      | type    | unknown |
      | message | <id>    |

    Examples:
      | id  | code |
      | 111 | 200  |

  @JIRA-12 @negative
  Scenario: [Jira-12] Try to delete non-existing pet
    Given I use the pet with ID 975131
    When I delete the pet by ID
    Then The "pet" response status code should be 404