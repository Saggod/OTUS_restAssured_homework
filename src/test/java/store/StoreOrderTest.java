package store;

import dbConnecttions.DbDeleteQuery;
import dto.StoreDTO;
import dto.StoreResponseDTO;
import org.junit.jupiter.api.*;
import services.StoreOrderApi;


public class StoreOrderTest {
    private final int id = 8;
    private final int petId = 9;
    private final int quantity = 22;
    private final int idDelete = 9;

    private StoreOrderApi storeOrderApi = new StoreOrderApi();
    private static final DbDeleteQuery dbDeleteQuery = new DbDeleteQuery();

    @Test()
    public void checkOrderCreate() { //Проверка создания нового OrderId, проверяем создание заказа
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

        storeOrderApi.storeOrderAdd(storeOrder)
                .extract().body().as(StoreResponseDTO.class);

        StoreResponseDTO responseGetRequest = storeOrderApi.storeGetOrderId(String.valueOf(id))
                        .extract().body().as(StoreResponseDTO.class);

        Assertions.assertAll("Check create new responseStoreOrderCreate",
                () -> Assertions.assertEquals(id, responseGetRequest.getId(), "Incorrect Id"),
                () -> Assertions.assertEquals(petId, responseGetRequest.getPetId(), "Incorrect petId"),
                () -> Assertions.assertEquals(quantity, responseGetRequest.getQuantity(), "Incorrect petId"),
                () -> Assertions.assertEquals("placed", responseGetRequest.getStatus(), "Incorrect msg"));

        dbDeleteQuery.deleteOrderTest(id);
    }

    @Test
    public void checkOrderDelete() { //Проверка удаления существующего OrderId, проверка, что удалиться нужный нам заказ
        StoreDTO storeOrder = StoreDTO
                .builder()
                .id(idDelete)
                .petId(petId)
                .quantity(quantity)
                .build();

        storeOrderApi.storeOrderAdd(storeOrder)
                .extract().body().as(StoreResponseDTO.class);

        storeOrderApi.storeOrderDelete(String.valueOf(idDelete))
                .extract().body().as(StoreResponseDTO.class);

        StoreResponseDTO responseGetRequest = storeOrderApi.storeGetOrderId(String.valueOf(idDelete))
                .extract().body().as(StoreResponseDTO.class);

        Assertions.assertAll("Check create new responseStoreOrderCreate",
                () -> Assertions.assertEquals( 1, responseGetRequest.getCode(), "Incorrect Code"),
                () -> Assertions.assertEquals("error", responseGetRequest.getType(), "Incorrect Type"),
                () -> Assertions.assertEquals("Order not found", responseGetRequest.getMessage(), "Incorrect msg"));
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
