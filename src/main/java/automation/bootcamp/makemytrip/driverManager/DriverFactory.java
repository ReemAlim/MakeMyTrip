package automation.bootcamp.makemytrip.driverManager;

import automation.bootcamp.makemytrip.dataReaders.PropertyReader;
import automation.bootcamp.makemytrip.driverManager.driversTypes.ChromeDriverManager;
import automation.bootcamp.makemytrip.driverManager.driversTypes.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    static PropertyReader propertyReader = new PropertyReader();
    static String browserName = propertyReader.getConfigProperty("browser");
    static WebDriver webDriver = null;

    /***
     * Method to get the required browser based on the value in the property file which is equal to one of the ENUM values;
     * It is called by the BasePage to initialize the webdriver
     * @return webdriver
     */
    public static WebDriver getDriver() {

        switch (BrowserTypes.valueOf(browserName.toUpperCase())) {
            case CHROME:
                webDriver = new ChromeDriverManager().getDriver();
                System.out.println("WebDriver : " + webDriver);
                break;
            case FIREFOX:
                webDriver = new FirefoxDriverManager().getDriver();
                System.out.println("WebDriver : " + webDriver + " Firefox");
                break;

            default:
                System.out.println("Unsupported browser");
        }
        return webDriver;
    }


}
