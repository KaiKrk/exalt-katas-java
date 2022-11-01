package com.exalt.company;

import java.util.stream.Stream;

public class StringCalculatorKata {

    final private String commaSeperator = ",";

    public int add(String numbers) {
        if (numbers.isEmpty())
            return 0;

        return Stream.of(numbers.split(commaSeperator)).mapToInt(Integer::parseInt).sum();
    }
}
