package services;

import dto.PetDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class PetStoreApi  extends AbsBasePageApi{
    private static final String PET_STORE = "/pet";
    private RequestSpecification spec;

    public PetStoreApi(){
        spec = given()
                .contentType(ContentType.JSON)
                .log().all();
    }

    public ValidatableResponse petStore(PetDTO petDTO){
      return  given(spec)
                .body(petDTO)
        .when()
                .post(BASE_URL + PET_STORE)
        .then()
                .log().all();
    }

    public ValidatableResponse findPetById(String i) {
        return  given(spec)
                .when()
                .get(BASE_URL + PET_STORE + "/" + i)
                .then()
                .log().all();
    }
}
