package com.softserve.task1.comparable;

import java.util.Set;
import java.util.TreeSet;


public class Demo {
    public static void main(String[] args) {
        Set<User> userSet = fillSet();

        for (User user : userSet) {
            System.out.println(user);
        }
    }

    private static Set<User> fillSet() {
        Set<User> userSet = new TreeSet<>();

        userSet.add(new User("Bob", "Johnson", 33));
        userSet.add(new User("Tom", "Smith", 54));
        userSet.add(new User("Tom", "Smith", 54));
        userSet.add(new User("Tim", "Nicholson", 28));

        return userSet;
    }
}

