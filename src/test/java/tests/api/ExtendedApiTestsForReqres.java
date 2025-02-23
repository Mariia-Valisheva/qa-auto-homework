package tests.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.model.reqresmodel.createandupdateuser.CreateUpdateUserRequest;
import utils.model.reqresmodel.createandupdateuser.CreateUserResponse;
import utils.model.reqresmodel.getsingleresource.GetSingleResourceModel;
import utils.model.reqresmodel.getusers.GetUsersModel;
import utils.model.reqresmodel.register.RegisterRequestModel;
import utils.model.reqresmodel.register.RegisterResponseModel;
import utils.model.reqresmodel.createandupdateuser.UpdateResponseModel;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;
import static specs.ReqresSpec.*;

@Tag("API")
@DisplayName("Тесты на создание, изменение и получение пользователя")
public class ExtendedApiTestsForReqres extends TestBaseForApiTest {

    @DisplayName("Тест на проверку списка юзеров")
    @Test
    void listResourcesCheckResponseTest() {

        GetUsersModel getUsersModel = step("Получаем список юзеров", () ->
                given(commonGetRequestSpec)
                        .queryParam("page", "2")
                        .get("/users")
                        .then()
                        .spec(basicResponseSpec)
                        .extract().as(GetUsersModel.class)
        );

        step("Проверяем список юзеров", () ->
        {
            assertThat(getUsersModel.getPage()).isEqualTo(2);
            assertThat(getUsersModel.getPer_page()).isEqualTo(6);
            assertThat(getUsersModel.getTotal()).isEqualTo(12);
            assertThat(getUsersModel.getTotal_pages()).isEqualTo(2);
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
        CreateUpdateUserRequest requestModel = new CreateUpdateUserRequest();
        requestModel.setName("Lana");
        requestModel.setJob("QA Engineer");

        UpdateResponseModel responseModel = step("Обновляем данные пользователя", () ->
                given(commonGetRequestSpec)
                        .body(requestModel)

                        .when()
                        .put("/users/2")
                        .then()
                        .spec(basicResponseSpec)
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
        RegisterRequestModel requestModel = new RegisterRequestModel();
        requestModel.setEmail("eve.holt@reqres.in");
        requestModel.setPassword("pistol");

        RegisterResponseModel responseModel = step("Регистрируем пользователя с валидными данными", () ->
                given(commonGetRequestSpec)
                        .body(requestModel)

                        .when()
                        .post("/register")
                        .then()
                        .spec(basicResponseSpec)
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

        GetSingleResourceModel getSingleResourceModel = step("Делаем запрос на получение значения", () ->
                given(commonGetRequestSpec)
                        .get("/unknown/2")
                        .then()
                        .spec(basicResponseSpec)
                        .extract().as(GetSingleResourceModel.class));

        step("Проверяем полученное значение", () -> {
                    assertThat(getSingleResourceModel.getData().getId()).isEqualTo(2);
                    assertThat(getSingleResourceModel.getData().getName()).isEqualTo("fuchsia rose");
                    assertThat(getSingleResourceModel.getData().getYear()).isEqualTo(2001);
                    assertThat(getSingleResourceModel.getData().getColor()).isEqualTo("#C74375");
                    assertThat(getSingleResourceModel.getData().getPantone_value()).isEqualTo("17-2031");
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
        CreateUpdateUserRequest createUserRequest = new CreateUpdateUserRequest();
        createUserRequest.setName("morpheus");
        createUserRequest.setJob("leader");

        CreateUserResponse createUserResponse = step("Делаем запрос на создание пользователя", () ->
                given(commonGetRequestSpec)
                        .body(createUserRequest)

                        .when()
                        .post("/users")
                        .then()
                        .spec(createResponseSpec)
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
