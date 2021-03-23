package automation.bootcamp.makemytrip.pages;

import automation.bootcamp.makemytrip.utilities.CommonUtilities;
import automation.bootcamp.makemytrip.utilities.helperClasses.CalendarClass;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;


public class ReservationPage extends BasePage {

    /***
     * Locators for the city field to select the city from the dropdown
     */
    By hotel_a_xpath = By.xpath("//a[contains(@class,'hrtlCenter')][contains(@href,'hotels')]");

    By city_input_id = By.id("city");
    By city_input_xpath = By.xpath("//input[@aria-autocomplete='list']");
    By city_ul_xpath = By.xpath("//ul[@role='listbox']");
    By city_li_options_xpath = By.xpath("//ul[@role='listbox']//li");
    By city_p_xpath = By.tagName("p");

    /***
     * Locators to fill the Guests field
     */
    By guest_div_xpath = By.xpath("//div[contains(@class,'roomGuests')]");
    By guestApply_button_xpath = By.xpath("//button[contains(@class,'btnApply') and @data-cy='submitGuest']");

    /***
     * Locators for the Travel For and Search button
     */
    By travelFor_div_xpath = By.xpath("//div[contains(@class,'travelFor')]");
    By search_button_id = By.id("hsw_search_button");

    /***
     * Locators for the checkIn/checkOut fields to fill them
     */
    By currentSelectedDay = By.xpath("//div[@class='DayPicker-Week']//div[@aria-selected='true' and contains(@class,'DayPicker-Day--today')]");
    By datePickerCaptionList_div_xpath = By.xpath("//div[@class='DayPicker-Month']//div[@class='DayPicker-Caption']/div");
    By monthYearButtonNext_span_xpath = By.xpath("//div[@class='DayPicker-wrapper']//span[contains(@class,'DayPicker-NavButton--next')]");


    /***
     * Initializing a calendar class instance
     */
    CalendarClass calendarClass;
    SearchListingPage searchListingPage;

    public ReservationPage() {
        super();
    }

    public void loadPageIgnoringLogin() {
        WebElement webElement = getWebElement(By.id("root"));
        webElement.click();
        getHotelCard();
    }

    private void getHotelCard() {
        clickOnButton(hotel_a_xpath);
    }

    public void fillInCityName(String cityName) {
        clickCityName();
        fillCityName(cityName);
    }

    private void clickCityName() {
        clickOnButton(city_input_id);
    }

