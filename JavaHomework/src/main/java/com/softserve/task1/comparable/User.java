package com.softserve.task1.comparable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User implements Comparable<User> {
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.getAge());
    }
}
