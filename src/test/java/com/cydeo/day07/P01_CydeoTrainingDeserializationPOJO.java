package com.cydeo.day07;

import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P01_CydeoTrainingDeserializationPOJO extends CydeoTestBase {
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
    public void test1() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 2).when().get("/student/{id}");



        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        //deserialize to Student class
        Student student = jsonPath.getObject("students[0]", Student.class);
        System.out.println(student.getFirstName());

        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());


        assertEquals("Mark",student.getFirstName());

        assertEquals(13,student.getBatch());

        assertEquals("math",student.getMajor());

        assertEquals("mark@email.com",student.getContact().getEmailAddress());

        assertEquals("Cydeo",student.getCompany().getCompanyName());

        assertEquals("777 5th Ave",student.getCompany().getAddress().getStreet());

        assertEquals(33222,student.getCompany().getAddress().getZipCode());


    }



    @DisplayName("GET /students")
    @Test
    public void tests() {

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 2).when().get("/student/{id}");



        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        //deserialize to Student class
        Students students = jsonPath.getObject("", Students.class);
        System.out.println("students = " + students);
        Student student = students.getStudents().get(0);

        System.out.println(student.getFirstName());

        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());


        assertEquals("Mark",student.getFirstName());

        assertEquals(13,student.getBatch());

        assertEquals("math",student.getMajor());

        assertEquals("mark@email.com",student.getContact().getEmailAddress());

        assertEquals("Cydeo",student.getCompany().getCompanyName());

        assertEquals("777 5th Ave",student.getCompany().getAddress().getStreet());

        assertEquals(33222,student.getCompany().getAddress().getZipCode());


    }

}
