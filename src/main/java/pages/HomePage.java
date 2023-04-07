package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static filesReaders.ReadFromFiles.getPropertyByKey;

public class HomePage extends BasePage{

    // constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // locators of home page

    // Operations on homa page web elements
    public HomePage navigateToHomePage ()
    {
        navigateToURL(getPropertyByKey("environment.properties", "APP_URL"));
        return this;
    }

}
