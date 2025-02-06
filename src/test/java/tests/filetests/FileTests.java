package tests.filetests;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileTests extends FileTestBase {

private ClassLoader cl = FileTests.class.getClassLoader();


    @DisplayName("Работа с json")
    @Test
    void jsonTest() throws Exception{
        try(InputStream is = cl.getResourceAsStream("testjson.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode actualValues = objectMapper.readValue(is, JsonNode.class);

            Assertions.assertEquals("CLIENT", actualValues.get("paymentType").asText());
            Assertions.assertEquals(5, actualValues.get("paymentPriority").asInt());

            JsonNode objectValues = actualValues.withObject("clientInfo");
            Assertions.assertEquals("Имя Фамилия", objectValues.get("clientName").asText());
            Assertions.assertEquals("Адрес 123", objectValues.get("clientAddress").asText());
            Assertions.assertEquals(true, objectValues.get("isResident").asBoolean());

            JsonNode arrayValues = objectValues.withArray("accountTypes");
            //String[] string = {"DEBIT", "CREDIT", "TRANSIT"};
            //Assertions.assertArrayEquals(string, arrayValues.);
        }
    }



    @DisplayName("Работа с архивом")
    @Test
    void zipTest() throws Exception{
        try(InputStream is = cl.getResourceAsStream("АрхивТест.zip");
            ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }

    @DisplayName("Работа с csv файлом")
    @Test
    void csvTest() throws Exception{
        try(InputStream is = cl.getResourceAsStream("csv_data/dataforsearch.csv");
            CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> dataFromCsv = csvReader.readAll();
            Assertions.assertEquals(5, dataFromCsv.size());
            Assertions.assertArrayEquals(
                    new String[] {"Java", "Объектно-ориентированное программирование на Java"},
                    dataFromCsv.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[] {"Python", " Многопоточный Python"},
                    dataFromCsv.get(1)
            );
            Assertions.assertArrayEquals(
                    new String[] {"Автотесты API", "Тестирование ПО: Автотесты для API с Java REST Assured и TestNG"},
                    dataFromCsv.get(2)
            );
            Assertions.assertArrayEquals(
                    new String[] {"SQL", "Основы SQL"},
                    dataFromCsv.get(3)
            );
            Assertions.assertArrayEquals(
                    new String[] {"REST", "Тестирование REST API в Postman - легкий старт в автоматизацию"},
                    dataFromCsv.get(4)
            );
        }
    }

    @DisplayName("Работа с xls файлом")
    @Test
    void xlsTest() throws Exception{
        open("https://www.vertex42.com/Files/download2/themed.php?file=home-inventory-list.xlsx");
        File file = $("[href='https://www.vertex42.com/Files/exclusive/home-inventory-list.xlsx']").download();
        XLS xls = new XLS(file);
        String value = xls.excel.getSheetAt(0).getRow(8).getCell(3).getStringCellValue();
        Assertions.assertTrue(value.contains("Purchase Information"));
    }

    @DisplayName("Работа с пдф файлом")
    @Test
    void pdfTest() throws Exception{
        open("https://www.sberbank.ru/ru/inform");
        File file = $("[href='http://www.sberbank.ru/common/img/uploaded/files/el_docs/common/Soglashenie_ob_ispolzovanii_el_podpisi.pdf']").download();
        PDF pdf = new PDF(file);
        Assertions.assertTrue(true, "Соглашение об использовании простой электронной подписи");
    }

    @Disabled
    @Test
    @DisplayName("Скачивание файла формата docx через конструкцию try")
    void downloadFileTest() throws Exception {
        open("https://mpt.tatarstan.ru/documents/reglament.htm");
        File fileTxt = $("[href='/file/mpt/File/64-ЗРТ.docx']").download();

        try(InputStream inst = new FileInputStream(fileTxt)) {
            byte[] dataFromFile = inst.readAllBytes();
            String readyData = new String(dataFromFile, StandardCharsets.UTF_8);
            //Assertions.assertTrue(readyData.contains("ОБ ИСПОЛНИТЕЛЬНЫХ ОРГАНАХ ГОСУДАРСТВЕННОЙ ВЛАСТИ"));
            System.out.println(readyData);
        }
    }

    @Test
    @DisplayName("Скачивание файла формата md через конструкцию try")
    void downloadFileMdTest() throws Exception {
        open("https://github.com/selenide/selenide/blob/main/README.md");
        File fileTxt = $(".react-blob-header-edit-and-raw-actions [href*='/selenide/raw/refs/heads/main/README.md").download();

        try(InputStream inst = new FileInputStream(fileTxt)) {
            byte[] dataFromFile = inst.readAllBytes();
            String readyData = new String(dataFromFile, StandardCharsets.UTF_8);
            Assertions.assertTrue(readyData.contains("What is Selenide?"));
        }
    }

    @Test
    @DisplayName("Скачивание файла формата md через библиотеку apache commons io")
    void downloadFileMdThroughLibTest() throws Exception {
        open("https://github.com/selenide/selenide/blob/main/README.md");
        File fileTxt = $(".react-blob-header-edit-and-raw-actions [href*='/selenide/raw/refs/heads/main/README.md").download();
        String readyData = FileUtils.readFileToString(fileTxt, StandardCharsets.UTF_8);
        Assertions.assertTrue(readyData.contains("What is Selenide?"));
        }
}
