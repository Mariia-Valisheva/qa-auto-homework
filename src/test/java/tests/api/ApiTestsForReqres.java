package tests.api;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

public class ApiTestsForReqres extends TestBaseForApiTest {


    @Test
    void listResourcesCheckStatusCodeTest() {
        given()
                .log().uri()
                .get("/unknown")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    void listResourcesCheckAmountOfObjectsTest() {
        given()
                .log().uri()
                .get("/unknown")
                .then()
                .log().all()
                .body("data", hasSize(6));
    }

    @Test
    void listResourcesCheckResponseTest() {
        given()
                .log().uri()
                .get("/unknown")
                .then()
                .log().all()
                .body("page", is(1))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("total_pages", is(2));

    }

    @Test
    void deleteUserTest() {
        given()
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().all()
                .statusCode(204);

    }

    @Test
    void updateUserTest() {
        String bodyValues = "{\"name\": \"Lana\",\"job\": \"QA Engineer\"}";
        given()
                .body(bodyValues)
                .contentType(JSON)
                .log().all()

                .when()
                .put("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Lana"))
                .body("job", is("QA Engineer"));

    }

    @Test
    void registerSuccessfulTest() {
        String bodyValues = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}";
        given()
                .body(bodyValues)
                .contentType(JSON)
                .log().all()

                .when()
                .post("/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }
}

