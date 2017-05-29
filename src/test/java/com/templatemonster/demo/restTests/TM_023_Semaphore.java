package com.templatemonster.demo.restTests;


import com.jayway.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class TM_023_Semaphore {
    private final String generatedUrl = "https://api.myjson.com/bins/13ft85";
    private final String generatedInvorrectUrl = "https://api.myjson.com/bins/13ft852";

    @Test
    public void statusCodeTest() {
        RestAssured.when().get(generatedUrl).then().statusCode(200);
        RestAssured.when().get(generatedUrl).then().statusLine("HTTP/1.1 200 OK");
        RestAssured.when().get(generatedInvorrectUrl).then().statusCode(404);
    }

    @Test
    public void bodyTest() {
        RestAssured.when().get(generatedUrl).then().body(Matchers.containsString("ggobi"));
        RestAssured.when().get(generatedUrl).then().body("login", Matchers.equalTo("ggobi"));
        // the response body should have a login property which is equal to ggobi".
        RestAssured.when().get(generatedUrl).then().body("login", Matchers.equalTo("ggobi"))
                .body("id", Matchers.equalTo(423638)).statusCode(200);
    }

    @Test
    public void testUserFetchesSuccess() {
        RestAssured.expect().
                body("id", Matchers.equalTo(423638)).
                body("login", Matchers.equalTo("ggobi")).
                body("url", Matchers.equalTo("https://api.github.com/orgs/ggobi")).
                when().
                get(generatedUrl);
    }

    @Test
    public void testUserNotFound() {
        RestAssured.expect().
                body(Matchers.nullValue()).
                when().
                get(generatedInvorrectUrl);
    }
}
