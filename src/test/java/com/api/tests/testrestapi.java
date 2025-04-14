package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class testrestapi {

    public static void main(String[] args) {
        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON).log().all()
                .get("/todos/1")
                .then()
                .spec(responseSpecification).log().all();
        Response response = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON).log().all()
                .get("/todos/1");
        System.out.println(response.jsonPath().getInt("userId"));
        System.out.println(response.jsonPath().getString("title"));
    }
}
