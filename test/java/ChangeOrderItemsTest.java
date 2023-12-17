import entities.Order;
import entities.Table;
import enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import utilities.ChangeOrderItems;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeOrderItemsTest {
    private ChangeOrderItems changeOrderItems;
    ArrayList<Order> orders = new ArrayList<>();
    Order order = new Order();

    @BeforeEach
    void setUp() {
        changeOrderItems = new ChangeOrderItems();
        ArrayList<String> items = new ArrayList<>();
        items.add("Food,Alfredo Pasta with Salmon and Spinach,14.99");
        items.add("Food,Beef Broth with Dried Mushrooms,9.99");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Table table = new Table(9, false);
        this.order.setStatus(OrderStatus.NON_PROCESSED);
        this.order.setDate(LocalDate.ofEpochDay(2023-12-16));
        this.order.setTime(time);
        this.order.setTable(table);
        this.order.setSumPrice(25.2);
        this.order.setMenuItems(items);
        changeOrderItems.setOrders(orders);
    }

    @Test
    void removeItemFromOrder_invalidItemIndex_shouldPrintErrorMessage() throws FileNotFoundException, NoSuchAlgorithmException {
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        changeOrderItems.removeItemFromOrder(1);

        String expectedOutput = "Enter the index of the item to remove: \nInvalid item index.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(System.in);
        System.setOut(System.out);
    }
}
