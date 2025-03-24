package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AllureTestPage {
    String repo = "Mariia-Valisheva/qa-auto-homework";
    String issueText = "TestIssue";

    private final SelenideElement
    smallSearchInput =  $(".header-search-button"),
    extendedSearchInput =  $("#query-builder-test"),
    issueTab =  $("#issues-tab"),
    issueTitle = $("h3.markdown-title");


    public AllureTestPage openPage() {
        open("");
        return this;
    }

    public AllureTestPage searchByRepoName() {
        smallSearchInput.click();
        extendedSearchInput.setValue(repo).pressEnter();

        return this;
    }

    public AllureTestPage openRepo() {
        $(By.linkText(repo)).click();

        return this;
    }

    public AllureTestPage issueTabCheck() {
        issueTab.click();
        issueTitle.shouldHave(text(issueText));

        return this;
    }
}
