Feature: This test deals with the creation, retrieval, and deletion of customers.

  @local_test
  Scenario Outline: Create customers
    Given I have a payload containing the following properties: "<first_name>" "<last_name>"
    And   I have set the message headers <headers>
    When  I send this message to the add customer path
    Then  the http status code of the add customer response message must be 200 OK
    When  I retrieve the customer "<first_name>" "<last_name>" using the get customer path
    Then  the "<first_name>" "<last_name>" must be retrieved

    Examples:
    | headers                             | first_name | last_name |
    | {"Content-Type":"application/json"} | Andy       | Murray    |
    | {"Content-Type":"application/json"} | Rafael     | Nadal     |
