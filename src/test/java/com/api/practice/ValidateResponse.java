package com.api.practice;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class ValidateResponse {


    @Test
    public void getResponseValidationUsingThen(){

        given()
                .filter(new AllureRestAssured())
                .when()
                .get("http://localhost:3000/student/")
                .then().statusCode(200).body("[4].name",equalTo("Michael Brown"));


    }

    @Test
    public void getResponseValidation(){

        Response response = given().filter(new AllureRestAssured())
                .when()
                .get("http://localhost:3000/student/");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getHeader("Content-Type"),"application/json");

        List<String> name = response.jsonPath().getList("name");
        System.out.print("names: "+name);
        String fname = response.jsonPath().get("[0].name");
        System.out.println("\n"+fname);
        List<List<String[]>> courseList = response.jsonPath().getList("courses");
        System.out.println(courseList);
        List<String> location = response.jsonPath().get("location");
        System.out.println(location);
        Assert.assertTrue(response.jsonPath().get("[4].name").equals("Michael Brown"));
        String mobile = response.jsonPath().get("[0].phone");
        Assert.assertEquals(mobile,"+1-555-123-4567");
        System.out.println(mobile);


    }

    @Test
    public void getResponseValidationComplexJson(){

        Response response = given().filter(new AllureRestAssured())
                .when()
                .get("http://localhost:3000/studentComplex");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getHeader("Content-Type"),"application/json");

       List<String> depCourses = response.jsonPath().get("department.courses.course_name");
        System.out.println(depCourses);


    }

    @Test
    public void getResponseValidationUsingObject(){

        Response response = given().filter(new AllureRestAssured())
                .when()
                .get("http://localhost:3000/studentComplex");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getHeader("Content-Type"),"application/json");

       // System.out.println(response.asPrettyString());
        JSONArray jsonArray = new JSONArray(response.asString());
        for(int i=0;i<jsonArray.length();i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.print(jsonObject.getString("id"));
            System.out.print(jsonObject.getString("name"));
            System.out.print(jsonObject.getString("location"));
            System.out.println(jsonObject.getString("phone"));

            JSONObject address = jsonObject.getJSONObject("address");
            System.out.print(address.getString("street"));
            System.out.print(address.getString("city"));
            System.out.print(address.getString("zip"));
            System.out.println(address.getString("country"));

            JSONObject enrollment = jsonObject.getJSONObject("enrollment");
            System.out.print(enrollment.getString("semester"));
            System.out.println(enrollment.getString("status"));

            JSONObject department = jsonObject.getJSONObject("department");
            System.out.print(department.getString("dept_name"));
            System.out.println(department.getString("head"));

            JSONArray courses = department.getJSONArray("courses");
            for(int j=0;j<courses.length();j++){

                JSONObject course = courses.getJSONObject(j);
                System.out.print(course.getString("course_id"));
                System.out.print(course.getString("course_name"));
                System.out.print(course.getString("instructor"));
                System.out.println(course.getString("schedule"));


            }

        }


    }


    @Test
    public void verifyJSONSchema(){

        given()
                .contentType(ContentType.JSON)
                .when().get("http://localhost:3000/studentComplex")
                .then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("student.json")).log().all();

    }
}
