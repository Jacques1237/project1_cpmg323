package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Miles {
    private final UUID id;

    @NotBlank
    private final int miles;
    private final LocalDateTime created_date;
    private final UUID personID;


    public Miles(@JsonProperty("id") UUID id, @JsonProperty("personID") UUID personID, @JsonProperty int miles) {
        this.id = id;
        this.miles = miles;
        this.personID = personID;
        this.created_date = LocalDateTime.now();
    }

    public Miles(@JsonProperty("id") UUID id, @JsonProperty("personID") UUID personID, @JsonProperty int miles,@JsonProperty LocalDateTime created_Date ) {
        this.id = id;
        this.miles = miles;
        this.personID = personID;
        this.created_date = created_Date;
    }

    public UUID getId() {

        return id;
    }

    public int getMiles() {
        return miles;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public UUID getPersonID() {
        return personID;
    }
}


