import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;

public class FirstTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "2560x1440";
    }

    @Test
    void StudentRegistrationFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Inrice");
        $("#lastName").setValue("Ricardio");
        $("#userEmail").setValue("someaddress@gmail.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("+7999999999");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("November")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1996")).click();
        $(".react-datepicker__month .react-datepicker__week").$(byText("1")).click();
        $("#subjectsInput").setValue("Собаковедение");
        $("#hobbiesWrapper").$(byText("Reading")).click();
    }
}

