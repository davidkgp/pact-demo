package com.test.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Student {

    private String rollId;
    private String fullName;
    private int age;
    private Address address;
}
