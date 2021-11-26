package com.lysenko.payments;

import java.util.Random;

public class NumberGenerator {

    public static Long get16DigitsNumber() {
        final long MAX_NUMBER_YOU_WANT_TO_HAVE = 9999999999999999L;
        final long MIN_NUMBER_YOU_WANT_TO_HAVE = 1000000000000000L;
        return Long.valueOf(Math.abs(Float.valueOf(new Random().nextFloat() * (MAX_NUMBER_YOU_WANT_TO_HAVE - MIN_NUMBER_YOU_WANT_TO_HAVE)).longValue()));
    }

    public static int get3DigitsNumber() {
        final int MAX_NUMBER_YOU_WANT_TO_HAVE = 999;
        final int MIN_NUMBER_YOU_WANT_TO_HAVE = 100;
        return Math.abs(Float.valueOf(new Random().nextFloat() * (MAX_NUMBER_YOU_WANT_TO_HAVE - MIN_NUMBER_YOU_WANT_TO_HAVE)).intValue());
    }
}
