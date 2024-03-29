package automation.bootcamp.makemytrip.dataProviders;

import automation.bootcamp.makemytrip.boClasses.PassengerBookingInfoClass;
import automation.bootcamp.makemytrip.boClasses.PassengerInfoClass;
import automation.bootcamp.makemytrip.dataReaders.*;
import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;

import java.util.List;

public class PassengerInfoDataProvider {
    static List<JsonObject> passengerInfoList = JsonReader.readJson("/Users/reemalim/Documents/Reem/AutomationBootCamp/FinalProject/src/test/resources/passengerInfo.json");

    @DataProvider(name = "PassengerInfoDataProvider")
    public static Object[][] getPassengerInfoData() {

        List<JsonObject> objectList = passengerInfoList;
        Object[][] object = new Object[objectList.size()][1];
        PassengerInfoClass passengerInfoClass;
        PassengerBookingInfoClass passengerBookingInfoClass;

        for (int i = 0; i < objectList.size(); i++) {

            passengerInfoClass = new PassengerInfoClass();
            passengerBookingInfoClass = new PassengerBookingInfoClass();
            JsonObject passengerBookingObject = passengerInfoList.get(i).get("passengerBookingInfo").getAsJsonObject();
            passengerInfoClass.setFirstName(passengerInfoList.get(i).get("firstName").getAsString());
            passengerInfoClass.setLastName(passengerInfoList.get(i).get("lastName").getAsString());
            passengerInfoClass.setAge(passengerInfoList.get(i).get("age").getAsInt());
            passengerInfoClass.setPhoneNumber(passengerInfoList.get(i).get("phoneNumber").getAsString());
            passengerBookingInfoClass.setCityName(passengerBookingObject.get("cityName").getAsString());
            passengerBookingInfoClass.setCheckIn(passengerBookingObject.get("checkInDate").getAsString());
            passengerBookingInfoClass.setCheckOut(passengerBookingObject.get("checkOutDate").getAsString());
            passengerBookingInfoClass.setAdultsCount(passengerBookingObject.get("numberOfAdults").getAsInt());
            passengerBookingInfoClass.setChildrenCount(passengerBookingObject.get("numberOfChildren").getAsInt());
            passengerBookingInfoClass.setNumberOfRooms(passengerBookingObject.get("numberOfRooms").getAsInt());
            passengerBookingInfoClass.setChildrenAge(passengerBookingObject.get("childrenAge").getAsJsonObject());
            passengerBookingInfoClass.setTravellingPurpose(passengerBookingObject.get("travellingFor").getAsString());
            passengerInfoClass.setPassengerBookingInfoClass(passengerBookingInfoClass);
            object[i][0] = passengerInfoClass;
        }
        return object;
    }

}
