package testbase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.SteppikPage;

public class SteppikTestBase {

    SteppikPage steppikPage = new SteppikPage();

    @BeforeAll
    static void configParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://stepik.org";
        Configuration.browserSize = "2560x1440";
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
   void openPageBeforeEachTest() {
        steppikPage.openPage();
    }

    //@AfterEach
    //void configParamsForEach() {
        //Selenide.closeWebDriver();
    //}
}
