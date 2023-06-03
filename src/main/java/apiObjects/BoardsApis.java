package apiObjects;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static filesReaders.ReadFromFiles.getPropertyByKey;

public class BoardsApis {

    public final String BOARDS_ENDPOINT = "1/boards/";

    private String apiKey;
    private String token;

    public BoardsApis(String apiKey, String token) {
        this.apiKey = apiKey;
        this.token = token;
    }

    public Response createNewBoard(String boardName) {

        return RestAssured.given().baseUri(getPropertyByKey("environment.properties", "API_BASE_URL"))
                .header("Host" , "api.trello.com")
                .contentType(ContentType.JSON)
                .queryParam("name", boardName)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .log().all()
                .post(BOARDS_ENDPOINT)
                ;
    }
    public Response deleteBoard (String boardId)
    {
        return RestAssured.given().baseUri(getPropertyByKey("environment.properties", "API_BASE_URL"))
                .header("Host" , "api.trello.com")
                .contentType(ContentType.JSON)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .log().all()
                .delete(BOARDS_ENDPOINT + boardId)
                ;
    }
}
