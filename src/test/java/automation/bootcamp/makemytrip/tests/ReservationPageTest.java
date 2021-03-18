package automation.bootcamp.makemytrip.tests;

import automation.bootcamp.makemytrip.boClasses.PassengerInfoClass;
import automation.bootcamp.makemytrip.boClasses.SearchFilterClass;
import automation.bootcamp.makemytrip.dataProviders.PassengerInfoDataProvider;
import automation.bootcamp.makemytrip.dataProviders.SearchFilterDataProvider;
import automation.bootcamp.makemytrip.pages.ReservationPage;
import automation.bootcamp.makemytrip.pages.SearchListingPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReservationPageTest {
    ReservationPage reservationPage = new ReservationPage();
    static SearchListingPage searchListingPage;

    @BeforeTest
    public void launchBrowser() {
        reservationPage.launchBrowser("https://www.makemytrip.com/");
    }

    @Test(dataProvider = "PassengerInfoDataProvider", dataProviderClass = PassengerInfoDataProvider.class)
    public void A_AssertOnTheSearchAListingPageAfterFillingReservationInfo(PassengerInfoClass passengerInfoClass) {
        reservationPage.chooseIndianFromCountryList();
        reservationPage.getHotelCard();
        reservationPage.clickCityName();
        reservationPage.fillCityName(passengerInfoClass.getPassengerBookingInfoClass().getCityName());
        reservationPage.clickCityOption(passengerInfoClass.getPassengerBookingInfoClass().getCityName());
        reservationPage.selectCheckInDate(passengerInfoClass.getPassengerBookingInfoClass().getCheckIn());
        reservationPage.selectCheckOutDate(passengerInfoClass.getPassengerBookingInfoClass().getCheckOut());
        reservationPage.clickGuestRoomChildren();
        reservationPage.setNumberOfAdults(passengerInfoClass.getPassengerBookingInfoClass().getAdultsCount(), passengerInfoClass.getPassengerBookingInfoClass().getChildrenCount());
        reservationPage.setNumberOfChildren(passengerInfoClass.getPassengerBookingInfoClass().getAdultsCount(), passengerInfoClass.getPassengerBookingInfoClass().getChildrenCount());
        reservationPage.fillChildrenAgeDropdown(passengerInfoClass.getPassengerBookingInfoClass().getChildrenAge());
        reservationPage.clickApplyGuests();
        reservationPage.clickTravelOption();
        reservationPage.clickTravelFor(passengerInfoClass.getPassengerBookingInfoClass().getTravellingPurpose());
        reservationPage.clickSearch();
        Assert.assertTrue(reservationPage.getNextPageTitle().contentEquals("MakeMyTrip"));
    }
    @BeforeTest
    public void getSearchListingPage(){
        searchListingPage = reservationPage.getSearchListingPageObject();
    }
    @Test(dataProvider = "PassengerInfoDataProvider", dataProviderClass = PassengerInfoDataProvider.class)
    public void B_AssertOnTheSearchCriteriaOnSearchListingPage(PassengerInfoClass passengerInfoClass) {
        searchListingPage = reservationPage.getSearchListingPageObject();
        Assert.assertTrue(passengerInfoClass.getPassengerBookingInfoClass().getCityName().contains(searchListingPage.getCityValue()));
        Assert.assertTrue(passengerInfoClass.getPassengerBookingInfoClass().getCheckIn().contains(searchListingPage.getCheckInDateAsString(searchListingPage.getCheckInValue())));
        Assert.assertTrue(passengerInfoClass.getPassengerBookingInfoClass().getCheckOut().contains(searchListingPage.getCheckInDateAsString(searchListingPage.getCheckOutValue())));
        Assert.assertEquals(passengerInfoClass.getPassengerBookingInfoClass().getNumberOfRooms(), searchListingPage.getRoomCount());
        Assert.assertEquals(passengerInfoClass.getPassengerBookingInfoClass().getAdultsCount(), searchListingPage.getAdultCount());
        Assert.assertEquals(passengerInfoClass.getPassengerBookingInfoClass().getChildrenCount(), searchListingPage.getChildrenCount());

    }

    @Test(dataProvider = "SearchFilterDataProvider",dataProviderClass = SearchFilterDataProvider.class)
    public void C_AssertOnTheSearchFilters(SearchFilterClass searchFilterClass){
        searchListingPage.clickOnCheckboxUserRating(searchFilterClass.getUserRating());
        searchListingPage.moverPriceRate(searchFilterClass.getxOffset());
        Assert.assertTrue(searchListingPage.getAppliedFilter());
        Assert.assertEquals(searchListingPage.getPriceRangeText(searchFilterClass.getPriceRange()),"5500");
        Assert.assertEquals(searchListingPage.getUserRatingText(searchFilterClass.getUserRating()),("4 & above (Very Good)"));

    }

//    @Test
//    public void assertOnTheSearchHotelName(){
//        searchListingPage.getTheRequiredHotelContainer();
//    }
//
    @AfterTest
    public void quitBrowser() {
        reservationPage.quit();
    }
}
