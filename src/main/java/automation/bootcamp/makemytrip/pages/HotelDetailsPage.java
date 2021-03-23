package automation.bootcamp.makemytrip.pages;

import automation.bootcamp.makemytrip.boClasses.PassengerInfoClass;
import automation.bootcamp.makemytrip.utilities.CommonUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HotelDetailsPage extends BasePage {
    /***
     * Might need this class when I complete the flow so wont delete this line nor the class itself
     */
//    RoomRecommendDetailsClass roomRecommendDetailsClass = new RoomRecommendDetailsClass();

    static WebElement recommendedLeftSide;
    static WebElement selectGuestColumn;
    static WebElement pricePerNightColumn;

    static List<String> guestOptionsStrings = new ArrayList<>();
    static List<WebElement> guestOptionsWebElements = new ArrayList<>();
    static List<String> adultChildString = new ArrayList<>();
    static List<String> adultString = new ArrayList<>();

    static int childrenCount = 0;
    static int adultCount = 0;


    By recommendedBlocks_div_xpath = By.xpath("//section[@id='RoomType']//div[contains(@class,'comboWrap')]");
    By recommendationLeftSide_div_class = By.xpath("//div[@class='left']");
    By recommendedTitle_div_xpath = By.xpath("//div[contains(@class,'comboTitle')]");
    By recommendedRoomRow_div_xpath = By.xpath("//child::div[contains(@class,'roomRow')]");
    /***
     * Room Selection container locators
     */
    By roomSelection_div_id = By.id("viewMoreRooms");
    By room_middleContainer_div_xpath = By.xpath("//div[@class='roomLeftCont']//div[@class='Middle']");
    By selectGuest_col_div_xpath = By.xpath("//div[contains(@class,'multiRoomRow makeFlex bdrBottom')]//div[@class='col3']");
    By pricePerNight_col_div_xpath = By.xpath("//div[contains(@class,'multiRoomRow makeFlex bdrBottom')]//div[@class='col4']");
    By addRoom_button_xpath = By.xpath("//a[contains(@class,'btnAddRoom')]");
    By guestDropdownHeader_div_xpath = By.className("ddWrapper");
    By roomHeader_scrollTo_className = By.className("ownChoice");
    By guestOptions_li_xpath = By.xpath("//li[@class='ddListItem']");
    By guestOptions_p_xpath = By.cssSelector("p:nth-child(1)");

    public HotelDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    /***
     * Method to return the first block for recommendations for the hotels that have this block BUT for some reason even
     * if I have the first block it prints all the details for all the displayed on the page although I access those printed
     * text from the returned object from here!
     * @return first recommended block to print its details
     */
    public WebElement getFirstRecommendedBlock() {
        try {
            if (getWebElements(recommendedBlocks_div_xpath).isEmpty()) {
                System.out.println("No Recommendation blocks for this hotel!");
                return null;

            } else {
                List<WebElement> recommendedBlocks = getWebElements(recommendedBlocks_div_xpath);
                if (recommendedBlocks.size() >= 1) {
                    System.out.println(recommendedBlocks.get(0));
                    return recommendedBlocks.get(0);
                }
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Recommendation element doesnot exist for this hotel!!");
        }
        return null;
    }

    /***
     * Getting the left side container for the FIRST recommended block to print its details
     * @return
     */
    private WebElement getFirstRecommendedBlockLeftSide() {

        if (getWebElements(recommendedBlocks_div_xpath).isEmpty()) {
            System.out.println("No recommended block to get from it the info required!");
            return null;
        } else {
            System.out.println("RecommendedBlocks : " + getWebElements(recommendedBlocks_div_xpath).size());
            List<WebElement> recommendedBlocks = getWebElements(recommendedBlocks_div_xpath);
            if (!recommendedBlocks.isEmpty()) {
                if (recommendedBlocks.size() >= 1) {
                    WebElement recommendedFirstBlock = recommendedBlocks.get(0);
                    System.out.println(recommendedFirstBlock.findElement(By.className("left")).findElements(By.xpath("//div[contains(@class,'roomRow')]")).size());
                    System.out.println("reem");

                    return recommendedLeftSide = getWebElementFromParent(recommendedFirstBlock, recommendationLeftSide_div_class);
                }
            }
        }
        return null;
    }

    /***
     * Method to return the recommended rooms in the first recommended block BUT actually now it prints all the details
     * for all the displayed block!
     * @return List of rows for the room represented on the first recommended block
     */
    private List<WebElement> getRecommendedRoomRows() {
        List<WebElement> roomRowsElements = getWebElementsFromParent(getFirstRecommendedBlockLeftSide(), recommendedRoomRow_div_xpath);
        return roomRowsElements;
    }

    /***
     * Method to print all the details for the rooms in the recommended first block BUT it prints all the details!
     */
    public void getAllInfoForARoomOption() {
        getRecommendedBlockTitle();
        List<WebElement> roomRows = getRecommendedRoomRows();
        System.out.println(roomRows.size());
        if (!roomRows.isEmpty()) {
            for (WebElement room : roomRows) {
                System.out.println("Recommendation room details: ");
                System.out.println(room.getText());
                System.out.println("------------------------------------");
            }
        }
    }

    /***
     * Method prints the title for the first recommended block and actually this one prints correctly ONLY the title for
     * the first block
     */
    private void getRecommendedBlockTitle() {
        WebElement recommendedLeftBlock = getWebElementFromParent(getFirstRecommendedBlock(), recommendedTitle_div_xpath);
        System.out.println("Title for the First Recommendation Block: " + recommendedLeftBlock.getText());
    }

    /***
     * Method returns all the middle containers for the Hotel list of rooms where those container have the 'PricePerNight' and
     * 'Select Guest' columns
     * @return
     */
    private List<WebElement> getRoomMiddleContainers() {
        waitUntilElementIsVisibleInPage(By.xpath("//div[@class='hotelHeader']"));
        try {
            WebElement roomParent = getWebElement(roomSelection_div_id);
            List<WebElement> roomMiddleContainers = getWebElementsFromParent(roomParent, room_middleContainer_div_xpath);
            return roomMiddleContainers;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find this element in the Dom; Apparently this hotel doesnt have available rooms" + e);
        }

    }

    /***
     * Method gets the two objects for the 'PricePerNight' and 'Select Guest' and set them to a static defined objects;
     * And returns the first middle container
     * @param roomMiddleContainers, container for the first option for rooms
     * @return
     */
    private WebElement getGuestAndPriceObjectForFirstRoom(List<WebElement> roomMiddleContainers) {
        List<WebElement> selectGuestColumnList;
        List<WebElement> pricePerNightColumnList;
        if (!roomMiddleContainers.isEmpty()) {

            WebElement middleContainerForFirstRoom = roomMiddleContainers.get(0);
            selectGuestColumnList = getWebElementsFromParent(middleContainerForFirstRoom, selectGuest_col_div_xpath);
            pricePerNightColumnList = getWebElementsFromParent(middleContainerForFirstRoom, pricePerNight_col_div_xpath);
            selectGuestColumn = selectGuestColumnList.get(0);
            pricePerNightColumn = pricePerNightColumnList.get(0);
            return middleContainerForFirstRoom;

        } else {
            throw new NoSuchElementException("This hotel doesnt have 'Rooms' section based on the filters applied!");
        }
    }

    /***
     * Method gets the dropdown objects for the first rooms options and set a static variable with and set as well their strings
     * in to another list of stringd
     * @param middleContainerForFirstRoom Webelement from which we will get the displayed drop downs for the guests
     */
    private void getGuestDropdownOptions(WebElement middleContainerForFirstRoom) {

        if (middleContainerForFirstRoom != null) {
            List<WebElement> guestHeader = getWebElementsFromParent(middleContainerForFirstRoom, guestDropdownHeader_div_xpath);
            WebElement guestFirstHeader = guestHeader.get(0);
            if (guestFirstHeader != null) {
                scrollThroughTheBrowser(roomHeader_scrollTo_className);
                guestFirstHeader.findElement(By.className("ddHeaderTitle")).click();
                List<WebElement> guestLis = getWebElementsFromParent(guestFirstHeader, guestOptions_li_xpath);

                if (!guestLis.isEmpty()) {
                    for (WebElement guestOption : guestLis) {
                        WebElement webElement;
                        webElement = getWebElementFromParent(guestOption, guestOptions_p_xpath);
                        guestOptionsWebElements.add(webElement);
                        guestOptionsStrings.add(webElement.getText());
                    }
                }
            }
        }
    }

    /***
     * Method to get the add room button to be clicked
     * @return the button object to be clicked
     */
    public WebElement getRoomButtonToClick() {
        try {
            WebElement addRoomButton = getWebElementFromParent(pricePerNightColumn, addRoom_button_xpath);
            return addRoomButton;
        } catch (NullPointerException e) {
            e.printStackTrace();
            getRoomButtonToClick();
        }
        return null;
    }

    /***
     * Method get the options that contains Adults+Children and set a list of string with AND Adults only and set another
     * list with
     */
    private void getChildAdultListFromDropdown() {
        if (guestOptionsStrings.size() != 0) {
            for (String guestDropdownString : guestOptionsStrings) {
                if (guestDropdownString.contains("Child") || guestDropdownString.contains("Children")) {
                    adultChildString.add(guestDropdownString);
                } else {
                    adultString.add(guestDropdownString);
                }
            }
        }
    }

    /***
     * Method to loop over the guests dropdwon objects and call a method to click the option required
     * @param adultChildString
     */
    private void loopOverAdultOrChildrenCount(List<String> adultChildString) {
        String[] str;
        for (String adultChildStr : adultChildString) {
            str = CommonUtilities.splitString(adultChildStr, " ");
            if (str.length <= 1) {
                System.out.println(str[0]);
//                System.out.println(str[2]);
                checkOptionThatFitsAndChooseRoom(adultChildStr, str[0], "");
                System.out.println("---------");
            } else if (str.length > 4) {
                System.out.println(str[0]);
                System.out.println(str[2]);
                checkOptionThatFitsAndChooseRoom(adultChildStr, str[0], str[2]);
                System.out.println("---------");
            }
        }
    }

    /***
     *
     */
    public void gettingApplicableRooms() {
        if (childrenCount != 0 || adultCount != 0) {
            if (adultChildString.size() != 0) {
                System.out.println("Adult and Children List : " + adultChildString);
                loopOverAdultOrChildrenCount(adultChildString);
            } else {
                System.out.println("Adult List ONLY : " + adultString);
                loopOverAdultOrChildrenCount(adultString);
                System.out.println("Here I should handle when the options are only Adults!");
            }
        }
    }

    /***
     *
     * @param passengerInfoClass
     */
    public void comboOfMethodsCalling(PassengerInfoClass passengerInfoClass) {
        List<WebElement> roomMiddleContainers = getRoomMiddleContainers();
        WebElement middleContainerForFirstRoom = getGuestAndPriceObjectForFirstRoom(roomMiddleContainers);
        getGuestDropdownOptions(middleContainerForFirstRoom);
        getChildAdultListFromDropdown();
        setChildrenAndAdultCountFromPassenger(passengerInfoClass);
        gettingApplicableRooms();

    }

    /***
     *
     * @param passengerInfoClass
     */
    public void setChildrenAndAdultCountFromPassenger(PassengerInfoClass passengerInfoClass) {
        childrenCount = passengerInfoClass.getPassengerBookingInfoClass().getChildrenCount();
        adultCount = passengerInfoClass.getPassengerBookingInfoClass().getAdultsCount();

    }

    /***
     *
     * @param adultChildStr
     * @param adultCountPerRoom
     * @param childCountPerRoom
     */
    public void checkOptionThatFitsAndChooseRoom(String adultChildStr, String adultCountPerRoom, String childCountPerRoom) {

        if (Integer.valueOf(adultCountPerRoom) <= adultCount && Integer.valueOf(childCountPerRoom) <= childrenCount || Integer.valueOf(adultCountPerRoom) <= adultCount) {
            System.out.println("This room is awesome we will take it!!");
            getRoomOptionToSelect(adultChildStr, Integer.valueOf(adultCountPerRoom), Integer.valueOf(childCountPerRoom));

        } else {
            System.out.println("PPl wont fit here!");
        }
    }

    /***
     *
     * @param adultChildStr
     * @param adultCountPerRoom
     * @param childCountPerRoom
     */
    private void getRoomOptionToSelect(String adultChildStr, int adultCountPerRoom, int childCountPerRoom) {
        if (!guestOptionsWebElements.isEmpty()) {
            for (WebElement guestDropDownOption : guestOptionsWebElements) {
                guestDropDownOption.click();
                System.out.println("Clicked once!!");
                subtractChildAdultFromTotalCount(adultChildStr, adultCountPerRoom, childCountPerRoom);
                //Will break here as logic isn't correct so I added one room
                break;
            }
        }
    }

    /***
     *
     * @param adultChildStr
     * @param adultCountPerRoom
     * @param childCountPerRoom
     */
    private void subtractChildAdultFromTotalCount(String adultChildStr, int adultCountPerRoom, int childCountPerRoom) {
        WebElement priceButton = getRoomButtonToClick();
        priceButton.click();

        adultCount = adultCount - adultCountPerRoom;
        childrenCount = childrenCount - childCountPerRoom;
        if (adultCount != 0 && childrenCount != 0) {
            System.out.println("I should proceed BUT the logic sucks!!");
//            checkOptionThatFitsAndChooseRoom(adultChildStr,String.valueOf(adultCount),String.valueOf(childrenCount));
        }
    }
}
