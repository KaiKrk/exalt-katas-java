package com.exalt.company;

import java.util.stream.Stream;

public class StringCalculatorKata {

    final private String commaSeperator = ",";
    final private String newLineSeparator = "\n";


    /**
     * delimeter used to split the working String, set to comma by default but can change if new separator is detected
     */
    private String defaultSeparator= commaSeperator;

    public int add(String... numbers) {
        /**
         * this variable is used to store the sum of all numbers
         */
        int sumOfNumbers = 0;

        /**
         * loop to sum the result of all String numbers sum's
         */
        for (String wNumbers: numbers
             ) {

            if (!wNumbers.isEmpty()) {
                //Check if new delimeter is detected
                if (wNumbers.startsWith("//")){
                    // extract the new delimeter
                    defaultSeparator= wNumbers.substring(2,3);
                    // substring the beginning of the string after extraction to get properly formatted string
                    wNumbers = wNumbers.substring(4);
                }

                wNumbers = wNumbers.replace(newLineSeparator,defaultSeparator);

                sumOfNumbers += Stream.of(wNumbers.split(defaultSeparator)).mapToInt(Integer::parseInt).sum();
            }


        }



        return sumOfNumbers;
    }
}
