package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClosedBoardsPopup extends BasePage{


    public ClosedBoardsPopup(WebDriver driver) {
        super(driver);
    }
    By closedBoards = By.cssSelector(".KIigWC5xzGtpZ7") ;

    public synchronized int getNumberOfClosedBoards ()
    {
        return locateListOfElements(closedBoards).size();
    }
}