    /***
     * Method to fill the city name field with the name got from the PassengerInfo JSON file
     * @param cityName, The city name text which will be typed in the field
     */
    private void fillCityName(String cityName) {
        fillTextField(city_input_xpath, cityName);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getWebElement(city_input_xpath).getAttribute("value").equalsIgnoreCase(cityName)) {
            clickCityOption(cityName);
        }
//        clickCityOption(cityName);
    }

    /***
     * Method to select the city name from the dropdown displayed; Which should
     * be equal to the city name entered by the user (which we got from the PassengerInfo JSON file)
     * @param text, The city name which we got from the PassengerInfo JSON file
     */
    private void clickCityOption(String text) {
        getLiDropdownOption(city_ul_xpath, city_li_options_xpath, city_p_xpath, text);
    }

    /***
     * Method to click on the Rooms&Guests area
     */
    public void clickGuestRoomChildren() {
        clickOnButton(guest_div_xpath);
    }

    /***
     * Method to check if number of adults and children is greater than 9 throw an exception otherwise proceed;
     * This methid will be used within the class that is why it is defined as private
     * @param numberOdAdults, number of adults from the PassengerInfo JSON file
     * @param numberOfChildren, number of children from the PassengerInfo JSON file
     * @return This method returns true if the number of adults+children less than or equal 9 otherwise throw an
     * exception
     */
    /*Reem: Handle the exception that when the returned value greater than 9*/
    private boolean checkNumberOfAdultsChildren(int numberOdAdults, int numberOfChildren) {
        if (numberOdAdults + numberOfChildren <= 9) {
            return true;
        }
        return false;
    }

    /***
     * Method to click on number of adults based on the number from the PassengerInfo JSON file
     * @param numberOfAdults, Number of adults returned from the PassengerInfo JSON file
     * @param numberOfChildren, Number of children returned from the PassengerInfo JSON file
     */
    public void setNumberOfAdults(int numberOfAdults, int numberOfChildren) {
        By adult_Li_xpath = By.cssSelector("li[data-cy='adults-" + numberOfAdults + "']");
        if (checkNumberOfAdultsChildren(numberOfAdults, numberOfChildren)) {
            if (numberOfAdults != 0) {
                clickOnButton(adult_Li_xpath);
            }
        }
    }

    /***
     * Method to click on number of children based on the number from the PassengerInfo JSON file
     * @param numberOfAdults, Number of adults returned from the PassengerInfo JSON file
     * @param numberOfChildren, Number of children returned from the PassengerInfo JSON file
     */
    public void setNumberOfChildren(int numberOfAdults, int numberOfChildren) {
        By children_Li_xpath = By.cssSelector("li[data-cy='children-" + numberOfChildren + "']");
        if (checkNumberOfAdultsChildren(numberOfAdults, numberOfChildren)) {
            clickOnButton(children_Li_xpath);
        }
    }

    /***
     * Method to get the children age as a list from the PAssengerInfo JSON file;
     * This method will be used within the class that is why it is defined as private
     * @param childrenAgeObject, Children JsonObject returned from the PassengerInfo JSON file
     * @return Returns list of integer of children age
     */
    private ArrayList getChildrenAge(JsonObject childrenAgeObject) {
        ArrayList childrenAgeList = new ArrayList();
        if (childrenAgeObject != null) {
            for (String key : childrenAgeObject.keySet()) {
                childrenAgeList.add(childrenAgeObject.get(key).getAsInt());

            }
        }
        return childrenAgeList;
    }

    /***
     * Method to loop over the children age list to fill the fields on the UI with the number in the list
     * @param childrenAgeObject The array list that hols the children age
     */
    public void fillChildrenAgeDropdown(JsonObject childrenAgeObject) {
        ArrayList childrenAgeAsList = getChildrenAge(childrenAgeObject);
        if (!childrenAgeAsList.isEmpty()) {
            for (int childAge = 0; childAge < childrenAgeAsList.size(); childAge++) {
                By children_age_select_cssSelector = By.cssSelector("select[id='" + childAge + "']");
                getSelectWebElement(children_age_select_cssSelector, String.valueOf(childrenAgeAsList.get(childAge)));
            }
        }
    }

    /***
     * Method to click on "Apply" button for the Room&Guests area to apply the entered values
     */
    public void clickApplyGuests() {
        clickOnButton(guestApply_button_xpath);
    }

    /***
     * Method to click on the "Travel For" area
     */
    public void clickTravelOption() {
        clickOnButton(travelFor_div_xpath);
    }

    /***
     * Method to select the travel for option returned from PassengerInfo JSON file
     * @param travelPurpose Travel purpose text returned from the PAssengerInfo JSON file
     */
    public void clickTravelFor(String travelPurpose) {
        if (travelPurpose != null) {
            String travelPurposeString = CommonUtilities.capitalizeFirstLetter(travelPurpose);
            By travelPurpose_li_xpath = By.xpath("//li[@data-cy='travelFor-" + travelPurposeString + "']");
            clickOnButton(travelPurpose_li_xpath);
        }
    }

    /***
     * Method to select the check in date
     * @param date The date returned from the PassengerInfo JSON file
     */
    public void selectCheckInDate(String date) {
        calendarClass = new CalendarClass(this.driver, currentSelectedDay, datePickerCaptionList_div_xpath, monthYearButtonNext_span_xpath);
        calendarClass.selectDateFromCalendar(date, 0);
    }

    /***
     * Method to select the check out date
     * @param date The date returned from the PassengerInfo JSON file
     */
    public void selectCheckOutDate(String date) {
        calendarClass.selectDateFromCalendar(date, 1);

    }

    /***
     * Method to click on the Search button after entering all the data to get the search result page
     */
    public void clickSearch() {
        clickOnButton(search_button_id);
    }

    public String getNextPageUrl() {
        return getPageUrl();
    }

    public SearchListingPage getSearchListingPageObject() {
        searchListingPage = new SearchListingPage(this.driver);
        waitForPageLoad();
        return searchListingPage;
    }

}
