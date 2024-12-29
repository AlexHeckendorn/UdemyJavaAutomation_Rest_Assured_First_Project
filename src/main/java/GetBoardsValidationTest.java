import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class GetBoardsValidationTest extends BaseTest {


@Test
    public void checkGetBoardsWithInvalidID(){
        Response response = requestWithoutAuth()
                .pathParam("id","invalid")
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(400);
    Assertions.assertEquals("invalid id", response.body().asString());
    }


    @Test
    public void checkGetBoardWithInvalidAuth(){
        Response response = requestWithoutAuth()
                .pathParam("id",getBoardID())
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }

 /*
    //Need to use a different user key & token for this to return passed
    @Test
    public void checkGetBoardWithAnotherUserCredential(){
        Response response = requestWithoutAuth()
                .queryParams(Map.of(
                        "key", " ",
                        "token", " "
                ))
                .pathParam("id",getBoardID())
                .get("/1/boards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
*/


    @Test
    public void checkGetBoards(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("member", getMemberID())
                .get("/1/members/{member}/boards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_boards.json"));
    }


    @Test
    public void checkGetBoard(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("id", getBoardID())
            .get("/1/boards/{id}")
            .then()
            .statusCode(200)
            .body("name", Matchers.equalTo("New Board"))
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));

    }
}
