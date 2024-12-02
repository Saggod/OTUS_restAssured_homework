package pet;


import dbConnecttions.DbDeleteQuery;
import dto.PetDTO;
import dto.PetImgUplDTO;
import dto.PetResponseDTO;
import dto.UploadImgResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UploadImgToStoreApi;
import services.PetStoreApi;

import java.io.File;

public class PetTests {

    private static final PetStoreApi petStoreApi = new PetStoreApi();
    private static final UploadImgToStoreApi uplImgToStoreApi = new UploadImgToStoreApi();
    private static final DbDeleteQuery  dbDeleteQuery = new DbDeleteQuery();

    @Test
    public void addNewPetTest() {  //Проверка добавления нового Pet
        int id = 19;
        String name = "T-Rax";

        PetDTO petAdd = PetDTO
                .builder()
                .id(id)
                .name(name)
                .build();

        petStoreApi.petStore(petAdd)
                .extract().body().as(PetResponseDTO.class);


        PetResponseDTO petGetResponse = petStoreApi.findPetById(String.valueOf(id))
                        .extract().body().as(PetResponseDTO.class);

        Assertions.assertAll("Check add pet to store",
                () -> Assertions.assertEquals(id, petGetResponse.getId(), "Incorrect id"),
                () -> Assertions.assertEquals(name, petGetResponse.getName(), "Incorrect name")
        );

        dbDeleteQuery.deletePetTestDbQuery(id);


    }

    @Test
    public void uplImgToStoreTest() { //Проверка загрузки картинки на сервер (но как я понял загрузить можно и просто файл)
        uplImgToStoreApi.i = "2";
        File file = new File("src/main/resources/files/t1.jpg");

        PetImgUplDTO
                .builder()
                .petId(Integer.parseInt(uplImgToStoreApi.i))
                .additionalMetadata("555")
                .build();

        UploadImgResponseDTO responsePetUplImg = uplImgToStoreApi.uplImgToStore(uplImgToStoreApi.i, file)
                .extract().body().as(UploadImgResponseDTO.class);

        Assertions.assertAll("Check upload fiels",
                () -> Assertions.assertEquals(200, responsePetUplImg.getCode(), "Incorrect code"),
                () -> Assertions.assertEquals("additionalMetadata: null\n" +
                        "File uploaded to ./t1.jpg, 5070 bytes", responsePetUplImg.getMessage(), "Incorrect msg")
        );

    }
}
