package com.test.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String postCode;
    private String city;


}
