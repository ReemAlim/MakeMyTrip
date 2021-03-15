package automation.bootcamp.makemytrip.pages;

import automation.bootcamp.makemytrip.utilities.CommonUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.DocFlavor;
import java.util.List;

public class SearchListingPage extends BasePage{
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
    By scrollToElement_div_id = By.id("hlistpg_fr_star_category");

    By appliedFilter_div_class = By.className("appliedFiltersContainer");
    By appliedFilter_options_xpath = By.xpath("//ul[@class='appliedFilters']//span[@class='latoBold']");
    String userRatingText = "4 & above (Very Good)";
    int xOffsetForPrice = 74;
    String pricePerNightRange = "1500";


    public SearchListingPage(){}
    public SearchListingPage(WebDriver driver){
        super(driver);
    }

    public String getCityValue(){
        String cityValue = getTextForValueAttribute(city_input_id);
        if(cityValue.contains("...")){
            return  cityValue.substring(0, cityValue.length()-3);
        }

        return cityValue;
    }
    public String getCheckInValue(){
        return getTextForValueAttribute(checkIn_input_id);
    }
    public String getCheckInDateAsString(String dateInput){
        String date = CommonUtilities.splitString(dateInput,", ")[1].replaceAll(" ","/");
        return CommonUtilities.getDateInNumbers(date);
    }

    public String getCheckOutValue(){
        return getTextForValueAttribute(checkOut_input_id);
    }

    private String[] getRoomGuestValue(){
        if(getTextForValueAttribute(guest_input_id)!= null){
            String[] guestRoomArray = CommonUtilities.splitString(getTextForValueAttribute(guest_input_id),",");
            return guestRoomArray;
        }
        return null;
    }

    public int getRoomCount(){
        return Integer.valueOf(getRoomGuestValue()[0].trim().split(" ")[0]);
    }

    public int getAdultCount(){
        return Integer.valueOf(getRoomGuestValue()[1].trim().split(" ")[0]);
    }

    public int getChildrenCount(){
        return Integer.valueOf(getRoomGuestValue()[2].trim().split(" ")[0]);
    }


    public void clickOnCheckboxUserRating(){
        WebDriverWait wait = new WebDriverWait(driver, 40);
        List<WebElement> userRatingSpanElements = getWebElements(userRating_span_xpath);
        scrollThroughTheBrowser(scrollToElement_div_id);
        if(!userRatingSpanElements.isEmpty()){
            for(WebElement ratingElement : userRatingSpanElements){
                if(ratingElement.getText().contains(userRatingText))
                {
                        ratingElement.click();
//                    }
                }
            }
        }
    }
    private List<WebElement> getRangeSliders(){
        return getWebElements(pricePerNight_div_slider_xpath);
    }

    public void moverPriceRate(int xOffsetForPrice){
        List<WebElement> webElements = getRangeSliders();
        if(!webElements.isEmpty()){
            moveSliderToEnd(getRangeSliders().get(0),xOffsetForPrice);
        }

    }

    public boolean getAppliedFilter(){
        if(getWebElement(appliedFilter_div_class).isDisplayed()){
            return true;
        }
        return false;
    }

    private List<WebElement> getAppliedFilterOptions(){
        if(getAppliedFilter()){
            return getWebElements(appliedFilter_options_xpath);
        }
        return null;
    }

    public String getUserRatingText(String userRatingText){
        if(!getAppliedFilterOptions().isEmpty()){
            for(WebElement webElement : getAppliedFilterOptions()){
                if(webElement.getText().equalsIgnoreCase(userRatingText)){
                    return userRatingText;
                }
            }
            return "User rating text isn't displayed";

        }
        return "Applied Filters is returned EMPTY!!";
    }

    public String getPriceRangeText(String pricePerNightRange){
        if(!getAppliedFilterOptions().isEmpty()){
            for(WebElement webElement : getAppliedFilterOptions()){
                if(webElement.getText().contains(pricePerNightRange)){
                    return pricePerNightRange;
                }
            }
            return "User rating text isn't displayed";
        }
        return "Applied Filters is returned EMPTY!!";
    }
}
