package test.get;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.holders.BoardIDValidationArgumentsHolder;
import arguments.providers.BoardIdValidationArgumentsProvider;
import consts.BoardEndpoint;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;


public class GetBoardsValidationTest extends BaseTest {


@ParameterizedTest
@ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkGetBoardsWithInvalidID(BoardIDValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .pathParams(validationArguments.getPathParams())
                .get(BoardEndpoint.GET_BOARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
    Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }


    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id",UrlParamValues.EXISTING_BOARD_ID)
                .get(BoardEndpoint.GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }


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
                .queryParam("fields", "id,name")
                .pathParam("id", UrlParamValues.EXISTING_BOARD_ID)
                .get(BoardEndpoint.GET_BOARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("New Board"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));

    }

     /*
    //Need to use a different user key & token for this to return passed
    @Test
    public void checkGetBoardWithAnotherUserCredential(){
        Response response = requestWithoutAuth()
                .queryParams(UrlParamValues.ANOTHER_USER_AUTH_QUERY_PARAMS)
                .pathParam("id",UrlParamValues.EXISTING_BOARD_ID)
                .get(BoardEndpoint.GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
*/

}
