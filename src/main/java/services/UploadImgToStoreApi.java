package services;

import dto.PetImgUplDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadImgToStoreApi extends AbsBasePageApi{
    public String i;

    private static final String USER_PATH = "/pet/{petId}/uploadImage";
    private RequestSpecification spec;

    public UploadImgToStoreApi(){
        spec = given()
                .contentType("multipart/form-data")
                .log().all();
    }


    public ValidatableResponse uplImgToStore(String petId, File file) {
        return  given(spec)
                .pathParam("petId", petId) // замените на ID питомца
                .multiPart("file", file)   // добавляем файл к запросу
                .when()
                .post(BASE_URL + USER_PATH)           // отправляем POST запрос
                .then()
                .log().all();
    }
}
