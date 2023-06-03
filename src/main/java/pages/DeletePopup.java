package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeletePopup extends BasePage{

    public DeletePopup(WebDriver driver) {
        super(driver);
    }


    public synchronized String getDeletionMessage ()
    {
        By deletionMessage = By.cssSelector("[data-testid='close-board-big-message']");
        return getTextOfElement(deletionMessage);
    }
}
