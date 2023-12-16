import entities.Order;
import entities.Table;
import enums.OrderStatus;
import org.junit.jupiter.api.Test;
import utilities.ReadOrdersFromFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadOrdersFromFileTest {

    @Test
    void testReadOrdersFromFile() throws IOException {
        // Create a temporary test file for orders.csv
        String testFilePath = "./src/persistent/testOrders.csv";
        createTestFile(testFilePath);

        // Execute the method under test
        ArrayList<Order> orders = ReadOrdersFromFile.readOrdersFromFile();

        // Verify the results
        assertEquals(2, orders.size()); // Assuming two valid entry in the test file
        Order order = orders.get(0);
        assertEquals(11, order.getTable().getNumberOfTable()); // Assuming the test file has "1" for table number
        assertFalse(order.getTable().isStatusFree()); // Assuming the test file has "true" for table status
        assertEquals(OrderStatus.COOKING, order.getStatus()); // Assuming the test file has "COOKING" for status

        // Clean up: Delete the temporary test file
        deleteTestFile(testFilePath);
    }

    private void createTestFile(String filePath) {
        // Create a test file with sample data
        try {
            String fileContent = "1,true,2023-01-01,12:00:00.000000,20.0,COOKING,food,Item1,10.0,beverages,Item2,5.0";
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), fileContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestFile(String filePath) {
        // Delete the temporary test file
        File testFile = new File(filePath);
        if (testFile.exists()) {
            testFile.delete();
        }
    }
}
