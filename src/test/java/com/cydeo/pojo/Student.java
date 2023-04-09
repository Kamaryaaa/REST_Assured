package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data  // for generating getter and setter, and toString
@JsonIgnoreProperties(ignoreUnknown = true)// for ignoring the fields we do don't need to verify
public class Student {

    private String firstName;
    private int batch;
    private String major;
    private Contact contact; // because emailAddress is held by contacr array, so we need to create contact POJO to reach email address;
    private Company company;



}
