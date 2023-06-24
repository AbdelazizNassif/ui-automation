package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class BoardPreviewPage extends BasePage{

    public BoardPreviewPage(WebDriver driver) {
        super(driver);
    }
    By boardTitle = By.cssSelector("[data-testid='board-name-display']");
    By boardVisibility = By.cssSelector("[aria-label='Private']");
    By boardTitleField = By.cssSelector("[data-testid='board-name-input']");

    public synchronized String getBoardTitle() {
        return getTextOfElement(boardTitle);
    }

    public synchronized String getBoardVisibilty() {
        return getTextOfElement(boardVisibility);
    }

    public synchronized void updateBoardName (String newBoardName)
    {
        clickElement(boardTitle);
        typeOnInputField(boardTitleField, newBoardName + Keys.ENTER);
    }


}
