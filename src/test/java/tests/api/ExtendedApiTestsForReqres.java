package tests.api;

import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import models.reqresmodel.createandupdateuser.CreateUpdateUserRequest;
import models.reqresmodel.createandupdateuser.CreateUserResponse;
import models.reqresmodel.getsingleresource.GetSingleResourceModel;
import models.reqresmodel.getusers.GetUsersModel;
import models.reqresmodel.register.RegisterRequestModel;
import models.reqresmodel.register.RegisterResponseModel;
import models.reqresmodel.createandupdateuser.UpdateResponseModel;
import specs.ReqresSpec;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;
import static specs.ReqresSpec.*;

@Tag("API")
@DisplayName("Тесты на создание, изменение и получение пользователя")
public class ExtendedApiTestsForReqres extends TestBaseForApiTest {

    ReqresSpec reqresSpec = new ReqresSpec();

    @DisplayName("Тест на проверку списка юзеров")
    @Test
    void listResourcesCheckResponseTest()
    {
        ResponseSpecification responseSpecification = reqresSpec.getBasicResponseSpec(200);

        GetUsersModel getUsersModel = step("Получаем список юзеров", () ->
                given(commonGetRequestSpec)
                        .queryParam("page", "2")
                        .get("/users")
                        .then()
                        .spec(responseSpecification)
                        .extract().as(GetUsersModel.class)
        );

        step("Проверяем список юзеров", () ->
        {
            assertThat(getUsersModel.getPage()).isEqualTo(2);
            assertThat(getUsersModel.getPerPage()).isEqualTo(6);
            assertThat(getUsersModel.getTotal()).isEqualTo(12);
            assertThat(getUsersModel.getTotalPage()).isEqualTo(2);
            assertThat(getUsersModel.getData()).hasSize(6);

            assertThat(getUsersModel.getSupport().getUrl())
                    .isEqualTo("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");
            assertThat(getUsersModel.getSupport().getText())
                    .isEqualTo("Tired of writing endless social media content? Let Content Caddy generate it for you.");
        });
    }

    @DisplayName("Тест на изменение пользователя")
    @Test
    void updateUserTest() {
        ResponseSpecification responseSpecification = reqresSpec.getBasicResponseSpec(200);
        CreateUpdateUserRequest requestModel = new CreateUpdateUserRequest();
        requestModel.setName("Lana");
        requestModel.setJob("QA Engineer");

        UpdateResponseModel responseModel = step("Обновляем данные пользователя", () ->
                given(commonGetRequestSpec)
                        .body(requestModel)

                        .when()
                        .put("/users/2")
                        .then()
                        .spec(responseSpecification)
                        .extract().as(UpdateResponseModel.class)
        );
        step("Проверяем, что данные успешно обновлены", () -> {
                    assertThat(responseModel.getName()).isEqualTo("Lana");
                    assertThat(responseModel.getJob()).isEqualTo("QA Engineer");
                }
        );
    }

    @DisplayName("Тест на успешную регистрацию")
    @Test
    void registerSuccessfulTest() {
        ResponseSpecification responseSpecification = reqresSpec.getBasicResponseSpec(200);
        RegisterRequestModel requestModel = new RegisterRequestModel();
        requestModel.setEmail("eve.holt@reqres.in");
        requestModel.setPassword("pistol");

        RegisterResponseModel responseModel = step("Регистрируем пользователя с валидными данными", () ->
                given(commonGetRequestSpec)
                        .body(requestModel)

                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpecification)
                        .extract().as(RegisterResponseModel.class)
        );
        step("Проверяем, что регистрация прошла успешно", () -> {
                    assertThat(responseModel.getId()).isEqualTo("4");
                    assertThat(responseModel.getToken()).isNotEmpty();
                }
        );
    }

    @DisplayName("Тест на получение единичного значения")
    @Test
    void getSingleResourceTest() {
        ResponseSpecification responseSpecification = reqresSpec.getBasicResponseSpec(200);

        GetSingleResourceModel getSingleResourceModel = step("Делаем запрос на получение значения", () ->
                given(commonGetRequestSpec)
                        .get("/unknown/2")
                        .then()
                        .spec(responseSpecification)
                        .extract().as(GetSingleResourceModel.class));

        step("Проверяем полученное значение", () -> {
                    assertThat(getSingleResourceModel.getData().getId()).isEqualTo(2);
                    assertThat(getSingleResourceModel.getData().getName()).isEqualTo("fuchsia rose");
                    assertThat(getSingleResourceModel.getData().getYear()).isEqualTo(2001);
                    assertThat(getSingleResourceModel.getData().getColor()).isEqualTo("#C74375");
                    assertThat(getSingleResourceModel.getData().getPantoneValue()).isEqualTo("17-2031");
                    assertThat(getSingleResourceModel.getSupport().getUrl())
                            .isEqualTo("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");
                    assertThat(getSingleResourceModel.getSupport().getText())
                            .isEqualTo("Tired of writing endless social media content? Let Content Caddy generate it for you.");
                }
        );
    }

    @DisplayName("Тест на создание нового пользователя")
    @Test
    void createUserTest() {
        ResponseSpecification responseSpecification = reqresSpec.getBasicResponseSpec(201);
        CreateUpdateUserRequest createUserRequest = new CreateUpdateUserRequest();
        createUserRequest.setName("morpheus");
        createUserRequest.setJob("leader");

        CreateUserResponse createUserResponse = step("Делаем запрос на создание пользователя", () ->
                given(commonGetRequestSpec)
                        .body(createUserRequest)

                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpecification)
                        .extract().as(CreateUserResponse.class));

        step("Проверяем созданного пользователя", () -> {
                    assertThat(createUserResponse.getName()).isEqualTo("morpheus");
                    assertThat(createUserResponse.getJob()).isEqualTo("leader");
                    assertThat(createUserResponse.getId()).isNotEmpty();
                    assertThat(createUserResponse.getCreatedAt()).isNotEmpty();
                }
        );
    }
}
