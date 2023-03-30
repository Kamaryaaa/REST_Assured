package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SimpleGetRequest {

    String url = "http://54.144.20.60:8000/api/spartans";

    /*
    When users send request to /api/spartans endpoint
    Then user should be able to see status code is 200
    And print out response body into screen

     */

    @Test
    public void simpleGetRequest(){
        Response response = RestAssured.get(url);

        //both same no diffrence, they get response status code
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //verify that status code is 200
        int statusCode = response.statusCode();

        //assert that it is 200
        Assertions.assertEquals(200,statusCode);

        //how to print json response
        response.prettyPrint();



    }


}
