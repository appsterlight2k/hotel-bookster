package com.appsterlight.$$$temporary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    //
    public static LocalDate parseDateFromDatePickerWithTimeZones(String dateString) throws Exception {
        //For ISO-dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }
}
