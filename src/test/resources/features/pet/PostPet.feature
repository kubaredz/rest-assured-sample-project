@regression @post @pet
Feature: Positive & Negative POST Pet method

  As a Pet Store API user
  I want to create a new pet with full details
  So that it is registered in the Pet Store system properly

  As a Pet Store API user
  I want to verify that invalid requests are rejected when creating a new pet
  So that the system remains stable and secure

  @JIRA-1 @smoke
  Scenario: [JIRA-1] Successfully create a new pet with all details
    Given I prepare a pet with the following data
      | id           | 101                     |
      | name         | Reksio                  |
      | status       | Available               |
      | categoryId   | 10                      |
      | categoryName | Dog                     |
      | photoUrls    | https://dog.com/dog.jpg |
      | tagId        | 25                      |
      | tagName      | German Shepherd         |
    When I send POST request to create the pet
    Then The "pet" response status code should be 200
    And The pet response should contain
      | id           | 101                     |
      | name         | Reksio                  |
      | status       | Available               |
      | categoryId   | 10                      |
      | categoryName | Dog                     |
      | photoUrls    | https://dog.com/dog.jpg |
      | tagId        | 25                      |
      | tagName      | German Shepherd         |

  @JIRA-2
  Scenario: [JIRA-2] Successfully create a pet with specific id and name
    Given I prepare a pet with the following data
      | id   | 100 |
      | name | Rex |
    When I send POST request to create the pet
    Then The "pet" response status code should be 200
    And The pet response should contain
      | id   | 100 |
      | name | Rex |

  @JIRA-3
  Scenario: [JIRA-3] Create pet with an ID that already exists
    Given I prepare a pet with the following data
      | id   | 100           |
      | name | Duplicate Rex |
    When I send POST request to create the pet
    Then The "pet" response status code should be 200
    And The pet response should contain
      | id   | 100           |
      | name | Duplicate Rex |

  @JIRA-4
  Scenario: [JIRA-4] Successfully create a pet with multiple photo URLs
    Given I prepare a pet with the following data
      | id        | 103                                      |
      | name      | Buddy                                    |
      | photoUrls | https://a.com/a.jpg, https://b.com/b.jpg |
    When I send POST request to create the pet
    Then The "pet" response status code should be 200
    And The pet response should contain
      | photoUrls | https://a.com/a.jpg, https://b.com/b.jpg |

  @JIRA-5
  Scenario: [JIRA-5] Successfully create a pet without tag and category
    Given I prepare a pet with the following data
      | id     | 104    |
      | name   | NoMeta |
      | status | sold   |
    When I send POST request to create the pet
    Then The "pet" response status code should be 200
    And The pet response should contain
      | id     | 104    |
      | name   | NoMeta |
      | status | sold   |

  @JIRA-8
  Scenario Outline: [Jira-8] Successfully create a pet with different status
    Given I prepare a pet with the following data
      | id     | 101      |
      | name   | Dog      |
      | status | <status> |
    When I send POST request to create the pet
    Then The "pet" response status code should be 200
    And The pet response should contain
      | id     | 101      |
      | name   | Dog      |
      | status | <status> |

    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |

  @JIRA-6 @blocked
  Scenario: [JIRA-6] Fail to create a pet when required field 'name' is missing
    Given I prepare an invalid pet with the following data
      | id | 102 |
    When I send POST request to create the pet
    Then The "pet" response status code should be 400

  @JIRA-7 @blocked
  Scenario: [JIRA-7] Fail to create a pet with non-numeric ID
    Given I prepare an invalid pet with the following data
      | id   | abc          |
      | name | InvalidIdPet |
    When I send POST request to create the pet
    Then The "pet" response status code should be 400

  @JIRA-8 @blocked
  Scenario Outline: [Jira-8] Fail to create a pet with invalid status
    Given I prepare a pet with the following data
      | id     | 101      |
      | name   | Dog      |
      | status | <status> |
    When I send POST request to create the pet
    Then The "pet" response status code should be 400

    Examples:
      | status      |
      | unavailable |
      | 123         |