package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {
    private SelenideElement table = $(".table");

    public void resultTable(String key, String value) {
        table.shouldHave(text(key)).shouldHave(text(value));
    }
}
