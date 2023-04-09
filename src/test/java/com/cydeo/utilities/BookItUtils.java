package com.cydeo.utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class BookItUtils {


    public static  String getToken(String email, String password){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when().get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath();
        String accessToken = jsonPath.getString("accessToken");

        return "Bearer "+accessToken;

    }




}
