package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenu extends BasePage{

    public SideMenu(WebDriver driver) {
        super(driver);
    }
    String activeBoard = "//a[contains(@title,'%s')]";
    String threeDotsOfActiveBoard = "(//a[@aria-label='%s (currently active)']//following-sibling::div/*[@role='menu']//span[@data-testid='OverflowMenuHorizontalIcon'])";
    By closeBoardBtn = By.cssSelector("[title='Close board']");
    By confirmClosingBoardBtn = By.cssSelector("[title='Close']");

    public synchronized DeletePopup deleteActiveBoard (String boardName)
    {
        By activeBoardXpath = By.xpath(String.format(activeBoard, boardName));
        hoverOverElement(activeBoardXpath);
        clickElement(By.xpath(String.format(threeDotsOfActiveBoard, boardName)));
        clickElement(closeBoardBtn);
        clickElement(confirmClosingBoardBtn);
        return new DeletePopup (driver) ;
    }
}
