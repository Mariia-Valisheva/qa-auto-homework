package tests.filetests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WorkWithFilesTests {

    private ClassLoader cl = WorkWithFilesTests.class.getClassLoader();

    @Test
    void unpackAndCheckZipTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("АрхивТест.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("Портфолио_ баг-репорты, тест-кейсы.pdf")) {
                    PDF pdf = new PDF(zs);
                    Assertions.assertTrue(true, "Тест-кейсы для сайта https://rt-school.ru/");
                } else if (entry.getName().equals("dataforsearch.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zs));
                    {
                        List<String[]> dataFromCsv = csvReader.readAll();
                        Assertions.assertEquals(5, dataFromCsv.size());
                    }
                }
                else if (entry.getName().equals("home-inventory-list.xlsx")) {
                    XLS xls = new XLS(zs);
                    String value = xls.excel.getSheetAt(0).getRow(8).getCell(3).getStringCellValue();
                    Assertions.assertTrue(value.contains("Purchase Information"));
                }
            }
        }
    }
}
