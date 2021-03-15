package automation.bootcamp.makemytrip.tests;

import automation.bootcamp.makemytrip.boClasses.PassengerInfoClass;
import automation.bootcamp.makemytrip.dataProviders.PassengerInfoDataProvider;
import automation.bootcamp.makemytrip.pages.ReservationPage;
import automation.bootcamp.makemytrip.pages.SearchListingPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReservationPageTest {
    ReservationPage reservationPage = new ReservationPage();
    SearchListingPage searchListingPage;

    @BeforeTest
    public void launchBrowser() {
        reservationPage.launchBrowser("https://www.makemytrip.com/");
    }

    @Test(dataProvider = "PassengerInfoDataProvider", dataProviderClass = PassengerInfoDataProvider.class)
    public void assertOnSearchListingPageAfterFillingReservationInfo(PassengerInfoClass passengerInfoClass) {


        reservationPage.chooseIndianFromCountryList();
        reservationPage.getHotelCard();
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

    @Test(dataProvider = "PassengerInfoDataProvider", dataProviderClass = PassengerInfoDataProvider.class)
    public void assertOnTheSearchCriteriaOnSearchListingPage(PassengerInfoClass passengerInfoClass) {
        searchListingPage = reservationPage.getSearchListingPageObject();
        Assert.assertTrue(passengerInfoClass.getPassengerBookingInfoClass().getCityName().contains(searchListingPage.getCityValue()));
        Assert.assertTrue(passengerInfoClass.getPassengerBookingInfoClass().getCheckIn().contains(searchListingPage.getCheckInDateAsString(searchListingPage.getCheckInValue())));
        Assert.assertTrue(passengerInfoClass.getPassengerBookingInfoClass().getCheckOut().contains(searchListingPage.getCheckInDateAsString(searchListingPage.getCheckOutValue())));
        Assert.assertEquals(passengerInfoClass.getPassengerBookingInfoClass().getNumberOfRooms(), searchListingPage.getRoomCount());
        Assert.assertEquals(passengerInfoClass.getPassengerBookingInfoClass().getAdultsCount(), searchListingPage.getAdultCount());
        Assert.assertEquals(passengerInfoClass.getPassengerBookingInfoClass().getChildrenCount(), searchListingPage.getChildrenCount());

    }

    @AfterTest
    public void quitBrowser() {
        reservationPage.quit();
    }
}
