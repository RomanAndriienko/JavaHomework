package com.softserve.task1.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Driver {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String license;
    private String city;
    private Car car;
}
