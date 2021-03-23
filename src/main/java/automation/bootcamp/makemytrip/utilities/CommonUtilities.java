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

    /***
     * Method to capitalize first letter for a string
     * @param stringToCapitalize
     * @return
     */
    public static String capitalizeFirstLetter(String stringToCapitalize) {
        String output = stringToCapitalize.substring(0, 1).toUpperCase() + stringToCapitalize.substring(1);
        return output;
    }

    /***
     * Method to split a string based on sent characters
     * @param stringToSplit
     * @param charToSplitAt
     * @return
     */
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

    /***
     * Method to get the value for a month into a number but returned as string as I dont know why switch case at this time
     * didnt accept numbers then I think I updated something on the pom to accept this but didnt update this for now
     * @param date the date returned from the UI
     * @return
     */
    public static String getDateInNumbers(String date) {
        String monthInDateString = splitString(date, "/")[1];
        String monthNumber = getEquivalentMonthNumber(monthInDateString);
        return date.replace(monthInDateString, monthNumber);
    }

}
