package com.cydeo.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class P06_MethodSourceTest {


    public static List<String> getNames(){

        List<String> namesList = Arrays.asList("Steven","TJ","Kimberly","Mike");
        return namesList;

    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name){
        System.out.println("name = " + name);








    }



}
