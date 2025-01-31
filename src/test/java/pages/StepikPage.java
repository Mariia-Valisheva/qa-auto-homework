package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StepikPage {
    private final SelenideElement
    searchInput = $(".search-form__input"),
    mainPlace = $(".marco-layout__content"),
    langugeArrow = $(".language-selector").$(".navbar__down-arrow"),
    submitButton = $(".search-form__submit");

    public StepikPage openPage() {
        open("");
        return this;
    }

    public StepikPage searchByText(String value) {
        searchInput.setValue(value).pressEnter();
        return this;
    }

    public StepikPage assertionsOnTheMainPlace(String value) {
        mainPlace.shouldHave(text(value));
        return this;
    }

    public StepikPage checkCollectionSize(int value) {
        ElementsCollection collection = $$(".course-cards__item");
        collection.shouldBe(CollectionCondition.sizeGreaterThan(value));
        return this;
    }

    public StepikPage chooseLanguage(String valueLang) {
        langugeArrow.click();
        $(".menu_right").$$(".menu-item").find(text(valueLang)).click();
        return this;
    }

    public StepikPage checkButtonText(String valueRes) {
        submitButton.shouldHave(text(valueRes));
        return this;
    }

    public StepikPage checkNavBarButtons(List<String> valueNames) {
        $$(".navbar__links li").filter(visible)
                .shouldHave(texts(valueNames));
        return this;
    }
}