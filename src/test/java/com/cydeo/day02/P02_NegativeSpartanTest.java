package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P02_NegativeSpartanTest {





    @BeforeAll
    public static void init(){
        RestAssured.baseURI= "http://54.144.20.60:8000";
    }


    /*
     * Given accept  content type is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     */

    @Test
    public void getAllSpartan(){
        Response response = RestAssured.given().accept(ContentType.JSON).when()
                .get("/api/spartans");

        Assertions.assertEquals(200,response.statusCode());

        response.prettyPrint();

    }

    /*
        Given Accept type application/xml
        When user send GET request to /api/spartans/10 end point
        Then status code must be 406
        And response Content Type must be application/xml;charset=UTF-8;
        */
    @DisplayName("Accept,application/xml, 406")
    @Test
    public void xmlTest(){
        Response response = RestAssured.given().when().accept(ContentType.XML).get("/api/spartans/10");

        Assertions.assertEquals(406,response.statusCode());

        Assertions.assertEquals("application/xml;charset=UTF-8",response.contentType());

    }








}
