package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllSideMenuPage extends BasePage{

    public AllSideMenuPage(WebDriver driver) {
        super(driver);
    }

    private By seeAllBtn = By.cssSelector(".hmenu-compressed-btn");
    private String sideMenuLink = "//div[text()='%s']";

    private void clickFromSideMenuByText (String sideMenuText)
    {
        By xpath = By.xpath(String.format(sideMenuLink, sideMenuText));
        clickElement(xpath);
    }

    public VideoGamesSideMenu clickVideoGamesLink ()
    {
        clickElement(seeAllBtn);
        clickFromSideMenuByText("Video Games");
        return new VideoGamesSideMenu (driver);
    }

}
