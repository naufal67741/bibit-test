@api
Feature: JSONPlaceholder Posts API Testing

  Scenario: Create a new post
    When I create a new post
    Then the post is created with correct data

  Scenario: Retrieve all posts
    When I retrieve all posts
    Then each post should have a non-null id

  Scenario: Delete a post
    When I delete the post with id 1
    Then the post should be deleted successfully

  Scenario: Update a post
    When I update the post with id 1
    Then the post should be updated with correct data