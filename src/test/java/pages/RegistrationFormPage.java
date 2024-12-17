package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormPage {
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            userCurrentAddress = $("#currentAddress"),
            submitButton = $("#submit"),
            closeButton = $("#closeLargeModal"),
            genderRadioButton = $("#genterWrapper"),
            hobbyCheckBox = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            dateInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            stateSearch = $("#state"),
            citySearch = $("#city"),
            mainForm = $(".practice-form-wrapper");

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultTableComponent tableComponent = new ResultTableComponent();

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.sendKeys(value);

        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameInput.sendKeys(value);

        return this;
    }

    public RegistrationFormPage setUserEmail(String value) {
        userEmailInput.sendKeys(value);

        return this;
    }

    public RegistrationFormPage chooseGender(String value) {
        genderRadioButton.$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setUserNumber(String value) {
        userNumberInput.sendKeys(value);

        return this;
    }

    public RegistrationFormPage setDateOfBirth(String month,String year, String day) {
        dateInput.click();
        calendarComponent.setDate(month, year, day);

        return this;
    }

    public RegistrationFormPage addSubject(String start, String end) {
        subjectInput.sendKeys(start);
        $(byText(end)).click();

        return this;
    }

    public RegistrationFormPage chooseHobby(String value) {
        hobbyCheckBox.$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage choosePicture(String value) {
        uploadPicture.uploadFromClasspath(value);

        return this;
    }

    public RegistrationFormPage setCurrentAddress(String value) {
        userCurrentAddress.sendKeys(value);

        return this;
    }

    public RegistrationFormPage chooseStateAndCity(String state, String city) {
        stateSearch.click();
        $(byText(state)).click();
        citySearch.click();
        $(byText(city)).click();

        return this;
    }

    public RegistrationFormPage clickSubmit() {
        submitButton.click();

        return this;
    }

    public RegistrationFormPage checkForm(String key, String value) {
        tableComponent.resultTable(key, value);

        return this;
    }

    public RegistrationFormPage clickClose() {
        closeButton.click();

        return this;
    }

    public RegistrationFormPage mainFormCheck(String value) {
        mainForm.shouldHave(text(value));

        return this;
    }
}

