package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P03_HRDeserializationPOJO extends HrTestBase {

    @DisplayName("GET regions to deserialization to POJO -LOMBOK - JSON PROPERTY")
    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions")
                .then().statusCode(200).extract().jsonPath();

        //get first region from items array and convert it to region class
        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);
        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegionName());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());


    }

    @DisplayName("GET employee to deserialization to POJO with only required fields")
    @Test
    public void test2(){

        JsonPath jsonPath = get("/employees").then().statusCode(200).extract().jsonPath();
        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);
        System.out.println("employee1 = " + employee1);


    }

     /*
    TASK
    Given accept is application/json
    When send request  to /regions endpoint
    Then status should be 200
            verify that region ids are 1,2,3,4
            verify that regions names Europe ,Americas , Asia, Middle East and Africa
            verify that count is 4
        -- Create Regions POJO
        -- And ignore field that you dont need
     */
    @DisplayName("GET all regions and use POJO")
    @Test
    public void test3(){
        JsonPath jsonPath = get("/regions").then().statusCode(200).contentType("application/json").extract().jsonPath();

        Regions regions =  jsonPath.getObject("", Regions.class);
        System.out.println("regions = " + regions);






    }



}
