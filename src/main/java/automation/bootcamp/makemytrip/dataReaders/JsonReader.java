package automation.bootcamp.makemytrip.dataReaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.json.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static List<JsonObject> readJson(String jsonFilePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(jsonFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonElement element = JsonParser.parseReader(reader);

        JsonArray array = element.getAsJsonArray();

        List<JsonObject> objectList = new ArrayList<JsonObject>();
        for (Object o : array) {
            JsonObject jsonObject = (JsonObject) o;

            objectList.add(jsonObject);
        }


        return objectList;
    }
//
//    public static void main(String[] args) {
//        List<JsonObject> userInfoList = readJson("/Users/reemalim/Documents/Reem/AutomationBootCamp/FinalProject/src/test/resources/passengerInfo.json");
//        List<JsonObject> objectList = userInfoList;
////        System.out.println(objectList.size());
//        Object[][] object = new Object[objectList.size()][1];
//
//        for (int i = 0; i < objectList.size(); i++) {
//            System.out.println(objectList.size());
////            UserInfo userInfo = new UserInfo();
////            userInfo.setEmail(objectList.get(i).get("email").getAsString());
////            userInfo.setPassword(objectList.get(i).get("password").getAsString());
//            object[i][0] = userInfo;
//////            object[i][0] = objectList.get(i);
//            System.out.println(objectList[i]);
//            System.out.println("********");
//        }
////        System.out.println(jsonList.get(0).get("firstPassenger"));
////        for(int i=0;i<jsonList.size();i++){
////
////        }
//
//    }
}
