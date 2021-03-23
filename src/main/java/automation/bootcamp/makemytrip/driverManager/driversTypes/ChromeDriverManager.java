package automation.bootcamp.makemytrip.driverManager.driversTypes;

        import automation.bootcamp.makemytrip.driverManager.DriverManagerAbstract;
        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManagerAbstract {
    /***
     * Overridden method from DriverManagerAbstract to setup and maximize the required browser which is here Chrome
     * @return webdriver
     */
    @Override
    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
