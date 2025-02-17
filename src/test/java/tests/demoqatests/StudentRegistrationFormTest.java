package tests.demoqatests;

import helpers.Attachments;
import org.junit.jupiter.api.*;
import pages.RegistrationFormPage;
import utils.RegistrationFormTestData;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на форму регистрации")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("WEB")

public class StudentRegistrationFormTest extends TestBaseDemo {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    RegistrationFormTestData registrationFormTestData = new RegistrationFormTestData();

    @Test
    @DisplayName("Введение валидных данных во все инпуты на форме регистрации")
    @Order(2)
    @Tags({
            @Tag("REGRESS"),
            @Tag("POSITIVE")
    }
    )
    void allInputsTest() {
        step("Заполняем форму регистрации", () -> {
            registrationFormPage
                    .setFirstName(registrationFormTestData.firstName)
                    .setLastName(registrationFormTestData.lastName)
                    .setUserEmail(registrationFormTestData.userEmail)
                    .chooseGender(registrationFormTestData.gender)
                    .setUserNumber(registrationFormTestData.userNumber)
                    .setDateOfBirth(registrationFormTestData.monthOfBirth, registrationFormTestData.yearOfBirth, registrationFormTestData.dayOfBirth)
                    .addSubject(registrationFormTestData.subject)
                    .chooseHobby(registrationFormTestData.hobby)
                    .choosePicture(registrationFormTestData.picture)
                    .setCurrentAddress(registrationFormTestData.currentAddress)
                    .chooseStateAndCity(registrationFormTestData.state, registrationFormTestData.city)
                    .clickSubmit();
        });

        step("Проверяем заполненную форму", () -> {
            registrationFormPage.checkForm("Student Name", registrationFormTestData.firstName + " " + registrationFormTestData.lastName)
                    .checkForm("Student Email", registrationFormTestData.userEmail)
                    .checkForm("Gender", registrationFormTestData.gender)
                    .checkForm("Mobile", registrationFormTestData.userNumber)
                    .checkForm("Date of Birth", registrationFormTestData.dayOfBirth + " " + registrationFormTestData.monthOfBirth + ',' + registrationFormTestData.yearOfBirth)
                    .checkForm("Subjects", registrationFormTestData.subject)
                    .checkForm("Hobbies", registrationFormTestData.hobby)
                    .checkForm("Picture", registrationFormTestData.picture)
                    .checkForm("Address", registrationFormTestData.currentAddress)
                    .checkForm("State and City", registrationFormTestData.state + " " + registrationFormTestData.city);
        });

        Attachments.addScreenshot("TestScreenShot");
        Attachments.addPageSource();

        step("Закрываем форму", () -> {
            registrationFormPage.clickClose();
        });

        Attachments.addBrowserConsoleLogs();
        Attachments.addVideo();

    }


    @Test
    @DisplayName("Введение валидных данных в обязательные инпуты на форме регистрации")
    @Order(1)
    @Tags({
            @Tag("SMOKE"),
            @Tag("POSITIVE")
    }
    )
    void minInputsTest() {
        registrationFormPage
                .setFirstName(registrationFormTestData.firstName)
                .setLastName(registrationFormTestData.lastName)
                .chooseGender(registrationFormTestData.gender)
                .setUserNumber(registrationFormTestData.userNumber)
                .clickSubmit();

        registrationFormPage.checkForm("Student Name", registrationFormTestData.firstName + " " + registrationFormTestData.lastName)
                .checkForm("Gender", registrationFormTestData.gender)
                .checkForm("Mobile", registrationFormTestData.userNumber);

        registrationFormPage.clickClose();
    }


    @Test
    @DisplayName("Введение некорректного номера телефона")
    @Order(3)
    @Tag("NEGATIVE")
    void incorrectNumberTest() {
        registrationFormPage
                .setFirstName(registrationFormTestData.firstName)
                .setLastName(registrationFormTestData.lastName)
                .chooseGender(registrationFormTestData.gender)
                .setUserNumber(registrationFormTestData.incorrectNumber)
                .clickSubmit();

        registrationFormPage.mainFormCheck("Student Registration Form");
    }

}

