@Automated
Feature: Test fancy service
# todo parallelize test with correlationId id??

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