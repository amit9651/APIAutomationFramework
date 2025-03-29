package com.api.tests;

import com.api.base.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ForgotPasswordTest {

    @Test(description = "Verify user can perform forgot password")
    public void forgotPassword(){
        String email = "amit17@yopmail.com";
        AuthService authService = new AuthService();
        Response response = authService.forgotPassword(email);
        System.out.println(response.asPrettyString());

    }
}
