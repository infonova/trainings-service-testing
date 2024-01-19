@Automated
Feature: Test user service

  Scenario: Storing and deleting users feature is successful
    When user from file "requests/user1.json" is successfully stored
    And user from file "requests/user2.json" is successfully stored
    Then verify that 2 users were stored via DemoApplication
    And users get successfully deleted
    Then verify that 0 users were stored via DemoApplication

  Scenario: TDD - Store user1 and user2 and delete only user2
    # Our goal is to introduce a new feature for the user endpoint, allowing us to delete a specific user based on the username.
    # We will follow the Test-Driven Development (TDD) approach.
    # Note: The necessary steps and glue code have already been defined.

    # To-Do List:
    # todo: Prepare the system by storing "user1" and "user2" (similar to the first scenario).
    # todo: Trigger the endpoint to delete the user "Maria.Musterfrau" at the path "/user/Maria.Musterfrau"
    # todo: Verify that only 1 user is stored.
    # todo: Verify that "Maria.Musterfrau" is not stored anymore.
    # todo: For Cleanup purpose - delete all users

    # todo: Implement the necessary endpoint in the application to enable the deletion of a specific user.
    Given user from file "requests/user1.json" is successfully stored
    Given user from file "requests/user2.json" is successfully stored
    When verify that 2 users were stored via DemoApplication
    Then user "Maria.Musterfrau" is successfully deleted
    And verify that 1 users were stored via DemoApplication
    And user "Maria.Musterfrau" is not stored
    And users get successfully deleted
