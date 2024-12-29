import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    private static final Properties trelloApiKeys = new Properties();

    static{
        loadAPIKeys();
    }

    private static void loadAPIKeys() {
        try (InputStream input = BaseTest.class.getClassLoader().getResourceAsStream("apiKeys/trelloAPIKeys.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find trelloAPIKeys.properties");
                return;
            }
            // Load the properties file
            trelloApiKeys.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getApiKey(String key) {
        return trelloApiKeys.getProperty(key);
    }


    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.trello.com";
    }


    protected RequestSpecification requestWithAuth() {
        String apiKey = getApiKey("trello.apiKey");
        String token = getApiKey("trello.apiToken");

        if (apiKey == null || token == null) {
            throw new RuntimeException("API key or token is missing in the properties file.");
        }
        return requestWithoutAuth()
                .queryParams(Map.of(
                        "key", apiKey,
                        "token", token
                ));
    }

    protected static RequestSpecification requestWithoutAuth() {
        return RestAssured.given();
    }

    // Methods to retrieve specific IDs from the properties file
    public static String getCardID() {
        return getRequiredProperty("trello.cardID");
    }

    public static String getBoardID() {
        return getRequiredProperty("trello.boardID");
    }

    public static String getMemberID() {
        return getRequiredProperty("trello.memberID");
    }

    public static String getListID() {
        return getRequiredProperty("trello.listID");
    }

    // Helper method to handle missing properties
    private static String getRequiredProperty(String key) {
        String value = trelloApiKeys.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing required property: " + key);
        }
        return value;
    }


}
