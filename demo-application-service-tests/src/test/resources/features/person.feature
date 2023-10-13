@Automated
Feature: Test user service

  Scenario: Storing and deleting users feature is successful
    When user from file "requests/user1.json" gets successfully stored
    And user from file "requests/user2.json" gets successfully stored
    Then verify that 2 users were stored via DemoApplication
    And users get successfully deleted
    Then verify that 0 users were stored via DemoApplication
