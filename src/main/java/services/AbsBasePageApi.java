package services;


import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class AbsBasePageApi {
    private RequestSpecification spec;

    public String BASE_URL = !System.getProperty("base.url").endsWith("/") ?
            System.getProperty("base.url")
            : System.getProperty("base.url")
            .substring(0, System.getProperty("base.url").length() - 1);



}
