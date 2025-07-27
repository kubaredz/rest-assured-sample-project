@regression @order @delete
Feature: Delete Order method tests

  As a Pet Store API user
  I want to delete an order
  So that the order is properly removed from the system

  Background: Successfully POST an order
    Given I prepare an order with the following data
      | id       | 3731                     |
      | petId    | 5678                     |
      | quantity | 2                        |
      | shipDate | 2025-07-24T12:42:44.528Z |
      | status   | placed                   |
      | complete | true                     |
    When I send POST request to place the order

  @JIRA-60 @smoke
  Scenario Outline: [Jira-60] Successfully delete Order by Id
    Given I have an order with ID <id>
    When I delete the order by ID
    Then The "order" response status code should be <code>
    And The "order" response should contain the following system message
      | code    | <code>  |
      | type    | unknown |
      | message | <id>    |

    Examples:
      | id   | code |
      | 3731 | 200  |

  @JIRA-62 @negative
  Scenario Outline: [Jira-62] Try to delete invalid or non-existent order
    Given I use the order with ID <id>
    When I delete the order by ID
    Then The "order" response status code should be 404
    And The "order" response should contain the following system message
      | code    | 404             |
      | type    | unknown         |
      | message | Order Not Found |

    Examples:
      | id    |
      | 99999 |
      | -1    |
      | 0     |