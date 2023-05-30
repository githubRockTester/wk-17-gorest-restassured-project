package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.hasItem;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    // 1) Verify the if the total record is 25
    @Test
    public void test001() {
        response.body(("size"), equalTo(10));
    }

    // 2) Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum."
    @Test
    public void test002() {
        response.body("findAll{it.id == 40104}.title", hasItem("Vox admiratio turpis complectus adversus socius."));
    }

    // 3) Test to verify that a particular ID exists or not
    @Test
    public void test003() {
        response.body("findAll{it}.user_id", hasItem(2329082));
    }

    // 4) Test to verify That multiple IDs exist
    @Test
    public void test004() {
        response.body("findAll{it}.id", hasItems(40101, 40103, 40105));
    }

    // 5) Test to verify the body of an ID
    @Test
    public void test005() {
        response.body("findAll{it.id == 40105}.body", hasItem("Cohaero at cavus. Administratio callide voluptates. Blandior conservo virga. Tunc aer vorax. Commodi vomer aeger. Clam numquam texo. Viridis aggredior anser. Utilis sollicito communis. Armarium communis considero. Vulgo asper unde. Apparatus aegre cilicium. Vulgus dapifer bardus. Supra tego demens."));
    }
}
