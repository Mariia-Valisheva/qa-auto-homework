package tests.demoqatests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.RegistrationFormPage;

public class TestBaseDemo {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        //Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void openPageBeforeEachTest() {
        registrationFormPage.openPage();
    }

    @AfterEach
    void configParamsForEach() {
        Selenide.closeWebDriver();
    }
}
