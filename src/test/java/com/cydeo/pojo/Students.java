package com.cydeo.pojo;

import java.security.PrivateKey;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Students {

    private List<Student> students;


}
