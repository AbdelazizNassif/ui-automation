package tests.boards;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardPreviewPage;
import pages.HomePage;
import pages.NewBoardPopup;
import pages.SideMenu;
import tests.TestBase;
import utils.DataGen;

import static filesReaders.ReadFromFiles.getJsonStringValueByKey;
import static utils.DataGen.generateRandomString;


public class BoardsTestsUI extends TestBase {

    volatile ThreadLocal<String> boardName = new ThreadLocal<>();
    volatile ThreadLocal<String> privateBoardVisibility = new ThreadLocal<>();
    final String testDataFile=  "board.json";

    @BeforeMethod
    public synchronized   void getTestData ()
    {
        boardName .set(  String.format(getJsonStringValueByKey(testDataFile,"boardName"), generateRandomString(7)));
        privateBoardVisibility.set( ( getJsonStringValueByKey(testDataFile,"privateBoardVisibility"))  ) ;
    }

    @Test
    public synchronized    void verifyNewBoardIsAdded_firstMethod()  {
        NewBoardPopup newBoardPopup = new HomePage(driver.get()).clickAddNewBoardTile();
        newBoardPopup.clickCreateBoardBtn();
        newBoardPopup.selectBoardVisibility(privateBoardVisibility.get());
        BoardPreviewPage boardPreviewPage = newBoardPopup.clickCreateBoardBtn();

        Assert.assertEquals(boardPreviewPage.getBoardTitle(), boardName.get(),
                "board name should match the creation name");
        Assert.assertEquals(boardPreviewPage.getBoardVisibilty(), privateBoardVisibility.get(),
                "board visibility should match be private");
    }

    @Test
    public synchronized    void verifyNewBoardIsAdded_secondMethod()  {
        NewBoardPopup newBoardPopup = new HomePage(driver.get()).clickAddNewBoardTile();
        newBoardPopup.clickCreateBoardBtn();
        newBoardPopup.selectBoardVisibility(privateBoardVisibility.get());
        BoardPreviewPage boardPreviewPage = newBoardPopup.clickCreateBoardBtn();

        Assert.assertEquals(boardPreviewPage.getBoardTitle(), boardName.get(),
                "board name should match the creation name");
        Assert.assertEquals(boardPreviewPage.getBoardVisibilty(), privateBoardVisibility.get(),
                "board visibility should match be private");
    }

    @Test
    public synchronized    void verifyNewBoardIsAdded_thirdMethod()  {
        NewBoardPopup newBoardPopup = new HomePage(driver.get()).clickAddNewBoardTile();
        newBoardPopup.clickCreateBoardBtn();
        newBoardPopup.selectBoardVisibility(privateBoardVisibility.get());
        BoardPreviewPage boardPreviewPage = newBoardPopup.clickCreateBoardBtn();

        Assert.assertEquals(boardPreviewPage.getBoardTitle(), boardName.get(),
                "board name should match the creation name");
        Assert.assertEquals(boardPreviewPage.getBoardVisibilty(), privateBoardVisibility.get(),
                "board visibility should match be private");
    }

    @AfterMethod
    public synchronized  void cleanup_deleteCreatedBoard ()
    {
            new SideMenu(driver.get())
                    .deleteActiveBoard(boardName.get());
        boardName = null ;
        privateBoardVisibility = null ;

    }

}
