package com.api.tests;

import com.api.base.AuthService;
import com.api.base.UserProfileService;
import com.api.models.request.UpdateUserProfileRequest;
import com.api.models.response.UserProfileResponse;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProfileTest {
    Faker faker = new Faker();
    @Test
    public void updateProfileAll(){
        AuthService authService = new AuthService();
        String token = authService.getToken("amit","amit123");
        System.out.println("Auth Token: "+token);
        System.out.println("-----------------------------------------------------------");
        UserProfileService userProfileService = new UserProfileService();
        Response response = userProfileService.getProfile(token);
        System.out.println(response.asPrettyString());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        Assert.assertEquals(userProfileResponse.getUsername(),"amit");
        System.out.println("-----------------------------------------------------------");
        UpdateUserProfileRequest updateUserProfileRequest = new UpdateUserProfileRequest.Builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .mobileNumber(faker.number().digits(10))
                .build();
        response = userProfileService.updateProfile(token,updateUserProfileRequest);
        System.out.println(response.asPrettyString());
    }
}
