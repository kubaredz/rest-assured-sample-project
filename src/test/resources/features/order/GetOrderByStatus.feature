@regression @order @getByStatus
Feature: Get Inventory Status by Pet Availability

  As a Pet Store API user
  I want to get inventory counts grouped by pet status
  So that I can monitor how many pets are sold, pending, or available

  @JIRA-54 @smoke
  Scenario: [Jira-54] Verify inventory response contains status counts
    When I send a GET request to fetch inventory by status
    Then The "order" response status code should be 200
    And The response should contain inventory counts for each status

  @JIRA-55
  Scenario: [Jira-55] Verify inventory contains expected status keys
    When I send a GET request to fetch inventory by status
    Then The response should contain the following status keys
      | available |
      | pending   |
      | sold      |