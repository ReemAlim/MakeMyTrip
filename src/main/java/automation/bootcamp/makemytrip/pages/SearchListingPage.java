package automation.bootcamp.makemytrip.pages;

import automation.bootcamp.makemytrip.utilities.CommonUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchListingPage extends BasePage{
    /***
     * Search Listing criteria areas' locators
     */
    By city_input_id = By.id("city");
    By checkIn_input_id = By.id("checkin");
    By checkOut_input_id = By.id("checkout");
    By guest_input_id = By.id("guest");

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
}
