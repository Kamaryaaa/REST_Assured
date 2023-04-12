package com.cydeo.day10;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P04_JsonSchemaValidation extends SpartanTestBase {

@Test
    public void test1(){


    given().accept(ContentType.JSON)
            .pathParam("id",20)
            .when().get("/api/spartans/{id}")
            .prettyPeek().then().statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));







    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SearchSpartansSchema.json"));
    }





}
