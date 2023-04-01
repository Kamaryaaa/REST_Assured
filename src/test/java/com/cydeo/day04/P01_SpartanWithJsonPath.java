package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_SpartanWithJsonPath extends SpartanTestBase {


    @Test
    public void task() {
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

        assertEquals(200, response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.getContentType());

        //we saved our response as JSONPATH  obj
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        short gender = jsonPath.getShort("gender");
        long phone = jsonPath.getLong("phone");

        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phone);


    }
}