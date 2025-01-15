package tests.parametrizedtests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.collections.SizeGreaterThan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.SteppikPage;
import testbase.SteppikTestBase;

import static com.codeborne.selenide.Selenide.$;


@DisplayName("Тесты для поиска на главное страницы Steppik")
@Tag("WEB")

public class SteppikParametrizedTest extends SteppikTestBase {



    SteppikPage steppikPage = new SteppikPage();

    @Test
    @DisplayName("Поиск по ключевому слову Java")

     void searchForJavaTest() {
        steppikPage
                   .searchByText("Java")
                   .assertionsOnTheMainPlace("Объектно-ориентированное программирование на Java");
    }

    @Test
    @DisplayName("Поиск по ключевому слову Python")

    void searchForPythonTest() {
        steppikPage
                .searchByText("Python")
                .assertionsOnTheMainPlace("Многопоточный Python");
    }


    @Test
    @DisplayName("Поиск по ключевому слову Java")

    void searchForJavaShouldHaveResultTest() {
        steppikPage
                .searchByText("Java");
        //$(".catalog-course-cards").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }


    @Test
    @DisplayName("Поиск по ключевому слову Python")

    void searchForPythonShouldHaveResultTest() {
        steppikPage
                .searchByText("Python");
        //$(".catalog-course-cards").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

}
