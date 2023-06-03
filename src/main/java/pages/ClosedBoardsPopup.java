package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClosedBoardsPopup extends BasePage{


    public ClosedBoardsPopup(WebDriver driver) {
        super(driver);
    }

    public synchronized int getNumberOfClosedBoards ()
    {
        By closedBoards = By.cssSelector(".KIigWC5xzGtpZ7") ;
        return locateListOfElements(closedBoards).size();
    }
}
