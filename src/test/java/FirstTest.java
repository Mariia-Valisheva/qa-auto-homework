import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FirstTest {

    @BeforeAll
    static void configParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
    }

    @Test
    void studentRegistrationFormTest() {

        //заполнение формы

        open("/automation-practice-form");
        $("#firstName").setValue("Inrice");
        $("#lastName").setValue("Ricardio");
        $("#userEmail").setValue("someaddress@gmail.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("9999999999");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("November")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1996")).click();
        $(".react-datepicker__month .react-datepicker__week").$(byText("1")).click();
        $("#subjectsInput").setValue("Ma");
        $(byText("Maths")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("1685578746_polinka-top-p-zhdun-foto-kartinki-prikolnie-pinterest-68.jpg");
        $("#currentAddress").setValue("Мой адрес не дом и не улица");
        $("#state").click();
        $(byText("Uttar Pradesh")).click();
        $("#city").click();
        $(byText("Merrut")).click();
        $("#submit").click();

        //проверка заполнения формы

        $(".table").shouldHave(text("Student Name")).shouldHave(text("Inrice Ricardio"));
        $(".table").shouldHave(text("Student Email")).shouldHave(text("someaddress@gmail.com"));
        $(".table").shouldHave(text("Gender")).shouldHave(text("Female"));
        $(".table").shouldHave(text("Mobile")).shouldHave(text("9999999999"));
        $(".table").shouldHave(text("Date of Birth")).shouldHave(text("01 November,1996"));
        $(".table").shouldHave(text("Subjects")).shouldHave(text("Maths"));
        $(".table").shouldHave(text("Hobbies")).shouldHave(text("Reading"));
        $(".table").shouldHave(text("Picture")).shouldHave(text("1685578746_polinka-top-p-zhdun-foto-kartinki-prikolnie-pinterest-68.jpg"));
        $(".table").shouldHave(text("Address")).shouldHave(text("Мой адрес не дом и не улица"));
        $(".table").shouldHave(text("State and City")).shouldHave(text("Uttar Pradesh Merrut"));


        //закрытие формы
        $("#closeLargeModal").click();

    }
}

