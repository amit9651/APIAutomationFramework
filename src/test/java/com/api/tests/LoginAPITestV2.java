package com.api.tests;

import com.api.base.AuthService;
import com.api.models.LoginResponse;
import com.api.models.request.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAPITestV2 {

    @Test(description = "Verify if login api is working")
    public void loginTest(){
        AuthService authService = new AuthService();
        LoginRequest loginRequest = new LoginRequest("string","string");
        Response response = authService.login(loginRequest);
        System.out.println(response.asPrettyString());
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getId());
        System.out.println(loginResponse.getUsername());
        System.out.println(loginResponse.getType());
        System.out.println(loginResponse.getRoles());
        Assert.assertNotNull(loginResponse.getToken());

    }
}
