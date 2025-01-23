package tests.parametrizedtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.SteppikPage;
import testbase.SteppikTestBase;
import utils.LanguagesForSteppik;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Тесты для поиска на главной страницы Steppik")
@Tag("WEB")

public class SteppikParametrizedTest extends SteppikTestBase {

    SteppikPage steppikPage = new SteppikPage();

   //Тест с использованием csv
    @CsvSource(value = {
            "Java | Объектно-ориентированное программирование на Java",
            "Python | Многопоточный Python",
            "Автотесты API | Тестирование ПО: Автотесты для API с Java, REST Assured и TestNG"
    }, delimiter = '|')
    @ParameterizedTest(name = "Тест на наличие {1} при поиске по значению {0}")
    void searchResultHasObjectTest(String searchData, String searchResult) {
        steppikPage
                .searchByText(searchData)
                .assertionsOnTheMainPlace(searchResult);
    }

//Аналогичный тест, но с большим количеством входных объектов и сsv файлом
    @CsvFileSource(resources = "/csv_data/dataforsearch.csv", delimiter = '|')
    @ParameterizedTest(name = "Тест на наличие {1} при поиске по значению {0}")
    void searchResultHasManyObjectTest(String searchData, String searchResult) {
        steppikPage
                .searchByText(searchData)
                .assertionsOnTheMainPlace(searchResult);
    }

    //Тест с valueSource
    @ValueSource(strings = {"Java", "Python"})
    @ParameterizedTest(name = "В поисковом запросе {0} должно быть больше одной карточки")

    void searchResultNotEmptyTest(String searchData) {
        steppikPage
                .searchByText(searchData)
                .checkCollectionSize(1);

    }

    //Тест с enum
    @EnumSource(LanguagesForSteppik.class)
    @ParameterizedTest(name = "При смене языка меняется название кнопки")
    void checkButtonNameTest(LanguagesForSteppik languages) {
        $(".language-selector").$(".navbar__down-arrow").click();
        $(".menu_right").$$(".menu-item").find(text(languages.name())).click();
        $(".search-form__submit").shouldHave(text(languages.description));
    }


    //Тест с MethodSource
    static Stream<Arguments> checkNavBarTest() {
        return Stream.of(
                Arguments.of(LanguagesForSteppik.Бел, List.of("Каталог", "Выкладанне")),
                Arguments.of(LanguagesForSteppik.De, List.of("Katalog", "Teaching")),
                Arguments.of(LanguagesForSteppik.Es, List.of("Catalog", "Teaching")),
                Arguments.of(LanguagesForSteppik.Po, List.of("Catálogo", "Ensinar")),
                Arguments.of(LanguagesForSteppik.Русс, List.of("Каталог", "Преподавание")),
                Arguments.of(LanguagesForSteppik.Укр, List.of("Каталог", "Викладання")),
                Arguments.of(LanguagesForSteppik.En, List.of("Catalog", "Teaching"))
        );
    }

    @MethodSource
    @EnumSource(LanguagesForSteppik.class)
    @ParameterizedTest(name = "При смене языка меняется название элементов в навбаре")
    void checkNavBarTest(LanguagesForSteppik languages, List<String> expectedText) {
        $(".language-selector").$(".navbar__down-arrow").click();
        $(".menu_right").$$(".menu-item").find(text(languages.name())).click();
        $$(".navbar__links li").filter(visible)
                .shouldHave(texts(expectedText));
    }
}
