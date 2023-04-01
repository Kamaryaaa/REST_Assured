package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.AbstractDocument;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithJsonPath extends HrTestBase {


@Test
    public void test1(){
    Response response = get("/countries");


    //response.prettyPrint();

    assertEquals(200,response.statusCode());

    JsonPath jsonPath = response.jsonPath();

    System.out.println("jsonPath.getString(\"items[2].country_name\") = " + jsonPath.getString("items[2].country_name"));

    //get me 3rd and 4rth country name
    System.out.println("jsonPath.getString(\"items[2,3].country_name\") = " + jsonPath.getString("items[2,3].country_name"));


    //get me all country name where region id is 2

    List<Object> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
    System.out.println("list = " + list);


}

@DisplayName("GET all /employees?limit=200 with jsonpath")
    @Test
    public void test2(){

    Response response = given().accept(ContentType.JSON).and()
            .queryParam("limit", 200).when().get("/employees");
    //response.prettyPrint();

    assertEquals(200,response.statusCode());

    JsonPath jsonPath = response.jsonPath();


    //get all emails from response
    List<String> allEmails = jsonPath.getList("items.email");
    System.out.println("allEmails = " + allEmails);
    System.out.println("allEmails.size() = " + allEmails.size());


//        //get all emails who are working as IT_PROG
    List<Object> listOfIT = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
    System.out.println("listOfIT = " + listOfIT);
    

    //get me all employees first names whose salary is more than 10000
    List<String> firstNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
    System.out.println("firstNames = " + firstNames);
    System.out.println("firstNames.size() = " + firstNames.size());


    //        //get me all information from response who has max salary
    String maxSalaryPerson = jsonPath.getString("items.max {it.salary}");
    System.out.println("maxSalaryPerson = " + maxSalaryPerson);

    //get me first name from response who has max salary
    System.out.println("jsonPath.getString(\"items.max{it.salary}.first_name\") = " + jsonPath.getString("items.max{it.salary}.first_name"));

    //get me firstname from response who has min salary
    System.out.println("jsonPath.getString(\"items.min{it.salary}.first_name\") = " + jsonPath.getString("items.min{it.salary}.first_name"));


}

/*
    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locations
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK
      */

    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON).when().get("/locations");
       // response.prettyPrint();

        assertEquals(200,response.statusCode());

        assertEquals(ContentType.JSON.toString(),response.getContentType());

        JsonPath jsonPath = response.jsonPath();

        System.out.println("jsonPath.getString(\"items[1].city_name\") = " + jsonPath.getString("items[1].city"));//Bern


       // get the last city with JsonPath
        System.out.println("jsonPath.getString(\"items[-1].city\") = " + jsonPath.getString("items[-1].city"));//Whitehorse

        //             get all country ids
        System.out.println("jsonPath.getList(\"items.country_id\") = " + jsonPath.getList("items.country_id"));

        //              get all city where their country id is UK
        System.out.println("jsonPath.getList(\"items.findAll {it.country_id=='UK'}\") = " + jsonPath.getList("items.findAll {it.country_id=='UK'}"));


    }






}
