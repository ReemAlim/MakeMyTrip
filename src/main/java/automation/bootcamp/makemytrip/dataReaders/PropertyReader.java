package automation.bootcamp.makemytrip.dataReaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private String propertyFilePath = "src/main/resources/config.properties";
    static Properties properties = new Properties();

    public PropertyReader() {
        readPropertyFile();
    }

    /***
     * Method to read the property file and load the properties value;
     * It is called from the constructor that is why it is defined private
     */
    private void readPropertyFile() {
        try {
            FileReader reader = new FileReader(propertyFilePath);
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Method to get the required value for the required key from the loaded properties object
     * @param propertyKey, value for the key to get its value from the property file
     * @return value for the required key as a string
     */
    public static String getConfigProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

}
