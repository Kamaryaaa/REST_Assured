package com.cydeo.day08;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P02_BookItTest extends BookItTestBase {
    String email = "lfinnisz@yolasite.com";
    String password = "lissiefinnis";
            String accessToken= BookItUtils.getToken(email,password);
;
    @DisplayName("GET/api/campuses")
    @Test
    public void test1(){


        System.out.println(accessToken);

        given().accept(ContentType.JSON)
                .header("Authorization",accessToken).when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);


    }

    //


    @DisplayName("get/api/users/me")
    @Test
    public void test2(){
        given()
                .accept(ContentType.JSON)
                .header("Authorization",BookItUtils.getToken(email,password))
                .when()
                .get("/api/users/me").prettyPeek()
                .then()
                .statusCode(200);
    }


}
