package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P02_SpartanPOST extends SpartanTestBase {
    /**
     Given except type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is 'A Spartan is Born!'
     "name": "John Doe",
     "gender": "Male",
     "phone": 8877445596
     */


    @Test
    public void test1(){

        String requestBody = "{\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John Doe\",\n" +
                "     \"phone\":8877445596\n" +
                "     }";
        JsonPath jsonPath = given().accept(ContentType.JSON) // send me a json response
                .and()
                .contentType(ContentType.JSON)// i am sending u json request body
                .body(requestBody)
                .when().post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("John Doe",jsonPath.getString("data.name"));
        assertEquals("Male",jsonPath.getString("data.gender"));
        assertEquals(8877445596l,jsonPath.getLong("data.phone"));


        System.out.println("jsonPath.getInt(\"id\") = " + jsonPath.getInt("data.id"));


    }








    @Test
    public void test2(){
        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","John Doe");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone","8877445596");

        JsonPath jsonPath = given().accept(ContentType.JSON) // send me a json response
                .and()
                .contentType(ContentType.JSON)// i am sending u json request body
                .body(requestBodyMap)
                .when().post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("John Doe",jsonPath.getString("data.name"));
        assertEquals("Male",jsonPath.getString("data.gender"));
        assertEquals(8877445596l,jsonPath.getLong("data.phone"));


        System.out.println("jsonPath.getInt(\"id\") = " + jsonPath.getInt("data.id"));


    }

    @Test
    public void test3(){
        Spartan spartan=new Spartan();
        spartan.setName("KumsaKumsa");
        spartan.setGender("Female");
        spartan.setPhone(8877445596l);

        JsonPath jsonPath = given().log().body().accept(ContentType.JSON) // send me a json response
                .and()
                .contentType(ContentType.JSON)// i am sending u json request body
                .body(spartan)
                .when().post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("KumsaKumsa",jsonPath.getString("data.name"));
        assertEquals("Female",jsonPath.getString("data.gender"));
        assertEquals(8877445596l,jsonPath.getLong("data.phone"));


        System.out.println("jsonPath.getInt(\"id\") = " + jsonPath.getInt("data.id"));


    }

    @Test
    public void test4(){
        Spartan spartanPost=new Spartan();
        spartanPost.setName("KumsaKumsa");
        spartanPost.setGender("Female");
        spartanPost.setPhone(8877445596l);

        JsonPath jsonPath = given().log().body().accept(ContentType.JSON) // send me a json response
                .and()
                .contentType(ContentType.JSON)// i am sending u json request body
                .body(spartanPost)
                .when().post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        assertEquals("KumsaKumsa",jsonPath.getString("data.name"));
        assertEquals("Female",jsonPath.getString("data.gender"));
        assertEquals(8877445596l,jsonPath.getLong("data.phone"));


        int id = jsonPath.getInt("data.id");

        //send get request to the spartan that is created then deserialize to spartan class and compare

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id).get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();
        Spartan spartanGet = response.as(Spartan.class);
        System.out.println("spartanGet = " + spartanGet);


        // verify names we send and get is matching
        assertEquals(spartanPost.getName(),spartanGet.getName());


    }



}
