package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;


public class P09_SpartanPostDDT extends SpartanTestBase {

/**
 *
 *  POST SPARTAN DDT
 *
 *  Given content type is json
 *  And accept type is json
 *  When I POST Spartan
 *  And status code needs to 201
 *  Verify spartan informations macthing with dynamic taht we provide
 *
 *  Generate DUMMY DATA
 *  https://www.mockaroo.com/
 *
 *  name
 *  gender
 *  phone
 *
 */




@ParameterizedTest()
@CsvFileSource(resources = "/MOCK_DATA.csv",numLinesToSkip = 1)
    public void postSpartanDDT(String name,String gender,long phone){

    Map<String,Object> data = new LinkedHashMap<>();
    data.put("name",name);
    data.put("gender",gender);
    data.put("phone",phone);
    System.out.println("data = " + data);

    given().accept(ContentType.JSON) // send me a json response
            .and()
            .contentType(ContentType.JSON)// i am sending u json request body
            .body(data)
            .when().post("/api/spartans").prettyPeek()
            .then()
            .statusCode(201)
            .contentType("application/json")
            .body("data.name",is(equalTo(data.get("name"))),
                    "data.gender",is(equalTo(data.get("gender"))),
                    "data.phone",is(equalTo(data.get("phone"))));

}



}
