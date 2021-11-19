package com.lysenko.payments;

import java.util.Random;

public class NumberGenerator {

    public static Long get16DigitsNumber() {
        final long MAX_NUMBER_YOU_WANT_TO_HAVE = 9999999999999999L;
        final long MIN_NUMBER_YOU_WANT_TO_HAVE = 1000000000000000L;
        Long actual = Long.valueOf(Math.abs(Float.valueOf(new Random().nextFloat() * (MAX_NUMBER_YOU_WANT_TO_HAVE - MIN_NUMBER_YOU_WANT_TO_HAVE)).longValue()));
        return actual;
    }

    public static Long get3DigitsNumber() {
        final long MAX_NUMBER_YOU_WANT_TO_HAVE = 999L;
        final long MIN_NUMBER_YOU_WANT_TO_HAVE = 100L;
        Long actual = Long.valueOf(Math.abs(Float.valueOf(new Random().nextFloat() * (MAX_NUMBER_YOU_WANT_TO_HAVE - MIN_NUMBER_YOU_WANT_TO_HAVE)).longValue()));
        return actual;
    }
}
