package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidation {
    public static boolean validateDate(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
