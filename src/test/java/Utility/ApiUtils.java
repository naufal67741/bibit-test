package Utility;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.List;

public class ApiUtils {
    private RequestSpecification request;
    private JSONObject requestBody;
    private String url;

    public ApiUtils() {
        request = RestAssured.given() .log().all();
        requestBody = new JSONObject();
    }

    public void setBaseUrl(String baseUrl) {
        this.url = baseUrl;
        System.out.println("Base URL: " + baseUrl);
    }

    public void setHeader(String key, String value) {
        request.header(key, value);
    }

    public void setBody(String body) {
        request.body(body);
    }

    public Response get(String endpoint) {
        return request.get(url+endpoint);
    }

    public Response post(String endpoint) {
        return request.post(url+endpoint);
    }

    public Response put(String endpoint) {
        return request.put(url+endpoint);
    }

    public Response delete(String endpoint) {
        return request.delete(url+endpoint);
    }

    public int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    public void addFieldToBody(String key, String value) {
        requestBody.put(key, value);
    }

    public void addFieldToBody(String key, int value) {
        requestBody.put(key, value);
    }

    public void finalizeBody() {
        request.body(requestBody.toString());
        System.out.println("Request Body: " + requestBody.toString());
    }

    public String getStringFromResponse(Response response, String field) {
        return response.jsonPath().getString(field);
    }

    public int getIntFromResponse(Response response, String field) {
        return response.jsonPath().getInt(field);
    }

    public static void validateJsonResponseSchema(Response response, String schemaFilePath) {
        File schema = new File(schemaFilePath);
        response.then().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    public List<Object> getObjectArray(Response response, String field) {
        return response.jsonPath().getList(field);
    }
}
