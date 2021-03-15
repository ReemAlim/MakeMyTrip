package automation.bootcamp.makemytrip.file.dataWriters;

import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;
public class JsonWriter {
    public static void writeInJsonFile(JsonObject objectToBeAdded){

        try{
            FileWriter file = new FileWriter("/Users/reemalim/Documents/Reem/AutomationBootCamp/FinalProject/src/test/resources/searchListingPageUrl.json");
            file.append(objectToBeAdded.toString());
            file.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
