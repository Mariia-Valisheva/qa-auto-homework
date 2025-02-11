package tests.alluretests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AllureTestPage;
import pages.AllureTestPageWithSteps;

import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

@DisplayName("Поиск репозитория в гитхабе и проверка issues tab")
public class AllureTest extends AllureTestBase {

   AllureTestPage allureTestPage = new AllureTestPage();


    @DisplayName("Чистый Selenide (с Listener)")
    @Test
    public void gitHubSelenideSearchTest() {

        allureTestPage
                .openPage()
                .searchByRepoName()
                .openRepo()
                .issueTabCheck();

    }

    @DisplayName("Лямбда шаги через step")
    @Test
    public void gitHubLambdaSearchTest() {

        step("Открываем главную страницу github", () -> {
            allureTestPage.openPage();
        });
        step("Ищем репозиторий по названию", () -> {
            allureTestPage.searchByRepoName();
        });
        step("Открываем репозиторий", () -> {
            allureTestPage.openRepo();
        });
        step("Проверяем наличие в issues элемента с текстом TestIssue", () -> {
            allureTestPage.issueTabCheck();
        });
    }



    @DisplayName("Шаги с аннотацией @Step")
    @Test
    public void gitHubAnnotationStepSearchTest() {
        AllureTestPageWithSteps steps = new AllureTestPageWithSteps();

        steps.openPage();
        steps.searchByRepoName();
        steps.openRepo();
        steps.issueTabCheck();

    }

    @DisplayName("Лямбда шаги через step + аннотация @Attachments")
    @Test
    public void gitHubAttachmentsLambdaSearchTest() {

        step("Открываем главную страницу github", () -> {
            allureTestPage.openPage();
        });
        step("Ищем репозиторий по названию", () -> {
            allureTestPage.searchByRepoName();
        });
        step("Открываем репозиторий", () -> {
            allureTestPage.openRepo();
        });
        step("Проверяем наличие в issues элемента с текстом TestIssue", () -> {
            allureTestPage.issueTabCheck();
            attachment("testscreen", webdriver().driver().source());
        });
    }


    @DisplayName("Шаги с аннотацией @Step + аннотация @Attachments")
    @Test
    public void gitHubAttachmentsStepSearchTest() {
        AllureTestPageWithSteps steps = new AllureTestPageWithSteps();

        steps.openPage();
        steps.searchByRepoName();
        steps.openRepo();
        steps.issueTabCheck();
        steps.takeScreenshotAfterTest();
    }
}


