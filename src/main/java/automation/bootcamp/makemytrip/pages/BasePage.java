package automation.bootcamp.makemytrip.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BasePage() {

    }
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Initiate a browser and navigate to the given URL; This should be moved to the loginPage
     */
    protected void getURL() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.navigate().to("https://www.makemytrip.com");
        driver.manage().window().maximize();
    }

    /**
     * Perform click on the given locator
     *
     * @param locator The webElement locator
     */
    protected void clickOnButton(By locator) {
        waitForElementToBeVisible(locator).click();
    }

    /**
     * Perform double click on the given locator
     *
     * @param locator
     */
    protected void doubleCLick(By locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(locator)).perform();

    }

    /***
     * Perform typing on a given textfield after clearing it as it might have old data
     * @param locator
     * @param text
     */
    protected void fillTextField(By locator, String text) {
        WebElement textField = waitForElementToBeVisible(locator);
        if (textField.isEnabled()) {
            textField.clear();
            textField.sendKeys(text);
            textField.sendKeys(Keys.ENTER);
        }
    }

    /***
     * Wait for the element to be visible using the locator
     * @param locator, Locator used to check if the element is visible or not
     * @return returns a webElement to perform an action on
     */
    private WebElement waitForElementToBeVisible(By locator) {
        webDriverWait = new WebDriverWait(driver, 20, 500);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /***
     * Wait for the element to be visible using the locator
     * @param locator, Locator used to check if the element is visible or not
     * @return returns a webElement to perform an action on
     */
    private WebElement waitForWebElementToBeVisible(WebElement webElement) {
        webDriverWait = new WebDriverWait(driver, 20, 500);
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    private List<WebElement> waitForALLWebElementToBeVisible(List<WebElement> webElements){
        webDriverWait = new WebDriverWait(driver, 20, 500);
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    /***
     * Get an option of a UL where dropdown is displayed as UL then checks that the text for LI is equal to a text
     * @param ulLocator, This is the UL which represents a dropdown in the DOM
     * @param liLocator, This is a locator for all the LI represented in the UL
     * @param pLocator, This is a locator for the paragraph where we get the text to compare with the sent one
     * @param text, The text to compare it with the one we get from the DOM
     */
    protected void getLiDropdownOption(By ulLocator, By liLocator, By pLocator, String text) {
//        webDriverWait = new WebDriverWait(driver, 20, 500);
        WebElement ulElement = driver.findElement(ulLocator);

        List<WebElement> liOptions = waitForALLWebElementToBeVisible(ulElement.findElements(liLocator));
//        System.out.println(liOptions);
//        System.out.println("----------");
        for (WebElement op : liOptions) {
            try {
                WebElement webElement = op.findElement(pLocator);
                if (waitForWebElementToBeVisible(webElement).getText().equalsIgnoreCase(text)) {
                    try {
                        waitForWebElementToBeVisible(webElement).click();
                        break;
                    } catch (StaleElementReferenceException e) {
                        e.printStackTrace();
                    }

                }
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * Check if a webElement is displayed on the page or not using locator
     * @param locator, This is the locator used to check if the element is displayed or not
     * @return, Returns a boolean value
     */
    protected boolean checkWebElementIsDisplayed(By locator) {
        webDriverWait = new WebDriverWait(driver, 30, 500);
        try{
            if (webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(locator))).isDisplayed()) {
                return true;
            }
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }


        return false;
    }
    protected void getSelectWebElement(By selectLocator , String value){
        WebElement selectWebElement = driver.findElement(selectLocator);
        Select selectElement = new Select(selectWebElement);
        selectElement.selectByVisibleText(value);
    }
    protected  List<WebElement> getWebElements(By locator){
        return driver.findElements(locator);
    }
    protected  WebElement getWebElement(By locator){
        return driver.findElement(locator);
    }

    protected void quitBrowser(){
        driver.quit();
    }
}
