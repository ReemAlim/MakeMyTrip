package automation.bootcamp.makemytrip.boClasses;

import com.google.gson.JsonObject;

public class PassengerBookingInfoClass {
    private int childrenCount;
    private int adultsCount;
    private int numberOfRooms;
    private String cityName;
    private String checkIn;
    private String checkOut;
    private String travellingPurpose;
    private JsonObject childrenAge;


    public JsonObject getChildrenAge() {
        return childrenAge;
    }

    public void setChildrenAge(JsonObject childrenAge) {
        this.childrenAge = childrenAge;
    }

    public String getTravellingPurpose() {
        return travellingPurpose;
    }

    public void setTravellingPurpose(String travellingPurpose) {
        this.travellingPurpose = travellingPurpose;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public int getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(int adultsCount) {
        this.adultsCount = adultsCount;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "PassengerBookingInfoClass{" +
                "childrenCount=" + childrenCount +
                ", adultsCount=" + adultsCount +
                ", numberOfRooms=" + numberOfRooms +
                ", cityName='" + cityName + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", travellingPurposeEnum=" + travellingPurpose +
                ", childrenAge=" + childrenAge +
                '}';
    }
}
