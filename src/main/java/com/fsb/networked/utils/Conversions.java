package com.fsb.networked.utils;

import java.time.LocalDate;

public class Conversions {
    public static LocalDate stringtoLocalDate(String dobString)
    {
        int year = Integer.parseInt(dobString.substring(0, 4));
        int month = Integer.parseInt(dobString.substring(5, 7));
        int day = Integer.parseInt(dobString.substring(8));
        return LocalDate.of(year, month, day);
    }
}
