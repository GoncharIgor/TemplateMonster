package com.templatemonster.demo.restTests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TM_022_RestAssured {
    private String jsonUrl = "https://api.github.com/users/hadley/orgs";

    @Test
    public void TM_022_testGet() throws URISyntaxException {
        URI uri = new URI("http://www.templatemonster.com/");

        Response response = RestAssured.when().get(uri);
        //System.out.println(response.asString());

        Response response1 = RestAssured
                .when()
                .get(new URI(jsonUrl));
        System.out.println(response1.asString());

        Response response2 = RestAssured
                .given()
                .accept(ContentType.XML) //given accepts the response in xml format
                .when()
                .get(new URI(jsonUrl));
        System.out.println(response2.asString());

    }

    @Test
    public void TM_022_testStatusCode() throws URISyntaxException {
        int code = RestAssured.when().get(jsonUrl).thenReturn().statusCode();
        System.out.println(code);
        //Assert.assertEquals(200, code, "Incorrect code was received");
        Assert.assertEquals(HttpStatus.SC_OK, code, "Incorrect code was received");

        ValidatableResponse response = RestAssured.when().get(jsonUrl).then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void TM_022_getWithBaseParameters() {
        RestAssured.baseURI = "http://";
        RestAssured.port = 80;
        RestAssured.basePath = "/api.github.com";

        String body = RestAssured.when().get("/users/hadley/orgs").thenReturn().body().asString(); //works for localhost,
        // because there we have particular port

    }

    @Test
    public void TM_022_getWithHeader() throws URISyntaxException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        String body = RestAssured.
                given()
                .headers(headers)
                .when()
                .get(new URI(jsonUrl))
                .thenReturn()
                .body()
                .asString();
        System.out.println(body);
    }

    @Test
    public void TM_022_extractJson() {
        //Response response = RestAssured.when().get(jsonUrl);
        Response response = RestAssured.when().get(jsonUrl).then().extract().response();
        String result = response.asString();

        // first we put our 'jsonAsString' into an ArrayList of Maps of type <String, ?>
        // ArrayList<Map<String,?>> jsonAsArrayList = RestAssured.from(result).get("");

        // put all of the IDs into a list
        List<Integer> listOfIDs = response.path("id");
        for (int id : listOfIDs) {
            System.out.println(id);
        }
    }

    @Test
    public void TM_022_extractJsonValue() {
        List<String> list = RestAssured.when().get(jsonUrl).thenReturn().path("login");
        for (String login : list) {
            System.out.println(login);
        }
    }

    @Test
    public void TM_022_extractJsonValueFromBody() {
        RestAssured.when().get(jsonUrl).then().body(Matchers.containsString("ggobi"));
    }

    @Test
    public void valueValidation() {
        // String s = RestAssured.when().get(jsonUrl).thenReturn().path("login").toString(); //returns List<String>
        RestAssured.when().get(jsonUrl).then().body("login", Matchers.hasItems("ggobi"));
        // RestAssured.when().get(jsonUrl).then().body("login", Matchers.equalToIgnoringCase("ggobi")); <-
        // if we have only 1 returned Object
    }

    @Test
    public void multipleValueValidation() {
        //checks the 1-st O in Json response
        RestAssured.when().get(jsonUrl).then().body("login", Matchers.hasItems("ggobi"), "id", Matchers.hasItems(423638));
    }

    @Test
    public void validateNestedJsonO() {
        RestAssured.when().get(jsonUrl).then().body("Features.name", Matchers.hasItem("ggobi"));
    }

    @Test
    public void jsonPathValidation() {
        String res = RestAssured.when().get(jsonUrl).thenReturn().asString();
        JsonPath jsonPath = new JsonPath(res);
        String login = jsonPath.getString("login");
        System.out.println(login);

        List<String> list = jsonPath.getList("login");
        list.forEach(item -> System.out.println(item));
    }

    @Test
    public void handleTheQuerry() {
        String response = RestAssured.given().param("login", "ggobi").param("id").when().get(jsonUrl).thenReturn().asString();
        System.out.println(response);
        RestAssured.given().param("login", "ggobi").param("id").when().get(jsonUrl).then().assertThat().statusCode(HttpStatus.SC_OK).and()
                .assertThat().body("login", Matchers.hasItems("ggobi"));
    }
}




