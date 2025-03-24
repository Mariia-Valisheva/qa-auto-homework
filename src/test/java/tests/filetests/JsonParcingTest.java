package tests.filetests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import models.filetestsmodel.TestJson;

import java.io.InputStream;

public class JsonParcingTest {
    private ClassLoader cl = FileTests.class.getClassLoader();

    @DisplayName("Работа с json")
    @Test
    void jsonParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testjson.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            TestJson actualValues = objectMapper.readValue(is, TestJson.class);

            Assertions.assertEquals("CLIENT", actualValues.getPaymentType());
            Assertions.assertEquals(5, actualValues.getPaymentPriority());

            Assertions.assertEquals("Имя Фамилия", actualValues.getClientInfo().getClientName());
            Assertions.assertEquals("Адрес 123", actualValues.getClientInfo().getClientAddress());
            Assertions.assertTrue(actualValues.getClientInfo().isResident());

            String[] expectedArray = {"DEBIT", "CREDIT", "TRANSIT"};
            Assertions.assertArrayEquals(expectedArray, actualValues.getClientInfo().getAccountTypes());
        }
    }
}
