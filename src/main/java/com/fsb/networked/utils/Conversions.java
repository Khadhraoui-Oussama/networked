package com.fsb.networked.utils;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
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

    public static File convertBlobToFile(Blob blob) throws SQLException, IOException {
        File file = File.createTempFile("temp", null);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            // Get the input stream from the BLOB
            InputStream inputStream = blob.getBinaryStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            // Write the data from the input stream to the file output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return file;
    }
    public static Blob convertFileToBlob(File file, Connection connection) throws SQLException, IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);

           Blob a = connection.createBlob();
           a.setBytes(1,fileBytes);
           return  a;
        }
    }
}
