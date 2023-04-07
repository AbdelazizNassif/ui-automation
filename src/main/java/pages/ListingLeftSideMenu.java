package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListingLeftSideMenu extends BasePage{
    public ListingLeftSideMenu(WebDriver driver) {
        super(driver);
    }
    private By freeShipping = By.xpath("//span[text()='Free Shipping']");
    private By newCondition = By.xpath("//span[text()='New']");

    public void clickFreeShipping()
    {
        clickElement(freeShipping);
    }
    public void clickNewCondition()
    {
        clickElement(newCondition);
    }
}
