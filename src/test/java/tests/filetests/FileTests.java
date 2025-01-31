package tests.filetests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileTests extends FileTestBase {

    @Test
    @DisplayName("Скачивание файла через конструкцию try")
    void downloadFileTest() throws Exception {
     open("https://www.mvideo.ru/products/stiralnaya-mashina-uzkaya-hi-wm610w-20084442/specification");
        File instruction = $("[href*='https://static.mvideo.ru/ins/20084442.pdf']").download();

       try(InputStream inst = new FileInputStream(instruction)) {
           byte[] dataFromFile = inst.readAllBytes();
           String readyData = new String(dataFromFile, StandardCharsets.UTF_8);
           Assertions.assertTrue(readyData.contains("Автоматическая стиральная машина с фронтальной загрузкой"));
       }

    }
}
