import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.DogService;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@DisplayName("GET-Lista uma única imagem aleatória da lista de Cães")
public class BreedImagesRandomTest extends BaseTest {

    DogService service = new DogService();

    @Test
    @DisplayName("Deve validar contrato")
    public void deveValidarContrato() {
        Response response = service.getBreedsImagesRandom();

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("status", equalTo("success"))
                .body(matchesJsonSchemaInClasspath("schemas/breeds-images-random-schema.json"));
    }

    @Test
    @DisplayName("Deve retornar imagem válida para raças aleatórias")
    public void deveRetornarImagemAleatoriaDeRacas() {
        Response response = service.getBreedsImagesRandom();

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("status", equalTo("success"))
                .body("$", is(instanceOf(Map.class)))
                .body("message", matchesPattern("^https://images\\.dog\\.ceo/breeds/.+\\.jpg$"));
    }
}