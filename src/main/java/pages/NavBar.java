package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavBar extends BasePage{

    public NavBar(WebDriver driver) {
        super(driver);
    }
    By loginBtn = By.xpath("//div[contains(@class,'BigNavstyles')]//a[text()='Log in']");


    public synchronized LoginPage clickLoginBtnFromNavBar ()
    {
        clickElement(loginBtn) ;
        return new LoginPage(driver) ;
    }
}
