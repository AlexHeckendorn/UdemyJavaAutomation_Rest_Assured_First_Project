import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;


public class GetCardsTest extends BaseTest{

    @Test
    public void checkGetCards(){
        requestWithAuth()
                .queryParam("fields", "id,name")
                .pathParam("list_id", getListID())
                .get("/1/lists/{list_id}/cards")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_cards.json"));
    }
    

    @Test
    public void checkGetCard(){
        requestWithAuth()
                .queryParam("fields", "id,name")
            .pathParam("id", getCardID())
            .get("/1/cards/{id}")
            .then()
            .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
                .body("name", Matchers.equalTo("Test card"));


    }
}
