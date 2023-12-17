import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import utilities.ChangeOrderItems;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
class ChangeOrderItemsTest {
    private ChangeOrderItems changeOrderItems;
    @BeforeEach
    void setUp() {
        changeOrderItems = new ChangeOrderItems();
    }
    @Test
    void removeItemFromOrder_invalidItemIndex_shouldPrintErrorMessage() {
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
       // changeOrderItems.removeItemFromOrder(1);
        String expectedOutput = "Enter the index of the item to remove: \nInvalid item index.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(System.in);
        System.setOut(System.out);
    }
}