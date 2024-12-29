import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GetCardsValidationTest extends BaseTest{

@Test
    public void checkGetCardsWithInvalidID(){
        Response response = requestWithAuth()
                .pathParam("id","invalid")
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(400);
    Assertions.assertEquals("invalid id", response.body().asString());
    }


    @Test
    public void checkGetCardWithInvalidAuth(){
        Response response = requestWithoutAuth()
                .pathParam("id",getCardID())
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
    }

 /*
    //Need to use a different user key & token for this to return passed
    @Test
    public void checkGetCardWithAnotherUserCredential(){
        Response response = requestWithoutAuth()
                .queryParams(Map.of(
                        "key", " ",
                        "token", " "
                ))
                .pathParam("id",getCardID())
                .get("/1/cards/{id}");
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals("invalid token", response.body().asString());
    }
*/

}
