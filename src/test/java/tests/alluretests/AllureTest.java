package tests.alluretests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AllureTestPage;
import pages.AllureTestPageWithSteps;

import static io.qameta.allure.Allure.step;

@DisplayName("Поиск репозитория в гитхабе и проверка issues tab")
public class AllureTest extends AllureTestBase {

   AllureTestPage allureTestPage = new AllureTestPage();


    @DisplayName("Чистый Selenide (с Listener)")
    @Test
    public void gitHubLambdaSearchTest() {

        allureTestPage
                .openPage()
                .searchByRepoName()
                .openRepo()
                .issueTabCheck();

    }

    @DisplayName("Лямбда шаги через step")
    @Test
    public void gitHubSelenideSearchTest() {

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
    public void gitHubAnnotationSearchTest() {
        AllureTestPageWithSteps steps = new AllureTestPageWithSteps();

        steps.openPage();
        steps.searchByRepoName();
        steps.openRepo();
        steps.issueTabCheck();

    }
}


