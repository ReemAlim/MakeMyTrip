package automation.bootcamp.makemytrip.pages;

import automation.bootcamp.makemytrip.utilities.CommonUtilities;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class ReservationPage extends BasePage {

    // Reservation locators
    //This one should be moved from here in a login page
//    By loginCard_div_xpath = By.xpath("//div[contains(@class,'login__card')]");
//    By loginPopup_button_id = By.id("username");
//    By email_input_id = By.id("username");
//    By continue_button_xpath = By.xpath("//button[@data-cy='continueBtn']");
//    By password_input_id = By.xpath("//form//input[@id='password']");
//    By login_button_xpath = By.xpath("//button[@data-cy='login']");

    //Locators for filling the city field
    By hotel_a_xpath = By.xpath("//a[contains(@class,'hrtlCenter')][contains(@href,'hotels')]");
    By login_li_xpath = By.xpath("//li[contains(@class,'lhUser')]");
    By city_input_id = By.id("city");
    By city_input_xpath = By.xpath("//input[@aria-autocomplete='list']");
    By city_ul_xpath = By.xpath("//ul[@role='listbox']");
    By city_li_options_xpath = By.xpath("//ul[@role='listbox']//li");
    By city_p_xpath = By.xpath("//p[contains(@class,'locusLabel')]");

    //Locators to fill checkin/out fields
    By datePickerContainer_div_className = By.className("datePickerContainer");
    By checkin_input_id = By.id("checkin");
    By checkout_input_id = By.id("checkout");

    By travelFor_div_xpath = By.xpath("//div[contains(@class,'travelFor')]");


    By guest_div_xpath = By.xpath("//div[contains(@class,'roomGuests')]");
    //    By adultCount_ul_cssSelector = By.cssSelector("ul[class*='guestCounter'][data-cy='adultCount']");
//    By childrenCount_ul_xpath = By.xpath("//p[@data-cy='childrenRange']/following-sibling::ul[contains(@class,'guestCounter')]");
//    By childAgeList_ul_cssSelector = By.cssSelector("ul[class*='childAgeList']");
    By guestApply_button_xpath = By.xpath("//button[contains(@class,'btnApply') and @data-cy='submitGuest']");
    //    By travelForPopup_ul_class = By.className("travelForPopup");
    By search_button_id = By.id("hsw_search_button");

    public ReservationPage() {
        super();
    }

    public void launchBrowser() {
        getURL();
    }

    public void getHotelCard() {
        clickOnButton(login_li_xpath);
        clickOnButton(hotel_a_xpath);
    }

    public void fillCityName(String cityName) {
        clickOnButton(city_input_id);
        fillTextField(city_input_xpath, cityName);
    }

    public void clickCityOption(String text) {
        getLiDropdownOption(city_ul_xpath, city_li_options_xpath, city_p_xpath, text);
    }

    public void checkDatePickerDisplayed() {
        if (checkWebElementIsDisplayed(datePickerContainer_div_className)) {
            System.out.println("Displayed");
        }

    }

    public void clickGuestRoomChildren() {
        clickOnButton(guest_div_xpath);
    }

    private boolean checkNumberOfAdultsChildren(int numberOdAdults, int numberOfChildren) {
        if (numberOdAdults + numberOfChildren <= 9) {
            return true;
        }
        return false;
    }

    public void setNumberOfAdults(int numberOfAdults, int numberOfChildren) {
        By adult_Li_xpath = By.cssSelector("li[data-cy='adults-" + numberOfAdults + "']");
        if (checkNumberOfAdultsChildren(numberOfAdults, numberOfChildren)) {
            if (numberOfAdults != 0) {
                clickOnButton(adult_Li_xpath);
            }
        }

    }

    public void setNumberOfChildren(int numberOfAdults, int numberOfChildren) {
        By children_Li_xpath = By.cssSelector("li[data-cy='children-" + numberOfChildren + "']");
        if (checkNumberOfAdultsChildren(numberOfAdults, numberOfChildren)) {
            clickOnButton(children_Li_xpath);
        }

    }

    private ArrayList getChildrenAge(JsonObject childrenAgeObject) {
        ArrayList childrenAgeList = new ArrayList();
        if (childrenAgeObject != null) {
            for (String key : childrenAgeObject.keySet()) {
                childrenAgeList.add(childrenAgeObject.get(key).getAsInt());

            }
        }
        return childrenAgeList;
    }

    public void fillChildrenAgeDropdown(JsonObject childrenAgeObject) {
        ArrayList childrenAgeAsList = getChildrenAge(childrenAgeObject);
        if (!childrenAgeAsList.isEmpty()) {
            for (int childAge = 0; childAge < childrenAgeAsList.size(); childAge++) {
                By children_age_select_cssSelector = By.cssSelector("select[id='" + childAge + "']");
                getSelectWebElement(children_age_select_cssSelector, String.valueOf(childrenAgeAsList.get(childAge)));
            }
        }

    }

    public void clickApplyGuests() {
        clickOnButton(guestApply_button_xpath);
    }

    public void clickTravelOption() {
        clickOnButton(travelFor_div_xpath);
    }

    public void clickTravelFor(String travelPurpose) {
        if (travelPurpose != null) {
            String travelPurposeString = CommonUtilities.capitalizeFirstLetter(travelPurpose);
            By travelPurpose_li_xpath = By.xpath("//li[@data-cy='travelFor-" + travelPurposeString + "']");
            clickOnButton(travelPurpose_li_xpath);
        }
    }

    public void quit() {
        quitBrowser();
    }
}
