package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAPITestV1 {

    @Test(description = "Verify if login api is working")
    public void loginTest(){
        Response response = RestAssured.given().baseUri("http://64.227.160.186:8080")
                .when()
                .header("Content-Type","application/json")
                .body("{\"username\": \"string\",\"password\": \"string\"}").post("/api/auth/login");
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
