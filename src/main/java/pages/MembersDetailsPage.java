package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MembersDetailsPage extends BasePage{

    public MembersDetailsPage(WebDriver driver) {
        super(driver);
    }

    public synchronized String getPageHeader ()
    {
        return getTextOfElement(By.xpath("(//div[@class='org-members-actions-header'])[1]/h1"));
    }

    public synchronized int getNumberOfMembers ()
    {
        return locateListOfElements(By.cssSelector(".name-line .full-name")).size();
    }
}
