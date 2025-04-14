package com.api.practice;

import java.io.File;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FileUploadRest {
    String user = System.getProperty("user.dir");
    @Test
    public void singleFileUpload(){
        File file = new File("/Users/amitsingh/IdeaProjects/APIAutomationFramework/src/test/resources/file2.png");
        System.out.println(file.getPath());
        given()
                .header("Authorization","Bearer dashboard-token-C3bc6KpnTd69VPjNPAnu8ThitXqmiWj6jSs7wEWyXke3TOOC9g-H61o7HLCY0WMY1aX9DAAg4Y0QzYupHxrHwJZFt-EGy2bHkL2JAoleZLbwVDuKh1SGRCVjGTFaSovb")
                .multiPart("file",file)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadFile")
                .then().log().all();
        //System.out.println(response.asPrettyString());

    }
}
