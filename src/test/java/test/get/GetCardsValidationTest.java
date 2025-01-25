package test.get;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.providers.AuthValidationArgumentsProvider;
import arguments.holders.CardIDValidationArgumentsHolder;
import arguments.providers.CardIdValidationArgumentsProvider;
import consts.CardEndpoint;
import consts.UrlParamValues;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;


public class GetCardsValidationTest extends BaseTest {

@ParameterizedTest
@ArgumentsSource(CardIdValidationArgumentsProvider.class)
    public void checkGetCardsWithInvalidID(CardIDValidationArgumentsHolder validationArguments){
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .get(CardEndpoint.GET_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
    Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }


    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id",UrlParamValues.EXISTING_CARD_ID)
                .get(CardEndpoint.GET_CARD_URL);
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
