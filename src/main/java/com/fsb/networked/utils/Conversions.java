package com.fsb.networked.utils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Conversions {
    public static LocalDate stringtoLocalDate(String dateString)
    {
        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(5, 7));
        int day = Integer.parseInt(dateString.substring(8));
        return LocalDate.of(year, month, day);
    }

    public static LocalTime stringToLocaleTime(String timeString) {
        int hours = Integer.parseInt(timeString.substring(0, 2));
        int minutes = Integer.parseInt(timeString.substring(3, 5));
        int seconds = Integer.parseInt(timeString.substring(6));
        return LocalTime.of(hours,minutes,seconds);
    }
   /* public static LocalDate stringtoLocalDate(String dateString) {
        // Specify the expected date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Parse the string to a LocalDate object using the formatter
        return LocalDate.parse(dateString, formatter);
    }*/
   public static byte[] convertFileToByteArray(File file) throws IOException {
       try (FileInputStream fis = new FileInputStream(file)) {
           byte[] fileData = new byte[(int) file.length()];
           fis.read(fileData);
           return fileData;
       }
   }
    public static InputStream convertByteArrayToInputStream(byte[] byteArray) {
        return new ByteArrayInputStream(byteArray);
    }
}
