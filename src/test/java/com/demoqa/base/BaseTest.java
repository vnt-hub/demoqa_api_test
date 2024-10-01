package com.demoqa.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;


public class BaseTest {
    @BeforeClass
    static void setUp() {
        RestAssured.baseURI = "https://demoqa.com/";
    }
}
