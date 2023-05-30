package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasItem;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
              /*  .queryParam("page","1")
                .queryParam("per_page","20")
                .get("/users?page=1&per_page=20")*/
                .get("users")
                .then().statusCode(200);
    }

    // 1) // Test to verify that total number of records is 10
    @Test
    public void test001() {
        response.body(("size"), equalTo(10));
    }

    // 2) Test to verify that a particular id has the required name
    @Test
    public void test002() {
        response.body("find{it.id == 2329079}.name", equalTo("Prem Mukhopadhyay"));
    }

    // 3) Test to verify that a particular id has the required name
    @Test
    public void test003() {
        response.body("findAll{it.id == 2329079}.name", hasItem("Prem Mukhopadhyay"));
    }

    // 4) Test to verify that the response body has list of names
    @Test
    public void test004() {
        response.body("findAll{it.id}.name", hasItems("Keerti Chaturvedi", "Ahalya Mahajan", "Veda Arora"));
    }

    // 5) Test to verify that the id 2250475 has email as trilochana_chattopadhyay@bahringer.test
    @Test
    public void test005() {
        response.body("find{it.id == 2329077}.email", equalTo("keerti_chaturvedi@wisozk.example"));
    }

    // 6) Test to verify the status by name
    @Test
    public void test006() {
        response.body("find{it.name == 'Ahalya Mahajan'}.status", equalTo("active"));
    }

    // 7) Test to verify the gender by name
    @Test
    public void test007() {
        response.body("find{it.name == 'Prem Mukhopadhyay'}.gender", equalTo("female"));
    }
}
