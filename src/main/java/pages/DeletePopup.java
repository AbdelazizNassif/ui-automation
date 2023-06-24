package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeletePopup extends BasePage{

    public DeletePopup(WebDriver driver) {
        super(driver);
    }
    By deletionMessage = By.cssSelector("[data-testid='close-board-big-message']");


    public synchronized String getDeletionMessage ()
    {
        return getTextOfElement(deletionMessage);
    }
}
