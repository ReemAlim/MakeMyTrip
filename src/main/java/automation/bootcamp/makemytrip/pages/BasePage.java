package automation.bootcamp.makemytrip.pages;

import automation.bootcamp.makemytrip.driverManager.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.NoSuchElementException;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        webDriverWait = new WebDriverWait(this.driver, 20, 500);
    }

    public void openPage(String pageUrl) {
        driver.get(pageUrl);
        waitForPageLoad();
    }

    /**
     * Wait until page is loaded
     */
    protected void waitForPageLoad() {
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }

    /**
     * Perform click on the given locator
     *
     * @param locator The webElement locator
     */
    protected void clickOnButton(By locator) {
        waitForElementToBeVisible(locator).click();
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
        webDriverWait = new WebDriverWait(driver, 20, 1000);
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
//            e.printStackTrace();
            waitForALLWebElementToBeVisible(webElements);
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

    protected void getLiDropdownOption(By ulLocator, By liLocator, By pLocator, String text) {
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement ulElement = driver.findElement(ulLocator);
            List<WebElement> liOptions = waitForALLWebElementToBeVisible(ulElement.findElements(liLocator));
            for (WebElement op : liOptions) {
                try {
                    if (op.findElement(pLocator).getText().equalsIgnoreCase(text)) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void moveSliderToEnd(WebElement slider, int xOffset) {
        Actions moveSlider = new Actions(driver);
        moveSlider.dragAndDropBy(slider, xOffset, 0).build().perform();
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

        webDriverWait = new WebDriverWait(driver, 20, 500);
        List<WebElement> elements = null;
        try {
            elements = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (StaleElementReferenceException e) {
            getWebElements(locator);
        }
        return elements;
    }

    protected List<WebElement> getWebElementsFromParent(WebElement webElement, By locator) {
        try {

            return webElement.findElements(locator);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected WebElement getWebElementFromParent(WebElement webElement, By locator) {
        try {

            return webElement.findElement(locator);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected WebElement getWebElement(By locator) {
        return waitForWebElementToBeVisible(driver.findElement(locator));
    }

    protected String getCurrentWindowId(WebDriver webDriver) {
        return webDriver.getWindowHandle();
    }

    protected WebDriver switchToRequiredTab(String windowId) {
        Set<String> allTabs = driver.getWindowHandles();
        if (!allTabs.isEmpty() && allTabs.size() == 2) {
            allTabs.remove(windowId);
            return driver.switchTo().window(allTabs.iterator().next());
        }
        return null;
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
        waitForPageLoad();
        return driver.findElement(locator).getAttribute("value");

    }

    protected WebElement scrollThroughTheBrowser(By locator) {
        waitForPageLoad();
        WebElement webElement = waitForElementToBeVisible(locator);
        int yAxis = webElement.getLocation().getY();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0," + yAxis + ")", "");
        return webElement;
    }

    protected WebElement waitUntilElementIsVisibleInPage(By locator) {
        webDriverWait = new WebDriverWait(driver, 50, 700);
        WebElement element = null;
        try {

            element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element;
        } catch (StaleElementReferenceException e) {
            waitUntilElementIsVisibleInPage(locator);
        }
        return element;
    }

    public void closeBrowser() {
        driver.quit();
    }
}
