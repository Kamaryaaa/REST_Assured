package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequests {


    String url = "http://54.144.20.60:8000";



    @Test
    public void getAllSpartan(){

        /*
         * Given content type is application/json
         * When user sends GET request /api/spartans endpoint
         * Then status code should be 200
         * And Content type should be application/json
         */
        Response response = RestAssured.given().accept(ContentType.JSON).get(url + "/api/spartans");//hey api pls send me json response

        //response.prettyPrint();


        int actualStatusCode = response.statusCode();


        Assertions.assertEquals(200,actualStatusCode);

        String actualContentType = response.contentType();
        System.out.println("contentType = " + actualContentType);

        Assertions.assertEquals("application/json",actualContentType);

        //how to get connection header value
        //if we want to get any response header value, we can use header("headerName")
        //method from response obj, it will return header value as string

        System.out.println(response.header("content-type"));
        System.out.println(response.header("connection"));
        System.out.println(response.header("date"));

        //how to verify header exist?
        //is headerWithName method help us to verify header exists or not
        //it is useful for dynamic header values like date, we are
        //System.out.println(response.headers().hasHeaderWithName("Date"));
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);




    }
    ///*
    //     * Given content type is application/json
    //     * When user sends GET request /api/spartans/3 endpoint
    //     * Then status code should be 200
    //     * And Content type should be application/json
    //     * And response body needs to contains Fidole
    //     */

@Test
public void getSpartan(){
        Response response = RestAssured.given().accept(ContentType.JSON).get(url+"/api/spartans/3");
    int actualStatusCode = response.statusCode();
    Assertions.assertEquals(200,actualStatusCode);

    String actualContentType = response.contentType();
    //diffrent ways of doing the same assertions
    Assertions.assertEquals("application/json",actualContentType);
    Assertions.assertEquals("application/json",response.getContentType());
    Assertions.assertEquals("application/json",response.header("Content-type"));
    Assertions.assertEquals(ContentType.JSON.toString(),actualContentType);
    response.prettyPrint();

    //verify body contains "Fidole
     Assertions.assertTrue(response.body().asString().contains("Fidole"));// contains is not a good way for assertions
     /*
            This is not a good way to make assertion. In this way we are just converting response to String and
            with the help of String contains we are just looking into Response.But we should be able to get json
            "name" key value then verify that one is equal to "Fidole"
         */



      }
      /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
         */

      @Test
    public void helloSpartan(){
          //        When Users send GET request to /api/hello
          Response response = RestAssured.when().get(url + "/api/hello");

          //print result on console
          response.prettyPrint();


          //        Then response status code should be 200
          Assertions.assertEquals(200,response.statusCode());


          //        And Content type header should be "text/plain;charset=UTF-8"
          Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());


          //        And header should contain Date
          Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));


         //        And Content-Length should be 17
          Assertions.assertEquals("17",response.header("Content-length"));
          System.out.println(response.header("Content-length"));


          //        And body should be "Hello from Sparta"
          Assertions.assertEquals("Hello from Sparta",response.body().asString());
      }





}
