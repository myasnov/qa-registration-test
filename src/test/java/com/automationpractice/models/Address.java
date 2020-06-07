package com.automationpractice.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"address1", "address2", "city", "state", "postcode", "homephone", "addinfo", "assignaddress"})

public class Address {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postcode;
    private String addinfo;
    private String assignaddress;
}
