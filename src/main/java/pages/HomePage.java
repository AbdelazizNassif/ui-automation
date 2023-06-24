package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static filesReaders.ReadFromFiles.getPropertyByKey;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    By boardTitles = By.cssSelector(".board-tile-details-name");
    By addNewBoardTile = By.xpath("//*[text()='Create new board']");
    String boardTitleLocator = "//div[@title='%s' and contains(@class, 'board-tile-details-name')]";


    public synchronized HomePage navigate() {
        navigateToURL(getPropertyByKey("environment.properties", "BASE_URL"));
        return this;
    }

    public synchronized List<String> getAllTitlesBoard() {

        List<String> titles = new ArrayList<>();
        refreshCurrentPage();
        for (WebElement element : locateListOfElements(boardTitles)) {
            titles.add(element.getAttribute("title"));
        }
        return titles;
    }

    public synchronized NewBoardPopup clickAddNewBoardTile() {
        waitUntilPageToLoad();
        clickElement(addNewBoardTile);
        return new NewBoardPopup(driver);
    }

    public synchronized BoardPreviewPage clickSpecificBoardByName(String boardName) {

        By boardTitleXpath = By.xpath(String.format(boardTitleLocator, boardName));
        clickElement(boardTitleXpath);
        return new BoardPreviewPage(driver);
    }

    public synchronized HomePage refresh() {
        refreshCurrentPage();
        return this;
    }

    public synchronized MembersDetailsPage clickMembersButton() {
        clickElement(By.xpath("//*[@class='boards-page-board-section-header']//span[text()='Members']"));
        return new MembersDetailsPage(driver);
    }

    public synchronized ClosedBoardsPopup clickViewClosedPopup() {
        clickElement(By.cssSelector(".view-all-closed-boards-button"));
        return new ClosedBoardsPopup(driver);
    }
}
