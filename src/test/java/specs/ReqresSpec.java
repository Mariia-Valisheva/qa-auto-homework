package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class ReqresSpec {

    public static RequestSpecification commonGetRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public ResponseSpecification getBasicResponseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(expectedStatusCode)
                .build();
    }
}

