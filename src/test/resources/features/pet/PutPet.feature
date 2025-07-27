@regression @put @pet
Feature: Update pet details using PUT method

  As a Pet Store API user
  I want to update an existing pet
  So that I can change its details correctly in the system

  Background: Successfully create a new pet for update test
    Given I prepare a pet with the following data
      | id           | 120                     |
      | name         | Reksio                  |
      | status       | Available               |
      | categoryId   | 10                      |
      | categoryName | Dog                     |
      | photoUrls    | https://dog.com/dog.jpg |
      | tagId        | 25                      |
      | tagName      | German Shepherd         |
    When I send POST request to create the pet

  @JIRA-21 @smoke
  Scenario: [Jira-21] Successfully update pet name and status
    Given I use the pet with ID 120
    And I update the pet with the following data
      | name   | Burek |
      | status | Sold  |
    When I send PUT request to update the pet
    Then The "pet" response status code should be 200
    And The pet response should contain
      | id     | 120   |
      | name   | Burek |
      | status | Sold  |

  @JIRA-22 @negative @blocked
  Scenario: [Jira-22] Fail to update pet with missing required fields
    Given I use the pet with ID 120
    And I update the pet with the following data
      | name |  |
    When I send PUT request to update the pet
    Then The "pet" response status code should be 400
    And The "pet" response should contain the following system message
      | code    | 400       |
      | type    | unknown   |
      | message | bad input |

  @JIRA-23 @negative @blocked
  Scenario: [Jira-23] Fail to update a non-existing pet
    Given I prepare a pet with the following data
      | id           | 99999                 |
      | name         | GhostDog              |
      | status       | Available             |
      | categoryId   | 1                     |
      | categoryName | Spirit                |
      | photoUrls    | https://ghost.com/img |
      | tagId        | 77                    |
      | tagName      | Invisible             |
    When I send PUT request to update the pet
    Then The "pet" response status code should be 404
    And The "pet" response should contain the following system message
      | code    | 1             |
      | type    | error         |
      | message | Pet not found |

  @JIRA-24
  Scenario: [Jira-24] Update pet with long name string
    Given I use the pet with ID 120
    And I update the pet with the following data
      | name | ReksioReksioReksioReksioReksioReksioReksioReksio |
    When I send PUT request to update the pet
    Then The "pet" response status code should be 200
    And The pet response should contain
      | id   | 120                                              |
      | name | ReksioReksioReksioReksioReksioReksioReksioReksio |