package tests.filetests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WorkWithFilesTests {

    private ClassLoader cl = WorkWithFilesTests.class.getClassLoader();

    @Test
    @Tag("files")
    void unpackAndCheckPdfTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("АрхивТест.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("Портфолио_ баг-репорты, тест-кейсы.pdf")) {
                    PDF pdf = new PDF(zs);
                    String expected = "Тест-кейсы для сайта https://rt-school.ru/";
                    Assertions.assertTrue(pdf.text.contains(expected));
                }
            }
        }
    }

    @Test
    void unpackAndCheckCsvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("АрхивТест.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("dataforsearch.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zs));
                    {
                        List<String[]> dataFromCsv = csvReader.readAll();
                        Assertions.assertEquals(5, dataFromCsv.size());
                    }
                }
            }
        }
    }

    @Test
    void unpackAndCheckXlsTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("АрхивТест.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("home-inventory-list.xlsx")) {
                    XLS xls = new XLS(zs);
                    String value = xls.excel.getSheetAt(0).getRow(8).getCell(3).getStringCellValue();
                    Assertions.assertTrue(value.contains("Purchase Information"));
                }
            }
        }
    }

    @DisplayName("Тест на проверку пустого архива (мой вариант)")
    @Test
    void unpackAndCheckEmptyZipTestMine() throws Exception {
        try (InputStream is = cl.getResourceAsStream("EmptyArchive.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("home-inventory-list.xlsx")) {
                    XLS xls = new XLS(zs);
                    String value = xls.excel.getSheetAt(0).getRow(8).getCell(3).getStringCellValue();
                    Assertions.assertTrue(value.contains("Purchase Information"));
                }
            }
            ZipEntry checkEntry = zs.getNextEntry();
            if (checkEntry == null) {
                throw new RuntimeException("Архив пустой");
            }
        }
    }

    @DisplayName("Тест на проверку пустого архива (вариант от ментора)")
    @Test
    void unpackAndCheckEmptyZipTest() throws Exception {
        boolean flag = false;
        try (InputStream is = cl.getResourceAsStream("EmptyArchive.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("home-inventory-list.xlsx")) {
                    flag = true;
                    XLS xls = new XLS(zs);
                    String value = xls.excel.getSheetAt(0).getRow(8).getCell(3).getStringCellValue();
                    Assertions.assertTrue(value.contains("Purchase Information"));
                }
            }
        }
        if(!flag) throw new IOException("File not found");
    }
}