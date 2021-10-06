package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class MilesRequest {
    @NotBlank
    private final int iMiles;
    private final UUID personID;


    public MilesRequest(@JsonProperty("personID") UUID personID, @JsonProperty("miles") int iMiles){
        this.iMiles = iMiles;
        this.personID = personID;

    }

    public int getMiles() {
        return iMiles;
    }
    public UUID getPersonID() {
        return personID;
    }


}
