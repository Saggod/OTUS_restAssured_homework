package services;

import dto.PetDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetStoreApi {
    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static final String USER_PATH = "/pet";
    private RequestSpecification spec;

    public PetStoreApi(){
        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .log().all();
    }


    public ValidatableResponse petStore(PetDTO petDTO){

      return  given(spec)
                .basePath(USER_PATH)
                .body(petDTO)
        .when()
                .post()
        .then()
                .log().all();
    }
}
