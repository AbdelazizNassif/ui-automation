package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static filesReaders.ReadFromFiles.getPropertyByKey;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    By emailField = By.id("user");
    By continueBtn = By.id("login");
    By passwordField = By.id("password");


    public synchronized void login() {

        typeOnInputField(emailField, getPropertyByKey("environment.properties", "USER_EMAIL"));
        clickElement(continueBtn);
        typeOnInputField(passwordField, getPropertyByKey("environment.properties", "PASSWORD") + Keys.ENTER);
    }

    public synchronized LoginPage navigate ()  {
        navigateToURL(getPropertyByKey("environment.properties", "BASE_URL") + "login");
        return this;
    }
}
