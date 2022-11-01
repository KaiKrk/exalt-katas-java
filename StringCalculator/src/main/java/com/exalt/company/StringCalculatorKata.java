package com.exalt.company;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
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
         * List to store any negative value
         */
        List negativeValueArray = new ArrayList();
        /**
         * loop to sum the result of all String numbers sum's
         */
        for (String number: numbers
             ) {

            if (!number.isEmpty()) {
                //Check if new delimeter is detected
                if (number.startsWith("//")){
                    // extract the new delimeter
                    defaultSeparator= number.substring(2,3);
                    // substring the beginning of the string after extraction to get properly formatted string
                    number = number.substring(4);
                }
                //format the string with default delimeter between ints
                number = number.replace(newLineSeparator,defaultSeparator);

                final String wNumber = number;
                // check through the current string of numbers if contains any negative value
                Supplier<IntStream> numberAsArray = () -> Stream.of(wNumber.split(defaultSeparator)).mapToInt(Integer::parseInt);
                for (int i : numberAsArray.get().toArray()){
                    if( i < 0){
                        negativeValueArray.add(i);
                    }
                }

                sumOfNumbers += numberAsArray.get().sum();
            }
        }

        if(!negativeValueArray.isEmpty()){
            throw new IllegalArgumentException("no negative are allowed " + negativeValueArray);
        }

        return sumOfNumbers;
    }
}
