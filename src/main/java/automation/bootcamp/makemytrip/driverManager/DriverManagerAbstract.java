package automation.bootcamp.makemytrip.driverManager;

import org.openqa.selenium.WebDriver;

public abstract  class DriverManagerAbstract {
    /***
     * Abstract methods will be listed here to be overridden by the classes inherit from this abstract class;
     * This method will be implemented to get the current webdriver;
     * @return webdriver
     */
    public abstract WebDriver getDriver();
}
