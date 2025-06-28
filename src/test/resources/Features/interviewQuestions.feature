@InterviewQuestions
Feature: Interview questions for bibit

  @Web
  Scenario: Open product detail Reksadana Pasar Uang
    Given user is in bibit website
    And user is in logged in state
    When user open "explore" tab
    And user click on "Reksa Dana" menu
    And user open product detail "Reksadana Pasar Uang"
    Then user will see list of products for "Reksadana Pasar Uang"

  @Android
  Scenario: Buy and sort products
    Given user is in app homepage
    When user login
    Then user is in app homepage

    When user buy product "Sauce Labs Backpack" with quantity 2 and color "Blue"
    Then user successfully buy product

    When user sort products by "Name - Descending"
    Then user successfully sort products by "Name - Descending"

    When user sort products by "Price - Ascending"
    Then user successfully sort products by "Price - Ascending"

  @API @POST
  Scenario Outline: Create a New Post
    Given the API base URL is "https://jsonplaceholder.typicode.com"
    And the header "Content-Type" is "application/json"
    And the request "title" body is "<title>"
    And the request "body" body is "<body>"
    And the request "userId" body is <userId>
    And finalize JSON body

    When user send a "POST" request to "<endpoint>"

    Then the response "title" is "<title>"
    And the response "body" is "<body>"
    And the response "userId" is "<userId>"
    And the schema matches with "<schemaPath>"

    Examples:
      |endpoint|schemaPath|title  |body  |userId|
      |/posts|src/test/resources/JsonSchema/post.json|Learn API Testing|Practicing API testing with JSONPlaceholder|101|

  @API @GET
  Scenario Outline: Retrieve Posts
    Given the API base URL is "https://jsonplaceholder.typicode.com"
    And the header "Content-Type" is "application/json"
    When user send a "GET" request to "<endpoint>"

    Then each response "<valueToCheck>" is "<expectedValue>"
    And the schema matches with "<schemaPath>"

    Examples:
      |endpoint|schemaPath|valueToCheck|expectedValue|
      |/posts|src/test/resources/JsonSchema/get.json|id|not null|

  @API @DELETE
  Scenario Outline: Delete a Post
    Given the API base URL is "https://jsonplaceholder.typicode.com"
    And the header "Content-Type" is "application/json"
    When user send a "DELETE" request to "<endpoint>"

    Then status code is <expectedStatus>
    And the response body is "<expectedBody>"
    And the schema matches with "<schemaPath>"

    Examples:
      |endpoint|schemaPath|expectedStatus|expectedBody|
      |/posts/1|src/test/resources/JsonSchema/delete.json|200| {}|

  @API @PUT
  Scenario Outline: Update a Post
    Given the API base URL is "https://jsonplaceholder.typicode.com"
    And the header "Content-Type" is "application/json"
    And the request "title" body is "<title>"
    And the request "body" body is "<body>"
    And the request "userId" body is <userId>
    And finalize JSON body

    When user send a "PUT" request to "<endpoint>"

    Then the response "title" is "<title>"
    And the response "body" is "<body>"
    And the response "userId" is "<userId>"
    And the schema matches with "<schemaPath>"

    Examples:
      |endpoint|schemaPath|title  |body  |userId|
      |/posts/1|src/test/resources/JsonSchema/put.json|Updated Post Title|This is the updated body content.|99|
