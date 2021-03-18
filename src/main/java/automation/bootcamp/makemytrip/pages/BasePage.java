package automation.bootcamp.makemytrip.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.NoSuchElementException;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BasePage() {

    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void launchBrowser(String url) {
        getURL(url);
    }

    /**
     * Initiate a browser and navigate to the given URL; This should be moved to the loginPage
     */
    protected void getURL(String url) {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.navigate().to(url);
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
        if (textField.isDisplayed()) {
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
     * @param webElement, wait for this web element to be visible
     * @return returns a webElement to perform an action on
     */
    protected WebElement waitForWebElementToBeVisible(WebElement webElement) {
        webDriverWait = new WebDriverWait(driver, 20, 500);
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected WebElement waitForWebElementToBeClicked(WebElement webElement) {
        webDriverWait = new WebDriverWait(driver, 20, 500);
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /***
     * Wait for the element to be visible using the locator
     * @param webElements, wait for these web elements to be visible
     * @return returns list of web elements to perform an action on
     */
    private List<WebElement> waitForALLWebElementToBeVisible(List<WebElement> webElements) {
        webDriverWait = new WebDriverWait(driver, 20, 500);
        try {

            return webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElements));
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * Get an option of a UL where dropdown is displayed as UL then checks that the text for LI is equal to a text
     * @param ulLocator, This is the UL which represents a dropdown in the DOM
     * @param liLocator, This is a locator for all the LI represented in the UL
    //     * @param pLocator, This is a locator for the paragraph where we get the text to compare with the sent one
     * @param text, The text to compare it with the one we get from the DOM
     */
//    protected void getLiDropdownOption(By ulLocator, By liLocator,String text) {
////        System.out.println(text);
//        WebElement ulElement = waitForElementToBeVisible(ulLocator);
////        WebElement ulElement = driver.findElement(ulLocator);
//        List<WebElement> liOptions = ulElement.findElements(liLocator);
//        System.out.println(liOptions.size());
//        System.out.println("----");
//        loopOverLiDropdown(liOptions,text);
////        if (!liOptions.isEmpty()) {
////            for (WebElement op : liOptions) {
////                try {
////                    System.out.println(op.getText());
//////                    WebElement webElement = op.findElement(pLocator);
////                    if (op.getText().equalsIgnoreCase(text)) {
////                        try {
////                            System.out.println(op.getText());
////                            op.click();
////                            break;
////                        } catch (StaleElementReferenceException e) {
////                            e.printStackTrace();
////                        }
////
////                    }
//////                    break;
////                } catch (StaleElementReferenceException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
//
//    }
    protected void getLiDropdownOption(By ulLocator, By liLocator, By pLocator, String text) {
        try{
            WebElement ulElement = driver.findElement(ulLocator);
            List<WebElement> liOptions = waitForALLWebElementToBeVisible(ulElement.findElements(pLocator));
            for (WebElement op : liOptions) {
                System.out.println(op.getText());
                try {
//                    WebElement webElement = op.findElement(pLocator);
                    if (op.getText().equalsIgnoreCase(text)) {
                        try {
                            op.click();
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
        catch (Exception e){e.printStackTrace();}

    }

    private void loopOverLiDropdown(List<WebElement> liOptions, String text) {
        if (!liOptions.isEmpty()) {
            for (WebElement op : liOptions) {
                try {
                    System.out.println(op.getText());
//                    String t = op.findElement(By.className("locusLabel")).getText();
//                    checkLiTextWithCityName(op.getText());
//                    WebElement webElement = op.findElement(pLocator);
                    if (op.getText().contains(text)) {
                        try {
                            System.out.println(op.getText());
                            op.click();
                            break;
                        } catch (StaleElementReferenceException e) {
                            e.printStackTrace();
                        }

                    }
//
                } catch (StaleElementReferenceException e) {
                    e.printStackTrace();
                }
            }
        }

    }

//    protected void checkLiTextWithCityName(String liText,, String text) {
//
//    }

    protected void getOptionFromDropDown(By optionsLocator, String text) {

        List<WebElement> liOptions = waitForALLWebElementToBeVisible(driver.findElements(optionsLocator));
        for (WebElement op : liOptions) {
            if (op.getText().equalsIgnoreCase(text)) {
                op.click();
                break;
            }
        }
    }

    public void moveSliderToEnd(WebElement slider, int xOffset) {
        slider.click();
        Actions moveSlider = new Actions(driver);
        Action action = moveSlider.dragAndDropBy(slider, xOffset, 0).build();
        action.perform();
    }

    /***
     * Check if a webElement is displayed on the page or not using locator
     * @param locator, This is the locator used to check if the element is displayed or not
     * @return, Returns a boolean value
     */
    protected boolean checkWebElementIsDisplayed(By locator) {
        webDriverWait = new WebDriverWait(driver, 30, 500);
        try {
            if (webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(locator))).isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }


        return false;
    }

    protected void getSelectWebElement(By selectLocator, String value) {
        WebElement selectWebElement = driver.findElement(selectLocator);
        Select selectElement = new Select(selectWebElement);
        selectElement.selectByVisibleText(value);
    }

    protected List<WebElement> getWebElements(By locator) {
        try {
            return waitForALLWebElementToBeVisible(driver.findElements(locator));
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected WebElement getWebElement(By locator) {
        return waitForWebElementToBeVisible(driver.findElement(locator));
    }

    protected Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    /***
     * Method to get the title of the current page
     * @return String of the title for this page
     */
    protected String getWindowTitle() {
        return driver.getTitle();
    }

    protected String getPageUrl() {
        return driver.getCurrentUrl();
    }

    /***
     * Method to get the text for an input
     * @param locator, webElement locator to gets its inner text
     * @return
     */
    protected String getTextForValueAttribute(By locator) {
        return waitForWebElementToBeVisible(driver.findElement(locator)).getAttribute("value");

    }

    protected void scrollThroughTheBrowser(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    protected void quitBrowser() {
        driver.quit();
    }
}
