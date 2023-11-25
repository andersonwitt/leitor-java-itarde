package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static java.sql.Date parseDate(String value) {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(value);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            String mysqlDateString = sdf.format(date);
            return java.sql.Date.valueOf(mysqlDateString);
        } catch (ParseException e) {
            return null;
        }
    }
}