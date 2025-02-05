package test.get;

import consts.CardEndpoint;
import consts.UrlParamValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;


public class GetCardsTest extends BaseTest {

    @Test
    public void checkGetCards(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("list_id", UrlParamValues.EXISTING_LIST_ID)
                .get(CardEndpoint.GET_ALL_CARDS_URL)
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }
    

    @Test
    public void checkGetCard(){
        requestWithAuth()
                .queryParam("fields", "id,name")
            .pathParam("id", UrlParamValues.EXISTING_CARD_ID)
            .get(CardEndpoint.GET_CARD_URL)
            .then()
            .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
                .body("name", Matchers.equalTo("Test card"));


    }
}
