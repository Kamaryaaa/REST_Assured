package com.cydeo.utilities;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class SpartanNewTestBase {
 public static RequestSpecification reqAdminSpec;
 public static RequestSpecification reqUserSpec;
 public static ResponseSpecification resSpec;


 @BeforeAll
 public static void init() {
  baseURI = "http://54.237.226.155";
  port = 7000;
  basePath = "/api";

  reqAdminSpec = given()
          .log().all()
          .accept(ContentType.JSON)
          .auth().basic("admin", "admin");

  reqUserSpec = given()
          .log().all()
          .accept(ContentType.JSON)
          .auth().basic("user", "user");


  resSpec =
          expect().statusCode(200)
                  .contentType(ContentType.JSON);

  //Create dynamic method which is accepting username and password as a parameter and returning
  //request specification dynamicReqSpec()

 }

 public static RequestSpecification dynamicReqSpec(String username,String password) {

  return
          given()
                  .log().all()
                  .accept(ContentType.JSON)
                  .auth().basic(username, password);

 }

 public static ResponseSpecification dynamicResSpec(int statusCode) {

  return
          expect().statusCode(statusCode)
                  .contentType(ContentType.JSON);

 }






}