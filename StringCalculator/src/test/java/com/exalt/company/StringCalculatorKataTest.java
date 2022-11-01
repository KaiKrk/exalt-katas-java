package com.exalt.company;

import com.exalt.company.StringCalculatorKata;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class StringCalculatorKataTest {

    private StringCalculatorKata calculatorKata;

    @BeforeEach
    void init(){
        calculatorKata = new StringCalculatorKata();
    }

    @Test
    void addEmptyNumberShouldReturn0(){
        final String number = "";
        final int sumOfNumbers = calculatorKata.add(number);
        assertThat(sumOfNumbers).isEqualTo(0);
    }

    @Test
    void addOneAndTwoNumberShouldReturn3(){
        final String number = "1,2";
        final int sumOfNumbers = calculatorKata.add(number);
        assertThat(sumOfNumbers).isEqualTo(3);
    }

}
