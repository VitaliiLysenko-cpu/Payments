package com.lysenko.payments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberGeneratorTest {

    @Test
    void get16DigitsNumberTest() {
        final Long actual = NumberGenerator.get16DigitsNumber();
        assertTrue(actual >= 1000000000000000L && actual <= 9999999999999999L);
    }

    @Test
    void get3DigitsNumberTest(){
        final int actual = NumberGenerator.get3DigitsNumber();
        assertTrue(actual >= 100 && actual <=999);
    }
}