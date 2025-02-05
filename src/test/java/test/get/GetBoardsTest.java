package test.get;

import consts.BoardEndpoint;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;


public class GetBoardsTest extends BaseTest {


    @Test
    public void checkGetBoards(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("member", UrlParamValues.USER_NAME)
                .get(BoardEndpoint.GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }


    @Test
    public void checkGetBoard(){
        requestWithAuth()
                .queryParams("fields", "id,name")
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
            .get(BoardEndpoint.GET_BOARD_URL)
            .then()
            .statusCode(200)
            .body("name", Matchers.equalTo("New Board"))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));

    }
}
