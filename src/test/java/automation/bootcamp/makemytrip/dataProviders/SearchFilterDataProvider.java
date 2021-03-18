package automation.bootcamp.makemytrip.dataProviders;
import automation.bootcamp.makemytrip.boClasses.SearchFilterClass;
import automation.bootcamp.makemytrip.dataReaders.JsonReader;
import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;

import java.util.List;

public class SearchFilterDataProvider {
    static List<JsonObject> searchFilterList = JsonReader.readJson("/Users/reemalim/Documents/Reem/AutomationBootCamp/FinalProject/src/test/resources/searchFilter.json");
//    static List<JsonObject> searchListingUrl = JsonReader.readJson("/Users/reemalim/Documents/Reem/AutomationBootCamp/FinalProject/src/test/resources/searchListingPageUrl.json");

    @DataProvider(name = "SearchFilterDataProvider")
    public static Object[][] getSearchFilterData() {

        List<JsonObject> objectList = searchFilterList;

        Object[][] object = new Object[objectList.size()][1];
        SearchFilterClass searchFilterClass = new SearchFilterClass();

        for (int i = 0; i < objectList.size(); i++) {

            JsonObject searchFilterObject = searchFilterList.get(i).getAsJsonObject();
//            JsonObject searchUrlObject = searchListingUrl.get(i).getAsJsonObject();
            searchFilterClass.setxOffset(searchFilterObject.get("xOffset").getAsInt());
            searchFilterClass.setPriceRange(searchFilterObject.get("pricePerNightRange").getAsString());
            searchFilterClass.setUserRating(searchFilterObject.get("userRating").getAsString());
//            searchFilterClass.setSearchListingUrl(searchUrlObject.get("searchListPageURL").getAsString());
            object[i][0] = searchFilterClass;

        }
        return object;
    }


}
