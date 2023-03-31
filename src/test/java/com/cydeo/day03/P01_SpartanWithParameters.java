package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_SpartanWithParameters extends SpartanTestBase {

    /*   Given accept type is Json
        And Id parameter value is 24
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json
        And "Julio" should be in response payload(body)
     */
   @DisplayName("GET Spartan / api / spartans/{id} path param with 24")
    @Test
    public void SpartanWithParam(){
        Response response = given().when().accept(ContentType.JSON).
                and().
                pathParams("id",24)
                .when()
                .get("/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(200,response.statusCode());

        assertEquals(ContentType.JSON.toString(),response.contentType());

        //        And "Julio" should be in response payload(body)
       assertTrue(response.body().asString().contains("Julio"));
    }

/*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
      */

@DisplayName("GET Spartan / api / spartans/{id} path param with 500")
@Test
    public void task(){
    Response response = given().accept(ContentType.JSON).and().pathParams("id", 500).when().get("/api/spartans/{id}");
    response.prettyPrint();

    assertEquals(404,response.statusCode());
    assertEquals(HttpStatus.SC_NOT_FOUND,response.statusCode());// dose the same job with previous assertions

    assertEquals(ContentType.JSON.toString(),response.getContentType());

    assertTrue(response.body().asString().contains("Not Found"));
}

 /*
        Given Accept type is Json
        And query parameter values are:
            gender|Female
            nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @DisplayName("GET Spartan / api / spartans/search query param")
   @Test
    public void task2(){
       Response response = given().accept(ContentType.JSON).and().queryParam("gender", "Female").and().queryParam("nameContains", "e").
               when().get("/api/spartans/search");
       response.prettyPrint();

       assertEquals(200,response.statusCode());

       assertEquals(ContentType.JSON.toString(),response.getContentType());

       assertTrue(response.body().asString().contains("Female"));
       assertTrue(response.body().asString().contains("Janette"));


   }
      @DisplayName("query param using map")
    @Test
    public void task3(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON).and().queryParams(queryMap).

                when().get("/api/spartans/search");
        response.prettyPrint();

        assertEquals(200,response.statusCode());

        assertEquals(ContentType.JSON.toString(),response.getContentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }





}
