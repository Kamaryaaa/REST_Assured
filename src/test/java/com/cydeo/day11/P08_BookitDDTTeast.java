package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class P08_BookitDDTTeast {
    @ParameterizedTest
    @MethodSource("readBookItQA3")
    public void test(Map<String, String> credentials) {
       // System.out.println("credentials = " + credentials);

        String token = RestAssured.given()
                .baseUri("https://api.qa3.bookit.cydeo.com")
                .queryParam("email", credentials.get("email"))
                .queryParam("password", credentials.get("password"))
                .when().get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

        System.out.println("token = " + token);
    }

    
    public static List<Map<String, String>> readBookItQA3() {

        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");
        return excelUtil.getDataList();

    }

    }
