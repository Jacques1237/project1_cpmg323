package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class MemberRequest {


    @NotBlank
    private final String name;
    private final String lastname;
    private final String transaction_type;
    public MemberRequest(@JsonProperty("name") String name, @JsonProperty("lastname") String lastname,@JsonProperty("transaction_type") String transaction_type){
        this.name = name;
        this.lastname = lastname;
        this.transaction_type = transaction_type;
    }

    public String getName() {

        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTransaction_type() {

        return transaction_type;
    }
}
