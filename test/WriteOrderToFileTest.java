import entities.Order;
import entities.Table;
import enums.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.WriteOrderToFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WriteOrderToFileTest {

    private static final String TEST_FILE_PATH = "./src/persistent/testWriteOrderToFile.csv";

    @BeforeEach
    void setUp() {

        deleteTestFile();
    }

    @AfterEach
    void tearDown() {

        deleteTestFile();
    }

    @Test
    void testWriteOrderToFile() throws IOException {

        Order testOrder = createTestOrder();


        WriteOrderToFile.writeOrderToFile(testOrder, TEST_FILE_PATH);


        List<String> lines = readLinesFromFile(TEST_FILE_PATH);

        assertEquals(1, lines.size()); // Assuming one line in the file
        String expectedLine = orderToString(testOrder);
        assertEquals(expectedLine, lines.get(0));
    }

    private Order createTestOrder() {
        Table table = new Table(1, true);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        double total = 25.0;
        OrderStatus status = OrderStatus.SERVED;
        List<String> menuItems = new ArrayList<>();
        menuItems.add("food,Item1,10.0");
        menuItems.add("beverages,Item2,5.0");

        return new Order(table, date, time, total, status, (ArrayList<String>) menuItems);
    }

    private List<String> readLinesFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private void deleteTestFile() {

        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    private String orderToString(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(order.getTable().getNumberOfTable()).append(",");
        sb.append(order.getTable().isStatusFree()).append(",");
        sb.append(order.getDate()).append(",");
        sb.append(order.getTime()).append(",");
        sb.append(order.getSumPrice()).append(",");
        sb.append(order.getStatus()).append(",");
        sb.append("[");
        for (String item : order.getMenuItems()) {
            sb.append(item).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // Remove the trailing comma and space
        sb.append("]");
        return sb.toString();
    }
}
