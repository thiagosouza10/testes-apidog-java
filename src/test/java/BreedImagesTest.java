import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.DogService;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@DisplayName("GET-Listagem de imagens de uma raça específica")
public class BreedImagesTest extends BaseTest {

    DogService service = new DogService();

    @Test
    @DisplayName("Deve validar contrato")
    public void deveValidarContrato() {
        Response response = service.getBreedsImages("bulldog");

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("status", equalTo("success"))
                .body(matchesJsonSchemaInClasspath("schemas/breeds-images-schema.json"));
    }

    @Test
    @DisplayName("Deve retornar imagens para raça válida")
    public void deveRetornarImagensDaRaca() {
        Response response = service.getBreedsImages("bulldog");

        response.then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("status", equalTo("success"))
                .body("message", is(instanceOf(List.class)))
                .body("message.size()", is(greaterThan(0)))
                .body("message", everyItem(matchesPattern("^https://images\\.dog\\.ceo/breeds/bulldog.*\\.jpg$")));
    }

    @Test
    @DisplayName("Não Deve retornar imagens para raça que não existe")
    public void naoDeveRetornarImagensDeRacaInexistente() {
        Response response = service.getBreedsImages("gato");

        response.then()
                .statusCode(404)
                .header("Content-Type", containsString("application/json"))
                .body("status", is("error"))
                .body("message", is("Breed not found (main breed does not exist)"))
                .body("code", is(404));
    }

    @Test
    @DisplayName("Não Deve retornar imagens sem parâmetro da raça")
    public void naoDeveRetornarImagensSemRaca() {
        Response response = service.getBreedsImages("");

        response.then()
                .statusCode(404)
                .header("Content-Type", containsString("application/json"))
                .body("status", is("error"))
                .body("message", is("Breed not found (main breed does not exist)"))
                .body("code", is(404));
    }
}