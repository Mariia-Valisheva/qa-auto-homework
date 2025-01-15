package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private SelenideElement
    monthInput =  $(".react-datepicker__month-select"),
    yearInput = $(".react-datepicker__year-select");


    public void setDate(String month, String year, String day) {
        monthInput.click();
        $(byText(month)).click();
        yearInput.click();
        $(byText(year)).click();

        if (day.length() == 1) day = "0" + day;
        String dayInput = ".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)";
        $(dayInput).click();

    }
}
