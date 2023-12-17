import enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.ChangeOrderStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeOrderStatusTest {

    private ChangeOrderStatus changeOrderStatus;

    @BeforeEach
    void setUp() {
        changeOrderStatus = new ChangeOrderStatus();
    }

    @Test
    void testChangeOrderStatus_WaiterServedStatus() throws IOException, NoSuchAlgorithmException {
        String tempFilePath = "./src/persistent/tempOrders.csv";
        Files.copy(Paths.get("./src/persistent/orders.csv"), Paths.get(tempFilePath));

        String userInput = "1\n1\n1\n";
        System.setIn(new java.io.ByteArrayInputStream(userInput.getBytes()));

        changeOrderStatus.changeOrderStatus("waiter");

        List<String> updatedOrdersLines = Files.readAllLines(Paths.get(tempFilePath));
        Files.delete(Paths.get(tempFilePath));

        assertTrue(updatedOrdersLines.get(0).contains(OrderStatus.SERVED.toString()));
    }

    @Test
    void testChangeOrderStatus_CookCookingStatus() throws IOException, NoSuchAlgorithmException {

        String tempFilePath = "./src/persistent/tempOrders.csv";
        Files.copy(Paths.get("./src/persistent/orders.csv"), Paths.get(tempFilePath));

        String userInput = "2\n1\n1\n";
        System.setIn(new java.io.ByteArrayInputStream(userInput.getBytes()));

        changeOrderStatus.changeOrderStatus("cook");

        List<String> updatedOrdersLines = Files.readAllLines(Paths.get(tempFilePath));
        Files.delete(Paths.get(tempFilePath));

        assertTrue(updatedOrdersLines.get(0).contains(OrderStatus.COOKING.toString()));
    }

    @Test
    void testChangeOrderStatus_InvalidRole() throws IOException, NoSuchAlgorithmException {

        String userInput = "1\n";
        System.setIn(new java.io.ByteArrayInputStream(userInput.getBytes()));

        changeOrderStatus.changeOrderStatus("invalidRole");


    }

}
