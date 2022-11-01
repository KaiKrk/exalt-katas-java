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

    @Test
    void addSeveralStringNumbersShouldReturn3(){
        final String number1 = "1,2,3";
        final String number2 = "1,2,3";
        final String number3 = "1,2,3";
        final int sumOfNumbers = calculatorKata.add(number1,number2,number3);
        assertThat(sumOfNumbers).isEqualTo(18);
    }

}
