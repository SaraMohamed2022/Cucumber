Feature: Validating place addition API

  Scenario Outline: Verify if place is being successfully added using AddPlace API with different test data
    Given Add Place Payload with "<name>" , "<address>" , "<language>"
    When user calls "AddPlaceAPI" api with "post" request
    Then  the API call success with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify the generated Place_Id maps to "<name>" using "GetPlaceAPI" API
    Examples:
    | name | address | language |
    | My house | 19287 Berlin Germnay | English |
#    | My family house | 10000 Berlin Germnay | French |


  Scenario: Verify if place is being successfully added using AddPlace API
    Given Add Place Payload
    When user calls "AddPlaceAPI" api with "post" request
    Then  the API call success with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"




