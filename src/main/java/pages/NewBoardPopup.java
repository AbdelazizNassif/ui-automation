package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewBoardPopup extends BasePage{

    public NewBoardPopup(WebDriver driver) {
        super(driver);
    }

    public synchronized void fillBoardTitle (String board)
    {
        By boardTitleField = By.cssSelector("[data-testid='create-board-title-input']");
        typeOnInputField(boardTitleField, board);
//        return this;
    }
    public synchronized void selectBoardVisibility (String visibility)
    {
        By boardVisibilityDropdown = By.xpath("//div[contains(@id, 'create-board-select-visibility' )]");
        clickElement(boardVisibilityDropdown);
        clickElement(By.xpath(String.format("//*[text()='%s']", visibility)));
//        return this;
    }
    public synchronized BoardPreviewPage clickCreateBoardBtn ()
    {
        By createBoardBtn = By.cssSelector("[data-testid='create-board-submit-button']");
        clickElement(createBoardBtn);
        return new BoardPreviewPage(driver);
    }

}
