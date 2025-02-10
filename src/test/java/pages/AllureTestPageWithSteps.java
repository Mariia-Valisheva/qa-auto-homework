package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AllureTestPageWithSteps {
    String repo = "Mariia-Valisheva/qa-auto-homework";
    String issueText = "TestIssue";

    private final SelenideElement
    smallSearchInput =  $(".header-search-button"),
    extendedSearchInput =  $("#query-builder-test"),
    issueTab =  $("#issues-tab"),
    issueTitle = $("h3.markdown-title");

    @Step("Открываем главную страницу github")
    public AllureTestPageWithSteps openPage() {
        open("");
        return this;
    }

    @Step("Ищем репозиторий по названию")
    public AllureTestPageWithSteps searchByRepoName() {
        smallSearchInput.click();
        extendedSearchInput.setValue(repo).pressEnter();

        return this;
    }

    @Step("Открываем репозиторий")
    public AllureTestPageWithSteps openRepo() {
        $(By.linkText(repo)).click();

        return this;
    }

    @Step("Проверяем наличие в issues элемента с текстом TestIssue")
    public AllureTestPageWithSteps issueTabCheck() {
        issueTab.click();
        issueTitle.shouldHave(text(issueText));

        return this;
    }
}
