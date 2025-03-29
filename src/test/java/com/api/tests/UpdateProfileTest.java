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
    @Test(description = "Verify User is able to update profile")
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
                .email(faker.lorem().characters(4,false,false)+"@yopmail.com")
                .mobileNumber(faker.number().digits(10))
                .build();
        response = userProfileService.updateProfile(token,updateUserProfileRequest);
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.asPrettyString());
    }
}
