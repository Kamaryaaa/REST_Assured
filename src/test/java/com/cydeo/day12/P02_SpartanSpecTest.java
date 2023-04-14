package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.cydeo.utilities.SpartanNewTestBase.reqAdminSpec;
import static com.cydeo.utilities.SpartanNewTestBase.resSpec;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;


public class P02_SpartanSpecTest extends SpartanNewTestBase {



    @Test
    public void getAllSpartan(){
        given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(4))
                .body("id[1]",is(5))
                .log().body();

        RequestSpecification reqSpec = given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        ResponseSpecification resSpec = expect().statusCode(200)
                .contentType(ContentType.JSON);


    }

    @Test
    public void getAllSpartanShort(){
        given()
                .spec(reqAdminSpec)
         .when().
                get("/spartans")
         .then()
                .spec(resSpec);

    }


    @Test
    public void getOneSpartanShort(){
        given()
                .spec(reqAdminSpec)
                .and()
                .pathParam("id",21)
       .when().
                get("/spartans/{id}")
       .then()
                .spec(resSpec)
                .body("id",is(21));


    }
    @Test
    public void getOneSpartanAsUserShort(){
        given()
                .spec(reqUserSpec)
                .and()
                .pathParam("id",21)
                .when().
                get("/spartans/{id}")
                .then()
                .spec(resSpec)
                .body("id",is(21));


    }

    @Test
    public void getOneSpartanAsUserWithDynamicSpec(){
        given()
                .spec(dynamicReqSpec("admin","admin"))
                .and()
                .pathParam("id",21)
                .when().
                get("/spartans/{id}")
                .then()
                .spec(dynamicResSpec(200))
                .body("id",is(21));


    }

/**
 *  Create DELETE_RBAC.csv
 *   username,password,id,statusCode
 *    editor,editor,3,403
 *    user,user,3,403
 *    admin,admin,3,204
 *
 *  Create a parameterized test to check RBAC for DELETE method
 *
 *
 */

@ParameterizedTest
    @CsvFileSource(resources = "/DELETE_RBAC.csv",numLinesToSkip = 1)

    public void deleteSpartan(String username,String password,int id,int statusCode){


    given()
            .spec(dynamicReqSpec(username,password))
            .and()
            .pathParam("id",id)
            .when()
            .delete("/spartans/{id}")
            .then()
            .spec(dynamicResSpec(statusCode));



}

    @ParameterizedTest
    @CsvFileSource(resources = "/GET-RBAC.csv",numLinesToSkip = 1)
    public void getSpartan(String username,String password,int id,int statusCode){


        given()
                .spec(dynamicReqSpec(username,password))
                .and()
                .pathParam("id",id)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(dynamicResSpec(statusCode));



    }


















}
