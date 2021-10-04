package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class PersonRequest {


    @NotBlank
    private final String name;
    private final String lastname;
    public PersonRequest(@JsonProperty("name") String name, @JsonProperty("lastname") String lastname){
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }
}
