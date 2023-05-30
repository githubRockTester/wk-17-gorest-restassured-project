package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("users")
                .then().statusCode(200);
    }

    // 1) Test to extract all IDs
    @Test
    public void test001() {
        List<?> allIds = response.extract().path("id");
        System.out.println("The List Of All IDs : " + allIds);
    }

    // 2) Test to extract all names
    @Test
    public void test002() {
        List<String> allNames = response.extract().path("name");
        System.out.println("The List Of All Names : " + allNames);
    }

    // 3) Test to extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("name[4]");
        System.out.println("The Name On The 5th Object : " + name);
    }

    // 4) Test to extract all names having inactive status
    @Test
    public void test004() {
        List<String> allNamesWithStatusInactive = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("The List Of Names Having Inactive Status : " + allNamesWithStatusInactive);
    }

    // 5) Test to extract all genders having active status
    @Test
    public void test005() {
        List<String> allGendersWithStatusActive = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("The List Of All Genders Having Active Status : " + allGendersWithStatusActive);
    }

    // 6) Test to extract all names having female gender
    @Test
    public void test006() {
        List<String> allNamesWithGenderFemale = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("The List Of All Names Having Female Gender : " + allNamesWithGenderFemale);
    }

    // 7) Test to extract all emails having inactive status
    @Test
    public void test007() {
        List<String> allEmailsWithStatusInactive = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("The List Of Emails having Inactive Status : " + allEmailsWithStatusInactive);
    }

    // 8) Test to extract all IDs having male gender
    @Test
    public void test008() {
        List<?> allIdsWithMaleGender = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("The List Of All IDs Having Male Gender : " + allIdsWithMaleGender);
    }

    // 9) Test to extract all statuses
    @Test
    public void test009() {
        List<String> allStatuses = response.extract().path("status");
        System.out.println("The List Of All Statuses : " + allStatuses);
    }

    // 10) Test to extract email of a name
    @Test
    public void test010() {
        System.out.println("The Email Of Keerti Chaturvedi : " + response.extract().path("findAll{it.name == 'Keerti Chaturvedi'}.email"));
    }

    // 11) Test to extract gender of ID 2329079
    @Test
    public void test011() {
        System.out.println("The Gender Of ID 2329079 : " + response.extract().path("findAll{it.id == 2329079}.gender"));
    }
}
