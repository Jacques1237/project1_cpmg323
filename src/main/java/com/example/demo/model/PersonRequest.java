package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class PersonRequest {


    @NotBlank
    private final String name;

    public PersonRequest(@JsonProperty("name")String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
