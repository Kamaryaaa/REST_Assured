package com.cydeo.day10;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P02_MovieXML {



    @Test
    public void movieXML(){

        Response response = given().queryParam("apikey", "65957cbf")
                .queryParam("i", "tt3896198")
                .queryParam("r", "xml")
                .queryParam("t", "Inception")
                .when().get("http://www.omdbapi.com").prettyPeek();

        XmlPath xmlPath = response.xmlPath();
        System.out.println("xmlPath.getString(\"root.movie.@year\") = " + xmlPath.getString("root.movie.@year"));
//get title
        System.out.println("xmlPath.getString(\"root.movie.@title\") = " + xmlPath.getString("root.movie.@title"));

    }
    /**
     * http://www.omdbapi.com/?i=tt3896198&apikey=65957cbf&r=xml&s=Harry Potter
     * try to get all years and verify they are greter then 2000
     * --print each title and make sure each contINS HARRY POTTER
     * --verify total result is 129
     */

    @Test
    public void movieXMLSearch(){


        XmlPath xmlPath = given().
                queryParam("apikey", "65957cbf")
                .queryParam("i", "tt3896198")
                .queryParam("r", "xml")
                .queryParam("s", "Harry Potter").when().get("http://www.omdbapi.com")
                .prettyPeek().xmlPath();

        List<String> titles = xmlPath.getList("root.result.@title");
        for (String title : titles) {
            assertTrue(title.contains("Harry Potter"));

        }
        int totalResults = xmlPath.getInt("root.@totalResults");
        assertEquals(129,totalResults);

        List<String> years = xmlPath.getList("root.result.@year");


        assertThat(years, everyItem(greaterThan("2000")));

    }





}
