package automation.bootcamp.makemytrip.utilities;

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

    public static String capitalizeFirstLetter(String stringToCapitalize) {
        String output = stringToCapitalize.substring(0, 1).toUpperCase() + stringToCapitalize.substring(1);
        return output;
    }

    public static String[] splitString(String stringToSplit, String charToSplitAt) {
        String[] stringParts = stringToSplit.split(charToSplitAt);
        if (stringParts != null) {
            return stringParts;
        }
        return null;

    }

    private static String getEquivalentMonthNumber(String month) {
        switch (month) {
            case "Jan":
                return "01";
            case "Feb":
                return "02";
            case "Mar":
                return "03";
            case "Apr":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "Aug":
                return "08";
            case "Sep":
                return "09";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case "Dec":
                return "12";
            default:
                return "This month has no equivalent string!!";
        }
    }

    public static String getDateInNumbers(String date) {
        String monthInDateString = splitString(date, "/")[1];
        String monthNumber = getEquivalentMonthNumber(monthInDateString);

        return date.replace(monthInDateString, monthNumber);
    }

}
