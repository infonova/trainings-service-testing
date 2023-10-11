@Automated
Feature: Test fancy service

  Scenario: Call fancy service and success
    Given Wiremock will return for a GET request on /information a status 200 response
    When fancy service is called
    Then verify that the http response code is 200

  Scenario: Call fancy service and fail
    # todo write a test that calls fancy service and fails with status code 404