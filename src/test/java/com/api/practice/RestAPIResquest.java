package com.api.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class RestAPIResquest {
    int id;

    @Test(priority = 1)
    void getRequest(){

        given()
                    .when()
                    .get("https://reqres.in/api/users?page=2")
                    .then()
                    .statusCode(200)
                    .body("page",equalTo(2))
                    .body("data[0].first_name",equalTo("Michael"))
                    .log()
                    .all();


    }

    @Test(priority = 2)
    void getRequestList(){

        List<String> list =  given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .jsonPath().getList("data.id");
        System.out.println(list);



    }

    @Test(priority = 3)
    void createUser(){

        HashMap<String,String> map = new HashMap<>();
        map.put("name","morpheus");
        map.put("job","leader");
        id = given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
                //.then()
                //.statusCode(201)
                //.log().all();

        System.out.println(id);

    }

    @Test(priority = 4, dependsOnMethods = {"createUser"})
    void updateUser(){

        HashMap<String,String> map = new HashMap<>();
        map.put("name","morpheus");
        map.put("job","politician");
        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .put("https://reqres.in/api/users/"+id)
                .then()
                        .log().all();
        //.then()
        //.statusCode(201)
        //.log().all();

        System.out.println(id);

    }

    @Test(priority = 5,dependsOnMethods = {"createUser"})
    void deleteRequest(){
        given()
                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then().statusCode(204).log().all();

    }


}
