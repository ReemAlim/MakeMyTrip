package automation.bootcamp.makemytrip.tests;

import automation.bootcamp.makemytrip.boClasses.SearchFilterClass;
import automation.bootcamp.makemytrip.dataProviders.SearchFilterDataProvider;
import automation.bootcamp.makemytrip.pages.SearchListingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchListingPageTest {

    SearchListingPage searchListingPage = new SearchListingPage();

//    @BeforeTest
//    public void launchBrowser() {
//
//    }
    @Test(dataProvider = "SearchFilterDataProvider",dataProviderClass = SearchFilterDataProvider.class)
    public void assertOnSearchCriteria(SearchFilterClass searchFilterClass){
        System.out.println(searchFilterClass);
        searchListingPage.launchBrowser(searchFilterClass.getSearchListingUrl());
        searchListingPage.moverPriceRate(searchFilterClass.getxOffset());
        searchListingPage.clickOnCheckboxUserRating();
        Assert.assertTrue(searchListingPage.getAppliedFilter());
        Assert.assertEquals(searchListingPage.getPriceRangeText(searchFilterClass.getPriceRange()),"2000");
        Assert.assertEquals(searchListingPage.getUserRatingText(searchFilterClass.getUserRating()),("4 & above (Very Good)"));


    }

}
