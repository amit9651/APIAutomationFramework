package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpUserTest {
    Faker faker = new Faker();
    @Test(description = "Verify user can sign up")
    public void signUpTest(){
        String username = faker.name().username();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String mobile = faker.number().digits(10);

        AuthService authService = new AuthService();
        System.out.println(username+" "+password+" "+email+" "+firstName+" "+lastName+" "+mobile);
        SignUpRequest signUpRequest = new SignUpRequest(username,password,email,firstName,lastName,mobile);
        Response response = authService.signUp(signUpRequest);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.asPrettyString(),"User registered successfully!");

    }
}
