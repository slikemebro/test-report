package com.ua.hlibkorobov.testreport.util;

import java.time.LocalDate;

public final class ConverterDate {
    private ConverterDate() {
    }

    public static LocalDate convertToLocalDateViaInstant(String dateToConvert) {
        return LocalDate.parse(dateToConvert);
    }
}
