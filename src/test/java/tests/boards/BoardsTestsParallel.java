package tests.boards;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tests.TestBase;

import static filesReaders.ReadFromFiles.getJsonStringValueByKey;
import static utils.DataGen.generateRandomString;


public class BoardsTestsParallel extends TestBase {

    volatile ThreadLocal<String> boardName= new ThreadLocal<>();;
    volatile ThreadLocal<String> privateBoardVisibility= new ThreadLocal<>();;
    volatile ThreadLocal<String> testDataFile= new ThreadLocal<>();

    @BeforeMethod
    public synchronized   void getTestData ()
    {
        testDataFile.set( "board.json"  ) ;
        boardName .set( (  String.format(getJsonStringValueByKey(testDataFile.get(),"boardName"), generateRandomString(7))) ) ;
        privateBoardVisibility .set( ( getJsonStringValueByKey(testDataFile.get(),"privateBoardVisibility")) )  ;
    }

    @Test
    public synchronized   void verifyNewBoardIsAdded_UI()  {

        NewBoardPopup newBoardPopup = new HomePage(driver.get()).clickAddNewBoardTile();
        newBoardPopup.clickCreateBoardBtn();
        newBoardPopup.selectBoardVisibility(privateBoardVisibility.get());
        BoardPreviewPage boardPreviewPage = newBoardPopup.clickCreateBoardBtn();



        Assert.assertEquals(boardPreviewPage.getBoardTitle(), boardName.get(),
                "board name should match the creation name");
        Assert.assertEquals(boardPreviewPage.getBoardVisibilty(), privateBoardVisibility.get(),
                "board visibility should match be private");
        // post condition: delete board
        new SideMenu(driver.get())
                .deleteActiveBoard(boardName.get());
    }
    @Test
    public synchronized   void verifyNewBoardIsAdded_UI22()  {

        NewBoardPopup newBoardPopup = new HomePage(driver.get()).clickAddNewBoardTile();
        newBoardPopup.clickCreateBoardBtn();
        newBoardPopup.selectBoardVisibility(privateBoardVisibility.get());
        BoardPreviewPage boardPreviewPage = newBoardPopup.clickCreateBoardBtn();

        Assert.assertEquals(boardPreviewPage.getBoardTitle(), boardName.get(),
                "board name should match the creation name");
        Assert.assertEquals(boardPreviewPage.getBoardVisibilty(), privateBoardVisibility.get(),
                "board visibility should match be private");
        // post condition: delete board
        new SideMenu(driver.get())
                .deleteActiveBoard(boardName.get());
    }
    @Test
    public synchronized    void verifyMembersOfTrelloProject()  {
        MembersDetailsPage membersDetailsPage = new HomePage(driver.get())
                .clickMembersButton();

        Assert.assertTrue(membersDetailsPage.getPageHeader().contains("Workspace members (1)"),
                "members page should have title: Workspace members (1)");
        Assert.assertEquals(membersDetailsPage.getNumberOfMembers(), 1,
                "members should be only 1 in this trello team");
    }
    @Test
    public synchronized    void verifyMembersOfTrelloProject22()  {
        MembersDetailsPage membersDetailsPage = new HomePage(driver.get())
                .clickMembersButton();

        Assert.assertTrue(membersDetailsPage.getPageHeader().contains("Workspace members (1)"),
                "members page should have title: Workspace members (1)");
        Assert.assertEquals(membersDetailsPage.getNumberOfMembers(), 1,
                "members should be only 1 in this trello team");
    }
    @Test
    public synchronized    void verifyClosedBoardsAreNotEmpty()  {
        ClosedBoardsPopup closedBoardsPopup = new HomePage(driver.get())
                .clickViewClosedPopup()
                        ;

        Assert.assertTrue(closedBoardsPopup.getNumberOfClosedBoards() > 0,
                 "numebr of closed board should be greater than 0");
    }
    @Test
    public synchronized    void verifyClosedBoardsAreNotEmpty22()  {
        ClosedBoardsPopup closedBoardsPopup = new HomePage(driver.get())
                .clickViewClosedPopup()
                ;

        Assert.assertTrue(closedBoardsPopup.getNumberOfClosedBoards() > 0,
                "numebr of closed board should be greater than 0");
    }

    @AfterMethod
    public synchronized void clearThreads ()
    {
        boardName = null ;
        privateBoardVisibility = null ;
        testDataFile  = null ;
    }

}
