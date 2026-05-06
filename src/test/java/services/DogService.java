package services;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.qameta.allure.restassured.AllureRestAssured;

public class DogService {

    public static final String PATH_BREEDS_LIST_ALL = "/breeds/list/all";
    public static final String PATH_BREEDS_IMAGES = "/breed/{breed}/images";
    public static final String PATH_BREEDS_IMAGE_RANDOM = "/breeds/image/random";


    public Response getBreedsImages(String breed) {
        return given()
                .filter(new AllureRestAssured())
                .pathParam("breed", breed)
                .when()
                .get(PATH_BREEDS_IMAGES);
    }

    public Response getBreedsListAll(String path) {
        return given()
                .filter(new AllureRestAssured())
                .when()
                .get(path);
    }

    public Response getBreedsImagesRandom() {
        return given()
                .filter(new AllureRestAssured())
                .when()
                .get(PATH_BREEDS_IMAGE_RANDOM);
    }
}