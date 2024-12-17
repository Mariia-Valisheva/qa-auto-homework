package tests.demoqatests;

import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

public class StudentRegistrationFormTest extends TestBaseDemo {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @Test
    void allInputsTest() {

        registrationFormPage.openPage()
                    .setFirstName("Inrice")
                    .setLastName("Ricardio")
                    .setUserEmail("someaddress@gmail.com")
                    .chooseGender("Female")
                    .setUserNumber("9999999999")
                    .setDateOfBirth("November", "1996", "1")
                    .addSubject("Ma", "Maths")
                    .chooseHobby("Reading")
                    .choosePicture("1685578746_polinka-top-p-zhdun-foto-kartinki-prikolnie-pinterest-68.jpg")
                    .setCurrentAddress("Мой адрес не дом и не улица")
                    .chooseStateAndCity("Uttar Pradesh", "Merrut")
                    .clickSubmit();

        registrationFormPage.checkForm("Student Name", "Inrice Ricardio")
                        .checkForm("Student Email", "omeaddress@gmail.com")
                        .checkForm("Gender", "Female")
                        .checkForm("Mobile", "9999999999")
                        .checkForm("Date of Birth", "01 November,1996")
                        .checkForm("Subjects", "Maths")
                        .checkForm("Hobbies", "Reading")
                        .checkForm("Picture", "1685578746_polinka-top-p-zhdun-foto-kartinki-prikolnie-pinterest-68.jpg")
                        .checkForm("Address", "Мой адрес не дом и не улица")
                        .checkForm("State and City", "Uttar Pradesh Merrut");

        registrationFormPage.clickClose();

    }

    @Test
    void minInputsTest() {
        registrationFormPage.openPage()
                .setFirstName("Manual")
                .setLastName("QA")
                .chooseGender("Male")
                .setUserNumber("9999999999")
                .clickSubmit();

        registrationFormPage.checkForm("Student Name", "Manual QA")
                .checkForm("Gender", "Male")
                .checkForm("Mobile", "9999999999");

        registrationFormPage.clickClose();
    }

    @Test
    void incorrectNumberTest() {
        registrationFormPage.openPage()
                .setFirstName("Mariia")
                .setLastName("Valisheva")
                .chooseGender("Other")
                .setUserNumber("+999999999")
                .clickSubmit();

        registrationFormPage.mainFormCheck("Student Registration Form");
    }

}

