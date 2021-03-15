package automation.bootcamp.makemytrip.boClasses;

public class SearchFilterClass {


    private int xOffset;
    private String userRating;
    private String pricePerNightRange;
    private String searchListingUrl;

    public String getSearchListingUrl() {
        return searchListingUrl;
    }

    public void setSearchListingUrl(String searchListingUrl) {
        this.searchListingUrl = searchListingUrl;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getPriceRange() {
        return pricePerNightRange;
    }

    public void setPriceRange(String priceRange) {
        this.pricePerNightRange = priceRange;
    }

    @Override
    public String toString() {
        return "SearchFilterClass{" +
                "xOffset=" + xOffset +
                ", userRating='" + userRating + '\'' +
                ", pricePerNightRange='" + pricePerNightRange + '\'' +
                ", searchListingUrl='" + searchListingUrl + '\'' +
                '}';
    }


}
