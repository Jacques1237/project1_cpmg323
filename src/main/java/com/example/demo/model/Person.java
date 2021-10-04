package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {

    private final UUID id;

    @NotBlank
    private final String name;
    private final String lastname;


    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("lastname") String lastname){
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }
}
