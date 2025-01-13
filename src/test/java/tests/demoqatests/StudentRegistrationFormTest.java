package tests.demoqatests;

import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;
import utils.RegistrationFormTestData;

public class StudentRegistrationFormTest extends TestBaseDemo {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    RegistrationFormTestData registrationFormTestData = new RegistrationFormTestData();

    @Test
    void allInputsTest() {
        registrationFormPage.openPage()
                    .setFirstName(registrationFormTestData.firstName)
                    .setLastName(registrationFormTestData.lastName)
                    .setUserEmail(registrationFormTestData.userEmail)
                    .chooseGender(registrationFormTestData.gender)
                    .setUserNumber(registrationFormTestData.userNumber)
                    .setDateOfBirth(registrationFormTestData.dateMonth, registrationFormTestData.dateYear, "1")
                //.setDateOfBirth(registrationFormTestData.dateMonth, registrationFormTestData.dateYear, registrationFormTestData.dateDay)
                    .addSubject(registrationFormTestData.subject)
                    .chooseHobby(registrationFormTestData.hobby)
                    .choosePicture(registrationFormTestData.picture)
                    .setCurrentAddress(registrationFormTestData.currentAddress)
                    .chooseStateAndCity(registrationFormTestData.state, registrationFormTestData.city)
                    .clickSubmit();

        registrationFormPage.checkForm("Student Name", registrationFormTestData.firstName + " " + registrationFormTestData.lastName)
                        .checkForm("Student Email", registrationFormTestData.userEmail)
                        .checkForm("Gender", registrationFormTestData.gender)
                        .checkForm("Mobile", registrationFormTestData.userNumber)
                        //.checkForm("Date of Birth", "01 November,1996")
                        .checkForm("Subjects", registrationFormTestData.subject)
                        .checkForm("Hobbies", registrationFormTestData.hobby)
                        .checkForm("Picture", registrationFormTestData.picture)
                        .checkForm("Address", registrationFormTestData.currentAddress)
                        .checkForm("State and City", registrationFormTestData.state + " " + registrationFormTestData.city);

        registrationFormPage.clickClose();

    }

    @Test
    void minInputsTest() {
        registrationFormPage.openPage()
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
    void incorrectNumberTest() {
        registrationFormPage.openPage()
                .setFirstName(registrationFormTestData.firstName)
                .setLastName(registrationFormTestData.lastName)
                .chooseGender(registrationFormTestData.gender)
                .setUserNumber(registrationFormTestData.incorrectNumber)
                .clickSubmit();

        registrationFormPage.mainFormCheck("Student Registration Form");
    }

}

