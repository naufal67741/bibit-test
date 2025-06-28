package StepDefinitions;

import Payloads.PostPayload;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class APISteps {
    private Response response;
    private PostPayload postPayload;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setupBaseURI() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @When("I create a new post")
    public void createNewPost() {
        postPayload = new PostPayload();
        postPayload.setTitle("Learn API Testing");
        postPayload.setBody("Practicing API testing with JSONPlaceholder");
        postPayload.setUserId(101);

        response = RestAssured
                .given().contentType("application/json")
                .body(postPayload)
                .post("/posts");
    }

    @Then("the post is created with correct data")
    public void verifyCreatedPost() throws IOException {
        PostPayload resPost = mapper.readValue(response.asString(), PostPayload.class);

        assertThat(resPost.getTitle(), equalTo(postPayload.getTitle()));
        assertThat(resPost.getBody(), equalTo(postPayload.getBody()));
        assertThat(resPost.getUserId(), equalTo(postPayload.getUserId()));

        response.then().assertThat().body(
                JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/schemas/post.json")
                )
        );
    }

    @When("I retrieve all posts")
    public void retrieveAllPosts() {
        response = RestAssured
                .given()
                .get("/posts");
    }

    @Then("each post should have a non-null id")
    public void verifyPosts() {
        List<PostPayload> posts = response.jsonPath().getList("", PostPayload.class);
        for (PostPayload post : posts) {
            assertThat(post.getId(), is(notNullValue()));
        }

        response.then().assertThat().body(
                JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/schemas/get.json")
                )
        );
    }

    @When("I delete the post with id 1")
    public void deletePost() {
        response = RestAssured
                .given()
                .delete("/posts/1");
    }

    @Then("the post should be deleted successfully")
    public void verifyDeletedPost() {
        assertThat(response.statusCode(), is(200));
        assertThat(response.asString().trim(), equalTo("{}"));
    }

    @When("I update the post with id 1")
    public void updatePost() {
        postPayload = new PostPayload();
        postPayload.setTitle("Updated Post Title");
        postPayload.setBody("This is the updated body content.");
        postPayload.setUserId(99);

        response = RestAssured
                .given().contentType("application/json")
                .body(postPayload)
                .put("/posts/1");
    }

    @Then("the post should be updated with correct data")
    public void verifyUpdatedPost() throws IOException {
        PostPayload resPost = mapper.readValue(response.asString(), PostPayload.class);

        assertThat(resPost.getTitle(), equalTo(postPayload.getTitle()));
        assertThat(resPost.getBody(), equalTo(postPayload.getBody()));
        assertThat(resPost.getUserId(), equalTo(postPayload.getUserId()));

        response.then().assertThat().body(
                JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/schemas/put.json")
                )
        );
    }
}
