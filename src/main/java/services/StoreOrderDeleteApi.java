package services;

import dto.StoreDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class StoreOrderDeleteApi{
    public String i;

    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    private static final String STORE_PATH = "/store/order/";
    private RequestSpecification spec;

    public StoreOrderDeleteApi() {
        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public ValidatableResponse storeOrderDelete() {


        return given(spec)
                .basePath(STORE_PATH + i)
                .when()
                .delete()
                .then()
                .log().all();
    }
}
