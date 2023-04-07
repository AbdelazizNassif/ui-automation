package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBarPage extends BasePage{

    // constructor
    public NavigationBarPage(WebDriver driver) {
        super(driver);
    }

    // locators
    private By loginIcon = By.id("nav-link-accountList-nav-line-1");
    private By allIcon = By.id("nav-hamburger-menu");
    By cartIconFromNavBar = By.id("nav-cart");

    public LoginPage clickLoginIconFromNavigationBar ()
    {
        clickElement(loginIcon);
        return new LoginPage(driver);
    }

    public AllSideMenuPage clickAllFromNavigationBar ()
    {
        clickElement(allIcon);
        return new AllSideMenuPage(driver);
    }
    public ShoppingCart clickCartIconFromNavigationBar ()
    {
        clickElement(cartIconFromNavBar);
        return new ShoppingCart(driver);
    }
}
