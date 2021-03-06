package automation.bootcamp.makemytrip.dataProviders;

import automation.bootcamp.makemytrip.boClasses.PassengerBookingInfoClass;
import automation.bootcamp.makemytrip.boClasses.PassengerInfoClass;
import automation.bootcamp.makemytrip.dataReaders.JsonReader;
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
            passengerInfoClass.setFirstName(passengerInfoList.get(i).get("firstName").toString());
            passengerInfoClass.setLastName(passengerInfoList.get(i).get("lastName").toString());
            passengerInfoClass.setAge(passengerInfoList.get(i).get("age").getAsInt());
            passengerInfoClass.setPhoneNumber(passengerInfoList.get(i).get("phoneNumber").toString());
            passengerBookingInfoClass.setCityName(passengerBookingObject.get("cityName").getAsString());
            passengerBookingInfoClass.setCheckIn(passengerBookingObject.get("checkInDate").toString());
            passengerBookingInfoClass.setCheckOut(passengerBookingObject.get("checkOutDate").toString());
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
