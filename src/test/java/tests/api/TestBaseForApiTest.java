package tests.api;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class TestBaseForApiTest {

    @BeforeAll
    public static void configParams() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
