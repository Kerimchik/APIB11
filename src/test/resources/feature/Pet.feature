Feature: Test all pet operations

  Scenario: Create pet test
    Given I have valid url to create a pet
    When I send POST request to create a pet
    Then status code should be 200
    And response should be in json format





    Scenario: Get a pet
      Given I have a valid url to get a pet
      When I send Get request to retrieve a pet with 4362788 id
      Then status is 200
      And response body should contain 4362788 id




  Scenario: Update a pet a pet
    Given I have a valid url to update a pet
    When I send Put request to update a pet
    Then status should be 200
    And response body should "application/json"


  Scenario: Delete a pet
    Given I have a valid url to delete a pet
    When I send Delete request to delete a pet with 3265768 id
    Then the status should  be 200
    And pet with id 3265768 should not exist





