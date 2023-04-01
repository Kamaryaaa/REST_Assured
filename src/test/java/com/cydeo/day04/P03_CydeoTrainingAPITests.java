package com.cydeo.day04;

import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.AbstractDocument;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {


 /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
    */
 @DisplayName("GET /student/2")
    @Test
    public void test1(){

     Response response = given().accept(ContentType.JSON).and().pathParam("id", 2).when().get("/student/{id}");

     response.prettyPrint();

     assertEquals(200,response.statusCode());

     assertEquals("application/json;charset=UTF-8",response.contentType());

     assertTrue(response.headers().hasHeaderWithName("Date"));
     assertEquals(response.header("server"),"envoy");


     JsonPath jsonPath = response.jsonPath();

     assertEquals(jsonPath.getString("students[0].firstName"),"Mark");
     assertEquals(jsonPath.getInt("students[0].batch"),13);
     assertEquals(jsonPath.getString("students[0].major"),"math");
     assertEquals(jsonPath.getString("students[0].contact.emailAddress"),"mark@email.com");
     assertEquals(jsonPath.getString("students[0].company.companyName"),"Cydeo");
     assertEquals(jsonPath.getString("students[0].company.address.street"),"777 5th Ave");
     assertEquals(jsonPath.getString("students[0].company.address.zipCode"),"33222");



 }


 /*
    TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
     */

 @DisplayName("get a single student with path parameter")
 @Test
 public void test2(){

  Response response = given().accept(ContentType.JSON).pathParam("batch", 22).when().get("/student/batch/{batch}");
  response.prettyPrint();

  assertEquals(200,response.statusCode());

  assertEquals("application/json;charset=UTF-8",response.contentType());

  assertTrue(response.headers().hasHeaderWithName("Date"));
  assertEquals("envoy",response.header("server"));



  List<Integer> list = response.path("students.batch");
  System.out.println("list = " + list);
  System.out.println("list.size() = " + list.size());

  for (Integer each : list) {
   assertEquals(22,each);

  }


 }






}
