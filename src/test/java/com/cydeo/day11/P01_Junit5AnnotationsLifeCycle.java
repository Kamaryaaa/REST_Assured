package com.cydeo.day11;

import org.junit.jupiter.api.*;

public class P01_Junit5AnnotationsLifeCycle {

    @BeforeAll
    public static void init(){
        System.out.println("-------BeforeAll running------");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("-------BeforeEach running------");

    }

    @Test
    public void test1(){


        System.out.println("----Test 1 is running--------");


    }

    @Test
    public void test2(){


        System.out.println("----Test 2 is running--------");


    }
    @AfterEach
    public void destroyEach(){
        System.out.println("-------BeforeEach running------");

    }

    @AfterAll
    public static void destroy(){
        System.out.println("-------AfterEach running------");
    }







}
