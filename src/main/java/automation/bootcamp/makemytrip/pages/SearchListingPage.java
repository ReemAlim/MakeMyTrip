package automation.bootcamp.makemytrip.pages;

import automation.bootcamp.makemytrip.utilities.CommonUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchListingPage extends BasePage {
    /***
     * Search Listing criteria areas' locators
     */
    By city_input_id = By.id("city");
    By checkIn_input_id = By.id("checkin");
    By checkOut_input_id = By.id("checkout");
    By guest_input_id = By.id("guest");
    /***
     * Search Filter locators
     */
    By pricePerNight_div_slider_xpath = By.xpath("//div[@class='input-range__slider']");
    By userRating_span_xpath = By.xpath("//div[@id='hlistpg_fr_user_rating']//ul[@class='filterList']//span[@class='checkmarkOuter']");
    By propertyType_span_xpath = By.xpath("//div[@id='hlistpg_fr_property_types']//ul[@class='filterList']//span[@class='checkmarkOuter']");
    By scrollToElement_div_id = By.id("hlistpg_fr_user_rating");
    By scrollToElement_flaky_div_id = By.id("USER_RATING");
//    By scrollBackUp_div_id = By.id("hlistpg_fr_popular_filters");
    By scrollBackUp_div_id = By.xpath("//div[contains(@class,'filterWrap')]");
    By scrollToPropertyType_div_id = By.id("hlistpg_fr_property_types");

    By appliedFilter_div_class = By.className("appliedFiltersContainer");
    By appliedFilter_options_xpath = By.xpath("//ul[@class='appliedFilters']//span[@class='latoBold']");

    /***
     * Locators for Hotel Listing wrapper
     */
    By hotelListingContainer_div_className = By.className("listingWrap");
//    By hotelListingContainer_div_className = By.className("infinite-scroll-component ");
    //    By hotelListingContainer_div_xpath = By.xpath("//div[@id='hotelListingContainer']//div[contains(@class,'listingRow')]");
    By hotelListingContainer_div_xpath = By.xpath("//div[@class='listingRowOuter']");
//    By hotelListingContainer_div_xpath = By.id("hotelListingContainer");
    By hotelName_xpath = By.id("hlistpg_hotel_name");

    HotelDetailsPage hotelDetailsPage;
    static String mainWindowId;


    public SearchListingPage(WebDriver driver) {
        super(driver);
    }

    public String getCityValue() {
        String cityValue = getTextForValueAttribute(city_input_id);
        if (cityValue.contains("...")) {
            return cityValue.substring(0, cityValue.length() - 3);
        }

        return cityValue;
    }

    public String getCheckInValue() {
        return getTextForValueAttribute(checkIn_input_id);
    }

    public String getCheckInDateAsString(String dateInput) {
        System.out.println("Date: " + dateInput);
        String date = CommonUtilities.splitString(dateInput, ", ")[1].replaceAll(" ", "/");
        System.out.println("Date Extracted: " + date);
        return CommonUtilities.getDateInNumbers(date);
    }

    public String getCheckOutValue() {
        return getTextForValueAttribute(checkOut_input_id);
    }

    private String[] getRoomGuestValue() {
        if (getTextForValueAttribute(guest_input_id) != null) {
            String[] guestRoomArray = CommonUtilities.splitString(getTextForValueAttribute(guest_input_id), ",");
            return guestRoomArray;
        }
        return null;
    }

    public int getRoomCount() {
        return Integer.valueOf(getRoomGuestValue()[0].trim().split(" ")[0]);
    }

    public int getAdultCount() {
        return Integer.valueOf(getRoomGuestValue()[1].trim().split(" ")[0]);
    }

    public int getChildrenCount() {
        return Integer.valueOf(getRoomGuestValue()[2].trim().split(" ")[0]);
    }


    public void clickOnCheckboxUserRating(String userRatingText) {
        waitForPageLoad();
        WebElement pageElement = waitUntilElementIsVisibleInPage(By.xpath("//div[@class='listingWrap']"));
        if (pageElement.isDisplayed()) {

            WebElement ratingContainer = scrollThroughTheBrowser(scrollToElement_div_id);
            if (ratingContainer != null) {
                List<WebElement> userRatingSpanElements = getWebElementsFromParent(ratingContainer, userRating_span_xpath);
                if (!userRatingSpanElements.isEmpty()) {
                    for (WebElement ratingElement : userRatingSpanElements) {

                        if (ratingElement.getText().equalsIgnoreCase(userRatingText)) {
                            ratingElement.click();
                        }
                    }
                }
            }
        }


    }

    private List<WebElement> getRangeSliders() {
        return getWebElements(pricePerNight_div_slider_xpath);
    }

    public void moverPriceRate(int xOffsetForPrice) {
        scrollThroughTheBrowser(scrollBackUp_div_id);
        List<WebElement> webElements = getRangeSliders();

        System.out.println("Location: " + webElements.get(0).getLocation());
        if (!webElements.isEmpty()) {
            System.out.println("Location 1: " + webElements.get(0).getLocation());
            moveSliderToEnd(webElements.get(0), xOffsetForPrice);
        }

    }

    public boolean getAppliedFilter() {
        if (getWebElement(appliedFilter_div_class).isDisplayed()) {
            return true;
        }
        return false;
    }

    private List<WebElement> getAppliedFilterOptions() {
        if (getAppliedFilter()) {
            return getWebElements(appliedFilter_options_xpath);
        }
        return null;
    }

    public String getUserRatingText(String userRatingText) {
        if (!getAppliedFilterOptions().isEmpty()) {
            for (WebElement webElement : getAppliedFilterOptions()) {
                if (webElement.getText().equalsIgnoreCase(userRatingText)) {
                    return userRatingText;
                }
            }
            return "User rating text isn't displayed";

        }
        return "Applied Filters is returned EMPTY!!";
    }

    public String getPriceRangeText(String pricePerNightRange) {
        if (!getAppliedFilterOptions().isEmpty()) {
            for (WebElement webElement : getAppliedFilterOptions()) {
                if (webElement.getText().contains(pricePerNightRange)) {
                    return pricePerNightRange;
                }
            }
            return "User rating text isn't displayed";
        }
        return "Applied Filters is returned EMPTY!!";
    }

    public void getTheRequiredHotelContainer() {
        waitForPageLoad();
        WebElement hotelsContainer = waitUntilElementIsVisibleInPage(hotelListingContainer_div_className);

        if (hotelsContainer.isDisplayed()) {
            List<WebElement> hotelContainersList = getWebElements(hotelListingContainer_div_xpath);
            if (!hotelContainersList.isEmpty()) {
                WebElement hotelElementRequired = getTheRequiredHotelName(hotelContainersList);
                hotelElementRequired.click();
            } else {
                throw new NullPointerException("The hotel List is empty!");
            }

        }

    }

    private WebElement getTheRequiredHotelName(List<WebElement> hotelElementsList) {
        if (hotelElementsList.size() <= 5) {
            return hotelElementsList.get(hotelElementsList.size() - 1);
//            return hotelElementsList.get(hotelElementsList.size() - 2);
        } else {
            WebElement hotelElementRequired = hotelElementsList.get(4).findElement(hotelName_xpath);
//            WebElement hotelElementRequired = hotelElementsList.get(3).findElement(By.id("hlistpg_hotel_name"));
            return hotelElementRequired;
        }
    }

    public String getCurrentWindow() {
        System.out.println(getCurrentWindowId(this.driver));
        return getCurrentWindowId(this.driver);
    }

    public WebDriver getHotelDetailsDriver() {
        mainWindowId = getCurrentWindow();
        WebDriver currentDriver = switchToRequiredTab(mainWindowId);
        return currentDriver;
    }

    public String getHotelDetailsTabActive() {

        mainWindowId = getCurrentWindow();
        WebDriver currentDriver = getHotelDetailsDriver();
        return currentDriver.getCurrentUrl();
    }

    public String getHotelUrl(){
        return getHotelDetailsTabActive();
    }


    public HotelDetailsPage getHotelDetailsPageObject() {
        hotelDetailsPage = new HotelDetailsPage(this.driver);
        return hotelDetailsPage;
    }

}
