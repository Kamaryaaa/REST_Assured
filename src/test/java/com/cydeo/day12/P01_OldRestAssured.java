package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P01_OldRestAssured extends SpartanNewTestBase {
    @Test
    public void getAllSpartan(){
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(261))
                .body("id[1]",is(4))
                .log().body();





    }

    @Test
    public void getAllSpartanOldWay(){
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
        .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(1))
                .body("id[1]",is(2))
        .when()
                .get("/spartans");





    }










}
