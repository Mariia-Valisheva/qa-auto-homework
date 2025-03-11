package tests.demoqatests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigData;
import config.WebDriverConfig;
import helpers.Attachments;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.RegistrationFormPage;

public class TestBaseDemo {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Boolean isRemote = Boolean.valueOf(System.getProperty("isRemote", "false"));

    @BeforeAll
    static void configParams() {
        WebDriverConfig webDriverConfig = new WebDriverConfig();
        webDriverConfig.configParams();
    }


    @Step("Открываем страницу регистрации")
    @BeforeEach
    void openPageBeforeEachTest() {
        registrationFormPage.openPage();
    }

    @BeforeEach
    void addSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @AfterEach
    void addAttachments() {
        if (isRemote) {
            if (!Configuration.browser.equals("firefox")) {
                Attachments.addScreenshot("Test screenshot");
                Attachments.addPageSource();
                Attachments.addBrowserConsoleLogs();
                Attachments.addVideo();
            }
        } else {
            Attachments.addScreenshot("Test screenshot");
            Attachments.addPageSource();
            Attachments.addBrowserConsoleLogs();
        }
        Selenide.closeWebDriver();
    }
}