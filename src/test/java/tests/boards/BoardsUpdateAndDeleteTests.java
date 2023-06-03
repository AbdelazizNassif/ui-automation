package tests.boards;


import apiObjects.BoardsApis;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tests.TestBase;
import static filesReaders.ReadFromFiles.getJsonStringValueByKey;
import static filesReaders.ReadFromFiles.getPropertyByKey;
import static utils.DataGen.generateRandomString;

public class BoardsUpdateAndDeleteTests extends TestBase {

    final String testDataFile  = "board.json" ;
    volatile ThreadLocal<BoardsApis> boardApis  = new ThreadLocal<>() ;
    volatile ThreadLocal<String> boardName  = new ThreadLocal<>() ;
    volatile ThreadLocal<String> boardId  = new ThreadLocal<>() ;

    @BeforeMethod
    public synchronized    void precondition_createNewBoard() {
        boardApis.set( (  new BoardsApis(getPropertyByKey("environment.properties", "API_KEY"),
                getPropertyByKey("environment.properties", "TOKEN")) ) );
        boardName.set( (  String.format(getJsonStringValueByKey(testDataFile,"boardName"),  generateRandomString(7) )) ) ;
        Response response = boardApis.get().createNewBoard(boardName.get());
        boardId .set(  response.jsonPath().getString("id") ); ;
    }

    @Test
    public synchronized   void verifyBoardIsDeleted ()
    {
        new HomePage(driver.get()).refresh()
                .clickSpecificBoardByName(boardName.get());
        DeletePopup deletionPopup = new SideMenu(driver.get())
                .deleteActiveBoard(boardName.get());
        Assert.assertEquals(deletionPopup.getDeletionMessage(), boardName.get() + " is closed.",
                "Deletion message is not as expected" );
    }
    @Test
    public synchronized   void verifyBoardIsDeleted33 ()
    {
        new HomePage(driver.get()).refresh()
                .clickSpecificBoardByName(boardName.get());
        DeletePopup deletionPopup = new SideMenu(driver.get())
                .deleteActiveBoard(boardName.get());
        Assert.assertEquals(deletionPopup.getDeletionMessage(), boardName.get() + " is closed.",
                "Deletion message is not as expected" );
    }
    @Test
    public synchronized   void verifyBoardIsDeleted22 ()
    {
        new HomePage(driver.get()).refresh()
                .clickSpecificBoardByName(boardName.get());
        DeletePopup deletionPopup = new SideMenu(driver.get())
                .deleteActiveBoard(boardName.get());
        Assert.assertEquals(deletionPopup.getDeletionMessage(), boardName.get() + " is closed.",
                "Deletion message is not as expected" );
    }
    @Test
    public synchronized    void verifyBoardIsUpdated () {
        BoardPreviewPage boardPreviewPage = new HomePage(driver.get()).refresh()
                .clickSpecificBoardByName(boardName.get());
        String updatedBoardName =
                 String.format(getJsonStringValueByKey(testDataFile,"boardUpdatedName"), generateRandomString(4) );

        boardPreviewPage.updateBoardName(updatedBoardName);

        new HomePage(driver.get()).navigate();
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard() ,
                Matchers.hasItem(updatedBoardName) );
    }
    @AfterMethod
    public synchronized    void cleanup_deleteCreatedBoard ()
    {
        Response response = boardApis.get().deleteBoard(boardId.get());
        response.then().log().all();
        response.then().statusCode(200)
                .body("_value" , Matchers.equalTo(null));

        boardApis  = null ;
        boardName  = null ;
        boardId  = null ;
    }
}
