package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class BoardPreviewPage extends BasePage{

    public BoardPreviewPage(WebDriver driver) {
        super(driver);
    }


    public synchronized String getBoardTitle() {
        By boardTitle = By.cssSelector("[data-testid='board-name-display']");
        return getTextOfElement(boardTitle);
    }

    public synchronized String getBoardVisibilty() {
        By boardVisibility = By.cssSelector("[aria-label='Private']");
        return getTextOfElement(boardVisibility);
    }

    public synchronized void updateBoardName (String newBoardName)
    {
        By boardTitle = By.cssSelector("[data-testid='board-name-display']");
        By boardTitleField = By.cssSelector("[data-testid='board-name-input']");
        clickElement(boardTitle);
        typeOnInputField(boardTitleField, newBoardName + Keys.ENTER);
    }


}
