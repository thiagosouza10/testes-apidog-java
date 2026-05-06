import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import services.DogService;
import java.util.Map;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.DisplayName;

@DisplayName("GET-Listagem de raças de cães")
public class BreedListTest extends BaseTest {

    DogService service = new DogService();

    @Test
    @DisplayName("Deve retornar todas as raças de cães disponíveis")
    public void deveListarTodasAsRacas() {
        Response response = service.getAllBreeds(DogService.PATH_BREEDS_LIST_ALL);

        response.then()
                .statusCode(200)
                .body("status", equalTo("success"));

        response.then().body("message", is(notNullValue()));
        Map<String, Object> breeds = response.path("message");
        assertThat(breeds, is(instanceOf(Map.class)));
        assertThat(breeds.size(), is(greaterThan(0)));
    }

    @Test
    @DisplayName("Deve validar raça e sub-raças do bulldog")
    public void deveValidarRacaESubRacasDoBulldog() {
        Response response = service.getAllBreeds(DogService.PATH_BREEDS_LIST_ALL);

        response.then()
                .statusCode(200)
                .body("status", equalTo("success"));

        response.then().body("message.bulldog", is(instanceOf(List.class)));
        response.then().body("message.bulldog", hasItem("french"));
        List<String> subRacasBulldog = response.path("message.bulldog");
        assertThat(subRacasBulldog, is(not(empty())));
        assertThat(subRacasBulldog, hasItem("french"));
    }

    @Test
    @DisplayName("Deve retornar 404 para endpoint inválido")
    public void naoDeveListarRacas() {
        Response response = service.getAllBreeds("/breeds/list/invalid");
        response.then().statusCode(404);
    }
}