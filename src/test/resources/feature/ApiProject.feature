Feature: users

  Scenario: User Information
    Given User is on the users endpoint
    And User sets the payload variables
    When  User send get request
    Then Endpoint should display 200 status code
    And All user's information is displayed in the data list






  Scenario Outline: User Information Negative
    Given User is on the users endpoint
    And User misses <x> field on payload
    When  User send get request
    Then Endpoint should display 200 status code
    And All user's information is displayed in the data list

    Examples:
    |x|
    | name|
    | email|