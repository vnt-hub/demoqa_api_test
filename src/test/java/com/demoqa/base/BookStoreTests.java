package com.demoqa.base;

import com.demoqa.com.data.UserData;
import com.demoqa.com.models.Credentials;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class BookStoreTests extends BaseTest {
    static Credentials credentials = new Credentials();
    static UserData userData = new UserData();

    @Test
    @Description("Check exist user")
    void checkUserExist() {
        credentials.setUserName(userData.getUserName());
        credentials.setPassword(userData.getPassword());
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(credentials)
                .when()
                .post("/Account/v1/User")
                .then()
                .log().status()
                .log().body()
                .statusCode(406)
                .body("message", is("User exists!"))
                .body("code", is("1204"));
    }
}
