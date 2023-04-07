package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VideoGamesSideMenu extends BasePage{
    public VideoGamesSideMenu(WebDriver driver) {
        super(driver);
    }

    private String sideMenuLink = "//a[text()='%s']";

    private void clickFromSideMenuByText (String sideMenuText)
    {
        By xpath = By.xpath(String.format(sideMenuLink, sideMenuText));
        clickElement(xpath);
    }

    public AllVideoGamesListingPage clickAllVideoGamesLink ()
    {
        clickFromSideMenuByText("All Video Games");
        return new AllVideoGamesListingPage (driver);
    }
}
