package automation.bootcamp.makemytrip.utilities.helperClasses;

import automation.bootcamp.makemytrip.pages.BasePage;
import automation.bootcamp.makemytrip.utilities.CommonUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CalendarClass extends BasePage {
    private String[] dateParts;
    public By currentSelectedDayLocator;
    public By dayPickerCaptionListLocator;
    public By monthYearNextButtonLocator;


    By datePickerContainer_div_className = By.className("datePickerContainer");

    public CalendarClass(WebDriver driver, By currentSelectedDayLocator, By dayPickerCaptionListLocator, By monthYearNextButtonLocator) {
        super(driver);
        this.currentSelectedDayLocator = currentSelectedDayLocator;
        this.dayPickerCaptionListLocator = dayPickerCaptionListLocator;
        this.monthYearNextButtonLocator = monthYearNextButtonLocator;
    }

    public static String getEquivalentMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";

            case 4:
                return "April";
            case 5:
                return "May";

            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "No such month!";
        }
    }

    private boolean checkDatePickerDisplayed() {
        if (checkWebElementIsDisplayed(datePickerContainer_div_className)) {
            return true;
        }
        return false;
    }

    /***
     * Returning the dateCaption from the UI which holds both (Month and Year);
     * It returns two objects one for the CheckIn and the other for CheckOut
     * @return list of dateCaption objects
     */
    private List<String> getDateCaptionFromCalendar() {
        if (checkDatePickerDisplayed()) {
            List<WebElement> datePickerList = getWebElements(dayPickerCaptionListLocator);
            List<String> dateCaption = new ArrayList<String>();
            for (WebElement dateContainer : datePickerList) {
                dateCaption.add(dateContainer.getText());
            }
            return dateCaption;
        }
        return null;
    }

    private String getCurrentSelectedDay() {
        return getWebElement(currentSelectedDayLocator).getText();
    }

    private String[] getDayMonthYearFromJson(String date) {
        dateParts = CommonUtilities.splitString(date, "/");
        return dateParts;
    }

    /***
     * Clicking on the day returned from the JSON object
     */
    private void clickOnDay() {
        By days_div_xpath = By.xpath("//div[@class='DayPicker-Month']//div[@class='DayPicker-Day'][contains(@aria-label,'" + dateParts[0] + "')]");

        String day = getDayText(getWebElement(days_div_xpath));
        if (day.equals(dateParts[0])) {
            clickOnButton(days_div_xpath);
        }

    }

    private String getDayText(WebElement webElement) {

        if (Integer.valueOf(webElement.getText()) < 10) {
            String tobeSelectedText = "0".concat(webElement.getText());
            return tobeSelectedText;
        } else {
            return webElement.getText();
        }
    }

    /***
     * For now I check the  on the Month returned from the JSON object to be selected on the UI
     * @param date, date returned from the JSON object to check the year and month (for now I check on the month ONLY!)
     */
    public void selectDateFromCalendar(String date, int dateCaptionIndex) {
        boolean flag = true;
        String checkInMonth;
        String checkInYear;

        String monthInJsonString = CalendarClass.getEquivalentMonth(Integer.valueOf(getDayMonthYearFromJson(date)[1]));
        String yearInJson = getDayMonthYearFromJson(date)[2];

        if (getDateCaptionFromCalendar().size() != 0) {
            checkInMonth = getDateCaptionFromCalendar().get(dateCaptionIndex).substring(0, getDateCaptionFromCalendar().get(0).length() - 4);

            while (flag) {
                if (!monthInJsonString.equalsIgnoreCase(checkInMonth)) {
                    clickOnButton(monthYearNextButtonLocator);
                }
                flag = false;

            }
            clickOnDay();
        }

    }


}
