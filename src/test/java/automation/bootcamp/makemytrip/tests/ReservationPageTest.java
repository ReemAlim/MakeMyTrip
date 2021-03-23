package automation.bootcamp.makemytrip.tests;

import automation.bootcamp.makemytrip.boClasses.PassengerInfoClass;
import automation.bootcamp.makemytrip.boClasses.SearchFilterClass;
import automation.bootcamp.makemytrip.dataProviders.PassengerInfoDataProvider;
import automation.bootcamp.makemytrip.dataProviders.SearchFilterDataProvider;
import automation.bootcamp.makemytrip.dataReaders.PropertyReader;
import automation.bootcamp.makemytrip.pages.HotelDetailsPage;
import automation.bootcamp.makemytrip.pages.ReservationPage;
import automation.bootcamp.makemytrip.pages.SearchListingPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReservationPageTest {

    ReservationPage reservationPage = new ReservationPage();
    private String url = PropertyReader.getConfigProperty("baseUrl");
    static SearchListingPage searchListingPage;
    static HotelDetailsPage hotelDetailsPage;

    @BeforeTest
    public void launchBrowser() {
        reservationPage.openPage(url);
    }

    @Test(dataProvider = "PassengerInfoDataProvider", dataProviderClass = PassengerInfoDataProvider.class)
    public void A_AssertOnTheSearchAListingPageAfterFillingReservationInfo(PassengerInfoClass passengerInfoClass) {
        reservationPage.loadPageIgnoringLogin();
        reservationPage.fillInCityName(passengerInfoClass.getPassengerBookingInfoClass().getCityName());
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
        Assert.assertTrue(reservationPage.getNextPageUrl().contains("hotels/hotel-listing/"));
    }

    @BeforeTest
    public void getSearchListingPage() {
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

    @Test(dataProvider = "SearchFilterDataProvider", dataProviderClass = SearchFilterDataProvider.class)
    public void C_AssertOnTheSearchFilters(SearchFilterClass searchFilterClass) {
//        searchListingPage.moverPriceRate(searchFilterClass.getxOffset());
        searchListingPage.clickOnCheckboxUserRating(searchFilterClass.getUserRating());
//
        Assert.assertTrue(searchListingPage.getAppliedFilter());
//        Assert.assertEquals(searchListingPage.getPriceRangeText(searchFilterClass.getPriceRange()),"3000");
        Assert.assertEquals(searchListingPage.getUserRatingText(searchFilterClass.getUserRating()), ("4 & above (Very Good)"));
    }

    @Test
    public void D_assertOnTheSearchHotelName() {
        searchListingPage.getTheRequiredHotelContainer();
        Assert.assertTrue(searchListingPage.getHotelUrl().contains("/hotels/hotel-details/"));
    }

    @Test(dataProvider = "PassengerInfoDataProvider", dataProviderClass = PassengerInfoDataProvider.class)
    public void E_assertOnHotelDetails(PassengerInfoClass passengerInfoClass) {
        hotelDetailsPage = searchListingPage.getHotelDetailsPageObject();
        hotelDetailsPage.getFirstRecommendedBlock();
        hotelDetailsPage.getAllInfoForARoomOption();
        hotelDetailsPage.comboOfMethodsCalling(passengerInfoClass);
    }

    @AfterTest
    public void quitBrowser() {
        reservationPage.closeBrowser();
    }
}
