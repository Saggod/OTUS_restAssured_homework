package store;

import dto.StoreDTO;
import dto.StoreResponseDTO;
import org.junit.jupiter.api.*;
import services.StoreOrderApi;


public class StoreOrderTest {

    private StoreOrderApi storeOrderApi = new StoreOrderApi();

    @Test()
    public void checkOrderCreate() { //Проверка создания нового OrderId, проверяем создание заказа

        int id = 11;
        int petId = 12;
        int quantity = 22;
        String shipDate = "2024-10-30T06:13:33.576Z";
        String status = "placed";
        boolean complete = true;

        StoreDTO storeOrder = StoreDTO
                .builder()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .shipDate(shipDate)
                .status(status)
                .complete(complete)
                .build();

        StoreResponseDTO responseStoreOrderCreate = storeOrderApi.storeOrder(storeOrder)
                .extract().body().as(StoreResponseDTO.class);

        Assertions.assertAll("Check create new responseStoreOrderCreate",
                () -> Assertions.assertEquals(11, responseStoreOrderCreate.getId(), "Incorrect Id"),
                () -> Assertions.assertEquals(12, responseStoreOrderCreate.getPetId(), "Incorrect petId"),
                () -> Assertions.assertEquals(22, responseStoreOrderCreate.getQuantity(), "Incorrect petId"),
                () -> Assertions.assertEquals("placed", responseStoreOrderCreate.getStatus(), "Incorrect msg"));

    }

    @Test
    public void checkOrderDelete() { //Проверка удаления существующего OrderId, проверка, что удалиться нужный нам заказ
        storeOrderApi.i = "11";

        StoreResponseDTO responseStoreOrderDelete = storeOrderApi.storeOrderDelete(storeOrderApi.i)
                .extract().body().as(StoreResponseDTO.class);
        Assertions.assertAll("Check create new responseStoreOrderCreate",
                () -> Assertions.assertEquals(200, responseStoreOrderDelete.getCode(), "Incorrect Code"),
                () -> Assertions.assertEquals(storeOrderApi.i, responseStoreOrderDelete.getMessage(), "Incorrect msg"));
    }

    @Test
    public void checkOrderDeleteNotFound() { //Проверка удаления несуществующего OrderId, при удалении несуществующего заказа, у нас ничего не удалиться
        storeOrderApi.i = "999999";

        StoreResponseDTO responseStoreOrderDelete = storeOrderApi.storeOrderDelete(storeOrderApi.i)
                .extract().body().as(StoreResponseDTO.class);
        Assertions.assertAll("Check create new responseStoreOrderCreate",
                () -> Assertions.assertEquals(404, responseStoreOrderDelete.getCode(), "Incorrect Code"),
                () -> Assertions.assertEquals("Order Not Found", responseStoreOrderDelete.getMessage(), "Incorrect msg"));
    }

    //        31:44
}
