@regression @order @get
Feature: Get Order by ID

  As a Pet Store API user
  I want to retrieve a specific order by its ID
  So that I can verify the details of a particular order

  Background: Successfully POST an order
    Given I prepare an order with the following data
      | id       | 1                        |
      | petId    | 5678                     |
      | quantity | 2                        |
      | shipDate | 2025-07-24T12:42:44.528Z |
      | status   | placed                   |
      | complete | true                     |
    When I send POST request to place the order

  @JIRA-56 @smoke
  Scenario: [Jira-56] Successfully retrieve an order by ID
    Given I have an order with ID 1
    When I send a GET request to retrieve the order by ID 1
    Then The "order" response status code should be 200
    And The response should contain the order details with ID 1

  @JIRA-57 @negative
  Scenario: [Jira-57] Attempt to retrieve a non-existent order
    When I send a GET request to retrieve the order by ID 999999
    Then The "order" response status code should be 404

  @JIRA-58 @negative
  Scenario: [Jira-58] Attempt to retrieve an order with invalid ID
    When I send a GET request to retrieve the order by ID -1
    Then The "order" response status code should be 404

  @JIRA-59
  Scenario: [Jira-59] Validate structure of returned order
    Given I have an order with ID 1
    When I send a GET request to retrieve the order by ID 1
    Then The "order" response status code should be 200
    And The order should contain fields: "id, petId, quantity, status, complete"