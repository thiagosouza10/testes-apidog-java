package base;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        baseURI = "https://dog.ceo/api";
    }
}