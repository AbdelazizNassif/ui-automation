package tests.boards;

import apiObjects.BoardsApis;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.*;
import pages.HomePage;
import tests.TestBase;

import static filesReaders.ReadFromFiles.getJsonStringValueByKey;
import static filesReaders.ReadFromFiles.getPropertyByKey;
import static utils.DataGen.generateRandomString;


public class BoardsTests extends TestBase {

    volatile static ThreadLocal<String> boardName = new ThreadLocal<>();
    final String testDataFile = "board.json";
    volatile BoardsApis boardApis ;
    volatile static  ThreadLocal<String> boardId = new ThreadLocal<>();

    @BeforeMethod
    public synchronized void precondition_getTestData() {
        boardApis = new BoardsApis(getPropertyByKey("environment.properties", "API_KEY"),
                getPropertyByKey("environment.properties", "TOKEN")) ;
        boardName.set(String.format(getJsonStringValueByKey(testDataFile, "boardName"),
                generateRandomString(7)));
    }

    @Test
    public synchronized void verifyNewBoardIsAdded_apiFirstMethod() {
        Response response = boardApis.createNewBoard(boardName.get());
        boardId.set((response.jsonPath().getString("id")));
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard(),
                Matchers.hasItem(boardName.get()));
    }

    @Test
    public synchronized void verifyNewBoardIsAdded_apiFirstMethod22() {
        Response response = boardApis.createNewBoard(boardName.get());
        boardId.set((response.jsonPath().getString("id")));
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard(),
                Matchers.hasItem(boardName.get()));
    }

    @Test
    public synchronized void verifyNewBoardIsAdded_apiFirstMethod33() {
        Response response = boardApis.createNewBoard(boardName.get());
        boardId.set((response.jsonPath().getString("id")));
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard(),
                Matchers.hasItem(boardName.get()));
    }

    @Test
    public synchronized void verifyNewBoardIsAdded_apiSecondMethod() {
        Response response = boardApis.createNewBoard(boardName.get());
        boardId.set(response.jsonPath().getString("id"));
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard(),
                Matchers.hasItem(boardName.get()));
    }

    @Test
    public synchronized void verifyNewBoardIsAdded_apiSecondMethod22() {
        Response response = boardApis.createNewBoard(boardName.get());
        boardId.set(response.jsonPath().getString("id"));
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard(),
                Matchers.hasItem(boardName.get()));
    }

    @Test
    public synchronized void verifyNewBoardIsAdded_apiSecondMethod44() {
        Response response = boardApis.createNewBoard(boardName.get());
        boardId.set(response.jsonPath().getString("id"));
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard(),
                Matchers.hasItem(boardName.get()));
    }

    @Test
    public synchronized void verifyNewBoardIsAdded_apiThirdMethod22() {
        Response response = boardApis.createNewBoard(boardName.get());
        boardId.set(response.jsonPath().getString("id"));
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard(),
                Matchers.hasItem(boardName.get()));
    }

    @Test
    public synchronized void verifyNewBoardIsAdded_apiThirdMethod33() {
        Response response = boardApis.createNewBoard(boardName.get());
        boardId.set(response.jsonPath().getString("id"));
        MatcherAssert.assertThat(new HomePage(driver.get()).getAllTitlesBoard(),
                Matchers.hasItem(boardName.get()));
    }

    @AfterMethod
    public synchronized void postCondition_deleteCreatedBoard() {
        Response response = boardApis.deleteBoard(boardId.get());
        response.then().log().all();
        response.then().statusCode(200)
                .body("_value", Matchers.equalTo(null));

    }
}
