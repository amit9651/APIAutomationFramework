package com.api.tests;

import com.api.base.AuthService;
import com.api.base.UserProfileService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetProfileTest {

    @Test(description = "Show profile to user")
    public void getUserProfile(){
        AuthService authService = new AuthService();
        String token = authService.getToken("amit","amit123");
        UserProfileService userProfileService = new UserProfileService();
        Response response = userProfileService.getProfile(token);
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        System.out.println(response.asPrettyString());
        System.out.println(userProfileResponse.getEmail());
        Assert.assertEquals(userProfileResponse.getUsername(),"amit");
    }
}
