package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SteppikPage {
    private final SelenideElement
    searchInput = $(".search-form__input"),
    mainPlace = $(".marco-layout__content");

    public SteppikPage openPage() {
        open("");
        return this;
    }

    public SteppikPage searchByText(String value) {
        searchInput.setValue(value).pressEnter();
        return this;
    }

    public SteppikPage assertionsOnTheMainPlace(String value) {
        mainPlace.shouldHave(text(value));
        return this;
    }
}
