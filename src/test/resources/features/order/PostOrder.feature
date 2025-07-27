@regression @order @post
Feature: Create Order in Store

  As a Pet Store API user
  I want to place an order for a pet
  So that I can buy a pet and track the order

  @JIRA-50 @smoke
  Scenario: [Jira-50] Successfully POST an order
    Given I prepare an order with the following data
      | id       | 1234                     |
      | petId    | 5678                     |
      | quantity | 2                        |
      | shipDate | 2025-07-24T12:42:44.528Z |
      | status   | placed                   |
      | complete | true                     |
    When I send POST request to place the order
    Then The "order" response status code should be 200
    And The order response should contain
      | id       | 1234                     |
      | petId    | 5678                     |
      | quantity | 2                        |
      | shipDate | 2025-07-24T12:42:44.528Z |
      | status   | placed                   |
      | complete | true                     |

  @JIRA-51
  Scenario: [Jira-51] Successfully place an order with status 'approved'
    Given I prepare an order with the following data
      | id       | 2222                     |
      | petId    | 8888                     |
      | quantity | 1                        |
      | shipDate | 2025-08-01T10:00:00.000Z |
      | status   | approved                 |
      | complete | false                    |
    When I send POST request to place the order
    Then The "order" response status code should be 200
    And The order response should contain
      | id       | 2222                     |
      | petId    | 8888                     |
      | quantity | 1                        |
      | shipDate | 2025-08-01T10:00:00.000Z |
      | status   | approved                 |
      | complete | false                    |

  @JIRA-52 @negative @blocked
  Scenario: [Jira-52] Fail to place an order with invalid petId
    Given I prepare an invalid order with the following data
      | id       | 5555                     |
      | petId    | -1                       |
      | quantity | 1                        |
      | shipDate | 2025-09-15T12:00:00.000Z |
      | status   | placed                   |
      | complete | true                     |
    When I send POST request to place the order
    Then The order response status code should not be 200

  @JIRA-53
  Scenario Outline: [Jira-53] Successfully place multiple orders with varying data
    Given I prepare an order with the following data
      | id       | <id>       |
      | petId    | <petId>    |
      | quantity | <quantity> |
      | shipDate | <shipDate> |
      | status   | <status>   |
      | complete | <complete> |
    When I send POST request to place the order
    Then The "order" response status code should be 200
    And The order response should contain
      | id       | <id>       |
      | petId    | <petId>    |
      | quantity | <quantity> |
      | shipDate | <shipDate> |
      | status   | <status>   |
      | complete | <complete> |

    Examples:
      | id   | petId | quantity | shipDate                 | status    | complete |
      | 6001 | 3001  | 1        | 2025-08-01T10:00:00.000Z | placed    | true     |
      | 6002 | 3002  | 5        | 2025-08-02T10:00:00.000Z | approved  | false    |
      | 6003 | 3003  | 0        | 2025-08-03T10:00:00.000Z | placed    | true     |
      | 6004 | 3004  | 2        | 2025-08-04T10:00:00.000Z | delivered | false    |