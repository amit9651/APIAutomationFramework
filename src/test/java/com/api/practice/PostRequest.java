package com.api.practice;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest {

    @Test(priority = 3)
    void createUser(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","amit");
        jsonObject.put("job","doctor");
                given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then().statusCode(201)
                        .body("name",equalTo("amit"))
                        .body("job",equalTo("doctor")).log().all();




    }

    @Test
    void getRequest(){
        given()
                .pathParam("value","users")
                .queryParam("page",2)
                .queryParam("id",5)
                .when()
                .get("https://reqres.in/api/{value}")
                .then().statusCode(200).log().all();
    }


}
