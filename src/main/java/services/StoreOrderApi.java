package services;


import dto.StoreDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;


public class StoreOrderApi extends AbsBasePageApi {
    public String i;
    private static final String STORE_PATH = "/store/order";
    private RequestSpecification spec;

    public StoreOrderApi() {
        spec = given()
                .contentType(ContentType.JSON)
                .log().all();
    }

    public ValidatableResponse storeOrderAdd(StoreDTO storeDTO) {
        return given(spec)
                    .body(storeDTO)
                .when()
                    .post(BASE_URL + STORE_PATH)
                .then()
                    .log().all();
    }

    public ValidatableResponse storeGetOrderId(String i) {
        return given(spec)
                .when()
                .get(BASE_URL + STORE_PATH + "/" + i)
                .then()
                .log().all();
    }

    public ValidatableResponse storeOrderDelete(String i) {
        return given(spec)
                .when()
                .delete(BASE_URL + STORE_PATH + "/" + i)
                .then()
                .log().all();
    }


}
