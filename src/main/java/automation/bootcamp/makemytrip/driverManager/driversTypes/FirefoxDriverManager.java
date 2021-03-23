package automation.bootcamp.makemytrip.driverManager.driversTypes;

import automation.bootcamp.makemytrip.driverManager.DriverManagerAbstract;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManagerAbstract {
    /***
     * Overridden method from DriverManagerAbstract to setup and maximize the required browser which is here Firefox
     * @return webdriver
     */
    @Override
    public WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
