package com.fsb.networked.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Conversions {
    /*public static LocalDate stringtoLocalDate(String dobString)
    {
        int year = Integer.parseInt(dobString.substring(0, 4));
        int month = Integer.parseInt(dobString.substring(5, 7));
        int day = Integer.parseInt(dobString.substring(8));
        return LocalDate.of(year, month, day);
    }*/
    public static LocalDate stringtoLocalDate(String dateString) {
        // Specify the expected date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Parse the string to a LocalDate object using the formatter
        return LocalDate.parse(dateString, formatter);
    }
}
