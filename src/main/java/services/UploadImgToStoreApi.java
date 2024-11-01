package services;

import dto.PetImgUplDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadImgToStoreApi {
    public String i;

    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static final String USER_PATH = "/pet/{petId}/uploadImage";
    private RequestSpecification spec;

    public UploadImgToStoreApi(){
        spec = given()
                .baseUri(BASE_URI)
                .contentType("multipart/form-data")
                .log().all();
    }


    public ValidatableResponse uplImgToStore(String petId, File file) {
        return  given(spec)
                .pathParam("petId", petId) // замените на ID питомца
                .multiPart("file", file)   // добавляем файл к запросу
                .when()
                .post(USER_PATH)           // отправляем POST запрос
                .then()
                .log().all();
    }
}
