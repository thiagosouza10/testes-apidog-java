package services;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.qameta.allure.restassured.AllureRestAssured;

public class DogService {

    public static final String PATH_BREEDS_LIST_ALL = "/breeds/list/all";
    public static final String PATH_BREEDS_IMAGE_RANDOM = "/breeds/image/random";


    public Response getRandomImage(String path) {
        return given()
                .filter(new AllureRestAssured())
                .when()
                .get(path);
    }

    public Response getAllBreeds(String path) {
        return given()
                .filter(new AllureRestAssured())
                .when()
                .get(path);
    }

}