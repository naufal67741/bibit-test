package StepDefinitions;

import Utility.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

public class StepDefinitionApi {
    private ApiUtils apiUtils = new ApiUtils();
    private Response response;

    @Given("the API base URL is {string}")
    public void setBaseUrl(String baseUrl) {
        apiUtils.setBaseUrl(baseUrl);
    }

    @Given("the request {string} body is {string}")
    public void addFieldToBody(String key, String value) {
        apiUtils.addFieldToBody(key, value);
    }

    @Given("the request {string} body is {int}")
    public void addFieldToBody(String key, int value) {
        apiUtils.addFieldToBody(key, value);
    }

    @When("user send a {string} request to {string}")
    public void sendGetRequest(String method,String endpoint) {
        switch (method) {
            case "GET":
                response = apiUtils.get(endpoint);
                break;
            case "POST":
                response = apiUtils.post(endpoint);
                break;
            case "PUT":
                response = apiUtils.put(endpoint);
                break;
            case "DELETE":
                response = apiUtils.delete(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Invalid request type");
        }
    }

    @Then("the response {string} is {string}")
    public void validateResponseFieldString(String field, String expectedValue) {
        String actualValue = apiUtils.getStringFromResponse(response, field);
        System.out.println("Actual value: " + actualValue);
        System.out.println("Expected value: " + expectedValue);
        Assert.assertEquals("The " + field + " field value match", expectedValue, actualValue);
    }

    @Then("the response {string} is {int}")
    public void validateResponseFieldInt(String field, int expectedValue) {
        int actualValue = apiUtils.getIntFromResponse(response, field);
        Assert.assertEquals("The " + field + " field value does not match", expectedValue, actualValue);
    }

    @When("finalize JSON body")
    public void finalizeBody() {
        apiUtils.finalizeBody();
    }

    @When("the header {string} is {string}")
    public void setHeader(String key, String value) {
        apiUtils.setHeader(key, value);
    }

    @Then("the schema matches with {string}")
    public void validateJsonResponseSchema(String schemaFilePath) {
        ApiUtils.validateJsonResponseSchema(response, schemaFilePath);
    }

    @Then("each response {string} is {string}")
    public void validateEachResponseFieldString(String field, String expectedValue) {
        List<Object> actualValues = apiUtils.getObjectArray(response, field);
        System.out.println("Actual values: " + actualValues);
        switch (expectedValue) {
            case "not null":
                for (Object actualValue : actualValues) {
                    Assert.assertNotNull("The " + field + " field value is not null", actualValue);
                }
                break;
            default:
                throw new IllegalArgumentException("not implemented expected value");
        }
    }

    @Then("status code is {int}")
    public void validateStatusCode(int expectedStatusCode) {
        int actualStatusCode = apiUtils.getStatusCode(response);
        Assert.assertEquals("The status code match", expectedStatusCode, actualStatusCode);
    }

    @Then("the response body is {string}")
    public void validateResponseBody(String expectedResponseBody) {
        String actualResponseBody = response.getBody().asString();
        Assert.assertEquals("The response body match", expectedResponseBody, actualResponseBody);
    }
}
