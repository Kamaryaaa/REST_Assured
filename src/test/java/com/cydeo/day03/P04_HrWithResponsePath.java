package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P04_HrWithResponsePath extends HrTestBase {

    @DisplayName("GET Request to countries with using Response Path")
    @Test
    public void test1() {


        /*

         */


        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

       // response.prettyPrint();

        //print limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        //print second country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));
        //get all country names
        System.out.println("response.path(\"country_name\") = " + response.path("items.country_name"));
        //print 4th element country name
        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));
        //print 3rd element href
        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));

        List<String> allNames = response.path("items.country_name");
        System.out.println("allNames = " + allNames);

        //verify all regions_ids equals to 2
        List<Integer> actualRegionIds = response.path("items.region_id");
        System.out.println(actualRegionIds);

        for (Integer eachID : actualRegionIds) {
            assertEquals(2,eachID);
            System.out.println("eachID = " + eachID);
        }

        }


}



