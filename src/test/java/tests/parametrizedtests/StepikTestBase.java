package tests.parametrizedtests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.StepikPage;

public class StepikTestBase {

    StepikPage stepikPage = new StepikPage();

    @BeforeAll
    static void configParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://stepik.org";
        Configuration.browserSize = "2560x1440";
        //Configuration.holdBrowserOpen = true;
        Configuration.timeout = 12000;
    }

    @BeforeEach
    void openPageBeforeEachTest() {
        stepikPage.openPage();
    }

    @AfterEach
    void configParamsForEach() {
        Selenide.closeWebDriver();
    }
}
