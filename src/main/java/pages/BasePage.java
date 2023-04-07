package pages;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected WebElement locateElement(By elementLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        return driver.findElement(elementLocator);
    }

    protected List<WebElement> locateListOfElements(By elementsLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementsLocator));
        wait.until(ExpectedConditions.elementToBeClickable(elementsLocator));
        return driver.findElements(elementsLocator);
    }

    protected void clickElement(By elementLocator) {
        locateElement(elementLocator).click();
    }
    protected void clickElement(WebElement element) {
        element.click();
    }

    protected void typeOnInputField(By elementLocator, String text) {
        locateElement(elementLocator).sendKeys(text);
    }

    protected String getTextOfElement(By elementLocator) {
        return locateElement(elementLocator).getText();
    }
    protected String getTextOfElement(WebElement element) {
        return element.getText();
    }

    protected String getAttributeOfElement(By elementLocator, String attributeKey) {
        return locateElement(elementLocator).getAttribute(attributeKey);
    }

    protected void hoverOverElement(By elementLocator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(locateElement(elementLocator));
        actions.perform();
    }

    protected void scrollVertically (int y_pixcel)
    {
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, y_pixcel);
        actions.perform();
    }
    protected void scrollToElement (By elementLocator)
    {
        WebElement element = locateElement(elementLocator);
        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
    }
    protected void scrollToElement (WebElement element)
    {
        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
    }
    protected void forceClickOnElement (By elementLocator )
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = locateElement(elementLocator) ;
        js.executeScript("arguments[0].click()" , element) ;
    }

    protected void navigateToURL (String url)
    {
        driver.get(url);
    }

    public void waitUntilPageToLoad ()
    {
        JavascriptExecutor j = (JavascriptExecutor)driver;
        if (j.executeScript("return document.readyState").toString().equals("complete")){
            System.out.println("Page has loaded");
        }
    }

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        String destination = "./screenshots/" + screenshotName + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(destination));
        } catch (IOException e) {
            // handle
        }
    }

}
