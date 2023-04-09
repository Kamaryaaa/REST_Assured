package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P03_SpartanPUTPATCHDELETE extends SpartanTestBase {



    @DisplayName("PUT Spartan with Map")
    @Test
    public void test1(){
        int id = 423;

            Map<String,Object> requestBodyMap = new LinkedHashMap<>();
            requestBodyMap.put("name","John Doe Put");
            requestBodyMap.put("gender","Male");
            requestBodyMap.put("phone","8877445596");
     // put willl update existing record, we choose on the existing ID, make sure id is valid

        given().contentType(ContentType.JSON).pathParam("id",id)
                .body(requestBodyMap).when().put("/api/spartans/{id}")
                .then().statusCode(204);





    }




    @DisplayName("PATCH Spartan with Map")
    @Test
    public void test2(){
        int id = 423;

        Map<String,Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","John Doe PATCH");

        // put willl update existing record, we choose on the existing ID, make sure id is valid

        given().contentType(ContentType.JSON).pathParam("id",id)
                .body(requestBodyMap).when().patch("/api/spartans/{id}")
                .then().statusCode(204);





    }

    @DisplayName("DELETE Spartan with Map")
    @Test
    public void test3(){
        //we can delete one id only one time, so it will give 204 only for the first execution
        int id = 412;


        // put willl update existing record, we choose on the existing ID, make sure id is valid

        given().contentType(ContentType.JSON).pathParam("id",id)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //after deleted same request needs to give 404

        given().contentType(ContentType.JSON).pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);




    }

}
