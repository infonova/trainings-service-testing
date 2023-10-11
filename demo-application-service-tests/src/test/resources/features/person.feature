@Automated
Feature: Test person service

  Scenario: Storing and deleting persons feature is successful
    When person from file "requests/person1.json" gets successfully stored
    And person from file "requests/person2.json" gets successfully stored
    Then verify that 2 persons were stored via DemoApplication
    And persons get successfully deleted
    Then verify that 0 persons were stored via DemoApplication
