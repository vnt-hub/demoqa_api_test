package com.demoqa.base;

import com.demoqa.com.data.UserData;
import com.demoqa.com.models.Books;
import com.demoqa.com.models.Credentials;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Test

public class BookStoreTests extends BaseTest {
    static Credentials credentials = new Credentials();
    static Books books = new Books();
    static UserData userData = new UserData();


    public void userExist() {
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

    public void getAllBookStore() {
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .when()
                .get("BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    public void authorizedUser() {
        credentials.setUserName(userData.getUserName());
        credentials.setPassword(userData.getPassword());
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(credentials)
                .when()
                .post("Account/v1/Authorized")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(is("true"));
    }

    public void generateToken() {
        credentials.setUserName(userData.getUserName());
        credentials.setPassword(userData.getPassword());
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(credentials)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    public void addListOfBooks() {
        books.setUserId(userData.getUserid());
        books.setIsbn(userData.getIsbn());
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(books)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body();
    }
}
