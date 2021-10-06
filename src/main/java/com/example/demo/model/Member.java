package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Member {

    private final UUID id;

    @NotBlank
    private final String name;
    private final String lastname;
    private final int miles;




    public Member(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("lastname") String lastname, @JsonProperty("miles") int miles){
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.miles = miles;
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

    public int getMiles() {
        return miles;
    }


}
