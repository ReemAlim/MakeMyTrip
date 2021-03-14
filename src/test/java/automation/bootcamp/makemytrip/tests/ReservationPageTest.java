package automation.bootcamp.makemytrip.tests;

import automation.bootcamp.makemytrip.boClasses.PassengerInfoClass;
import automation.bootcamp.makemytrip.dataProviders.PassengerInfoDataProvider;
import automation.bootcamp.makemytrip.pages.ReservationPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReservationPageTest {
    ReservationPage reservationPage = new ReservationPage();
    @BeforeTest
    public void launchBrowser(){
        reservationPage.launchBrowser();
    }
    @Test(dataProvider = "PassengerInfoDataProvider",dataProviderClass = PassengerInfoDataProvider.class)
    public void fillReservationInfo(PassengerInfoClass passengerInfoClass){
        reservationPage.getHotelCard();
        reservationPage.fillCityName(passengerInfoClass.getPassengerBookingInfoClass().getCityName());
        reservationPage.clickCityOption(passengerInfoClass.getPassengerBookingInfoClass().getCityName());
        reservationPage.selectCheckInDate(passengerInfoClass.getPassengerBookingInfoClass().getCheckIn());
        reservationPage.selectCheckOutDate(passengerInfoClass.getPassengerBookingInfoClass().getCheckOut());
        reservationPage.clickGuestRoomChildren();
        reservationPage.setNumberOfAdults(passengerInfoClass.getPassengerBookingInfoClass().getAdultsCount(),passengerInfoClass.getPassengerBookingInfoClass().getChildrenCount());
        reservationPage.setNumberOfChildren(passengerInfoClass.getPassengerBookingInfoClass().getAdultsCount(),passengerInfoClass.getPassengerBookingInfoClass().getChildrenCount());
        reservationPage.fillChildrenAgeDropdown(passengerInfoClass.getPassengerBookingInfoClass().getChildrenAge());
        reservationPage.clickApplyGuests();
        reservationPage.clickTravelOption();
        reservationPage.clickTravelFor(passengerInfoClass.getPassengerBookingInfoClass().getTravellingPurpose());
        reservationPage.clickSearch();
        Assert.assertTrue(reservationPage.getNextPageTitle().contentEquals("MakeMyTrip"));
    }

    @AfterTest
    public void quitBrowser(){
        reservationPage.quit();
    }
}
