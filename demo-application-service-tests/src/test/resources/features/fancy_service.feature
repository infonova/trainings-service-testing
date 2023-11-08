@Automated
Feature: Test fancy service
  Scenario: Call fancy service and success
    Given Wiremock will return for a GET request on "/information?user=${user}" a status 200 response
    When fancy service is called
    Then verify that the http response code is 200
    Then verify that Wiremock got 1 request for "/information?user=${user}"

  Scenario: Call fancy service successfully and check response message
    Given "TestUser" is set as user in context
    Given Wiremock will return for a GET request on "/information?user=${user}" a status 200 response with response from "responses/successful_msg.json"
    When fancy service is called
    Then verify that the http response code is 200
    Then verify that the response message is "Fancy service was called by TestUser"
    Then verify that Wiremock got 1 request for "/information?user=${user}"

  Scenario: Call fancy service and fail
    # todo write a test that calls fancy service and fails with status code 404

  Scenario: Reproduce Bug
    # A new bug has been reported.
    # An exception is thrown in the application when calling the endpoint "/demo/new-feature" with a user other than "DefaultUser"
    # As a result, the response code becomes 500.

    # To-Do List:
    # todo: Reproduce this error by writing a new service test
    # todo: Set a random user in the context (e.g., "BugUser").
    # todo: Write a new test step that calls the new feature endpoint "/demo/new-feature."
    # todo: Verify that the HTTP response code is 500.