package com.exalt.company;

import java.util.stream.Stream;

public class StringCalculatorKata {

    final private String commaSeperator = ",";
    final private String newLineSeparator = "\n";

    public int add(String... numbers) {
        /**
         * this variable is used to concatenated all string into one, w stands for working
         */
        String wNumbers = "";

        /**
         * loop to concatenate all numbers parameter to one string
         */
        for (String n: numbers
             ) {
            if (wNumbers.isEmpty())
                wNumbers = wNumbers.concat(n);
            else
                wNumbers = wNumbers.concat(commaSeperator+n);
        }

        if (wNumbers.isEmpty())
            return 0;

        wNumbers = wNumbers.replace(newLineSeparator,commaSeperator);



        return Stream.of(wNumbers.split(commaSeperator)).mapToInt(Integer::parseInt).sum();
    }
}
