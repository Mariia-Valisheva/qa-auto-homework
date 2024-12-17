package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private SelenideElement
    monthInput =  $(".react-datepicker__month-select"),
    yearInput = $(".react-datepicker__year-select"),
    dayInput = $(".react-datepicker__month .react-datepicker__week");

    public void setDate(String month, String year, String day) {
        monthInput.click();
        $(byText(month)).click();
        yearInput.click();
        $(byText(year)).click();
        dayInput.$(byText(day)).click();

    }
}
