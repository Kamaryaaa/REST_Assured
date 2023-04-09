package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P01_SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("GET/Spartans as guest user -->expect-->401")
    @Test
    public void test1(){

        given().log().all()
                .accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(401)
                .body("error",is("Unauthorized"));








    }





    @DisplayName("GET/api/spartans as USER  -->EXPECT -->200")
    @Test
    public void test2(){

        given().accept(ContentType.JSON)
                .auth().basic("user","user")
                .when().get("/api/spartans").then().statusCode(200).log().all();




    }


@DisplayName("delete/api/spartans/{id} as editor --expect -->403")
    @Test
    public void test3(){

    given()

            .pathParam("id", 123)
            .auth()
            .basic("editor", "editor")
            .when()
            .delete("api/spartans/{id}").prettyPeek()
            .then().statusCode(403)
            .body("error",is("Forbidden"));


}

    @DisplayName("delete/api/spartans/{id} as admin --expect -->204")
    @Test
    public void test4(){

        given()
                .pathParam("id", 67)
                .auth()
                .basic("admin", "admin")
                .when()
                .delete("api/spartans/{id}").prettyPeek()
                .then().statusCode(204);


    }








}
