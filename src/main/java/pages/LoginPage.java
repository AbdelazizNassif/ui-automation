package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By emailField = By.id("ap_email") ;
    By continueBtn = By.id("continue") ;
    By passwordField = By.id("ap_password") ;
    By signInBtn = By.id("signInSubmit") ;

    public HomePage loginToAmazonWebsite (String phoneOrEmail, String password)
    {
        typeOnInputField(emailField, phoneOrEmail);
        clickElement(continueBtn);
        typeOnInputField(passwordField, password);
        clickElement(signInBtn);
        return new HomePage(driver);
    }
}
