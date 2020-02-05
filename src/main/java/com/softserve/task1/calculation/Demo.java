package com.softserve.task1.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Demo {
    public static void main(String[] args) {
        Item item1 = new Item(new BigDecimal(123456), 123456);
        Item item2 = new Item(new BigDecimal(567899), 567899);

        BigDecimal bigDecimalResultMode1 = item1.getValue1().setScale(5, RoundingMode.HALF_EVEN).divide(BigDecimal.valueOf(133.77), RoundingMode.UP);
        BigDecimal bigDecimalResultMode2 = item1.getValue1().divide(BigDecimal.valueOf(133.77), RoundingMode.DOWN);

        double doubleResult = item1.getValue2() / 133.77;

        System.out.println(String.format("Item1 mode1 : %s", bigDecimalResultMode1));
        System.out.println(String.format("Item1 mode2 : %s", bigDecimalResultMode2));
        System.out.println(String.format("Item1 double : %s", doubleResult));


        BigDecimal bigDecimalResult2 = item2.getValue1().divide(BigDecimal.valueOf(237.33), RoundingMode.HALF_DOWN);
        double doubleResult2 = item2.getValue2() / 237.33;

        System.out.println(String.format("Item1 bigDecimal : %s", bigDecimalResult2));
        System.out.println(String.format("Item1 double : %s", doubleResult2));


        BigDecimal first = new BigDecimal(1111);
        double second = 1111.0;

        double result = 0;
        BigDecimal bigDecimalResult = new BigDecimal("0");

        for (int i = 1; i <= 10; i++) {
            bigDecimalResult = first.divide(BigDecimal.valueOf(i), RoundingMode.UP);
            result = second / i;
        }

        System.out.println(bigDecimalResult.doubleValue());
        System.out.println(result);

    }
}
