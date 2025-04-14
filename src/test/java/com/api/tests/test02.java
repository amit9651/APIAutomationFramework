package com.api.tests;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class test02 {

    @Test
    public void getApi(){

        Response response = RestAssured.given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .queryParam("page","2")
                .get("api/users");
        //System.out.println(response.asPrettyString());
        //int size = response.jsonPath()
        int size = response.jsonPath().getInt("per_page");
        JsonPath jsonPath = response.jsonPath();
        //String[] li = jsonPath.
        List<String> lastName = jsonPath.getList("data.last_name");
        System.out.println(lastName);


    }
}






















