package com.softserve.task1.builder;

public class Demo {
    public static void main(String[] args) {
        Car car1 = Car.builder()
                .brand("Fiat")
                .model("Punto")
                .licencePlate("AA1111AA")
                .colour("black")
                .numberOfSeats(4)
                .yearOfProduction(2010)
                .build();

        Driver driver1 = Driver.builder()
                .firstName("Roman")
                .email("qwer@gmail.com")
                .phone("+380505055555")
                .license("qwerty")
                .lastName("Andriienko")
                .password("*******")
                .city("Dnipro")
                .car(car1)
                .build();


        System.out.println(driver1);
    }
}
