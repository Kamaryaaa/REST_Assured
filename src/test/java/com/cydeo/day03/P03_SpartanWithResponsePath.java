package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P03_SpartanWithResponsePath extends SpartanTestBase {





     @Test
    public void task(){
          /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */
         Response response = given().accept(ContentType.JSON)
                 .and().pathParams("id", 10).when()
                 .get("api/spartans/{id}");

         response.prettyPrint();

         assertEquals(200,response.getStatusCode());

         assertEquals(ContentType.JSON.toString(),response.getContentType());

         int id = response.path("id");
         String name = response.path("name");
         String gender = response.path("gender");
         long phone = response.path("phone");

         System.out.println("id = " + id);
         System.out.println("name = " + name);
         System.out.println("gender = " + gender);
         System.out.println("phone = " + phone);

         //if the path is incorrect it will return null
         String address = response.path("address");
         System.out.println("address = " + address);

         //Assertions
         assertEquals(10,id);
         assertEquals("Lorenza",name);
         assertEquals("Female",gender);
         assertEquals(3312820936l,phone);







     }


     @DisplayName("GET all Spartans")
     @Test
     public void test2(){
          Response response = given().accept(ContentType.JSON)
                  .when().get("/api/spartans");

          //response.prettyPrint();

          //get me first spartan ID
          int firstId = response.path("[0].id");
          System.out.println("firstId = " + firstId);
          int IDFirst = response.path("id[0]");
          System.out.println("IDFirst = " + IDFirst);


          //get first spartan name
          System.out.println("response.path(\"[0].name\") = " + response.path("[0].name"));
          System.out.println("response.path(\"[1].name\") = " + response.path("[1].name"));

          //get me last spartan
          //name[-1] --> -1 refers last element of the name list
          System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));

          //get me second spartan name from the last
          System.out.println("response.path(\"name[-2]\") = " + response.path("name[-2]"));

          //get me all spartan names
          List<String> allNames = response.path("name");

          //how to print all names
          for (String eachName : allNames) {
               System.out.println("eachName = " + eachName);
          }
     }








}
