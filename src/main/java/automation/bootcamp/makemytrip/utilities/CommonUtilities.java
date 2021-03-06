package automation.bootcamp.makemytrip.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtilities {
    /***
     * Get the current date
     * @return Returns the current date in a string form
     */
    public Date getCurrentDate(String date) throws ParseException {
        Date dateFormat = new SimpleDateFormat("dd/MM/yyyy ").parse(date);
        return dateFormat;
    }

    public static String capitalizeFirstLetter(String stringToCapitalize){
        String output = stringToCapitalize.substring(0, 1).toUpperCase() + stringToCapitalize.substring(1);
        return output;
    }
}
