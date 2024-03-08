Feature: Actor

  Scenario: Actor is fetched by id
    Given Actor with id 1 exists
    When get request is made for actor 1
    Then an Actor is returned

  Scenario: Create an actor
    Given Actor is Created
    When get request is made for the Actor
    Then the created Actor is Returned