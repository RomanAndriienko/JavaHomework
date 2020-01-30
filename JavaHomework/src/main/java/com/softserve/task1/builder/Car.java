package com.softserve.task1.builder;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class Car {
    private String licencePlate;
    private String colour;
    private String brand;
    private String model;
    private Integer yearOfProduction;
    private Integer numberOfSeats;
}
