import entities.ItemsMenu;
import org.junit.jupiter.api.Test;
import utilities.ChangeMenuItems;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeMenuItemsTest {

    @Test
    void testReadMenuFromFile() {
        ChangeMenuItems changeMenuItems = new ChangeMenuItems();
        List<ItemsMenu> menuItems = changeMenuItems.readMenuFromFile();

        assertNotNull(menuItems);
        assertTrue(menuItems.size() > 0, "Menu should not be empty");
    }

    @Test
    void testUpdateMenuFile() throws IOException, NoSuchAlgorithmException {
        String tempFilePath = "./src/persistent/tempMenuItems.csv";
        Files.copy(Paths.get("./src/persistent/menuItems.csv"), Paths.get(tempFilePath));
        ChangeMenuItems changeMenuItems = new ChangeMenuItems();
        changeMenuItems.updateMenuFile();

        List<String> updatedMenuLines = Files.readAllLines(Paths.get(tempFilePath));
        Files.delete(Paths.get(tempFilePath));

        assertTrue(updatedMenuLines.size() > 0, "Updated menu should not be empty");
    }

    @Test
    void testItemToString() {
        ItemsMenu item = new ItemsMenu("Category", "ItemName", "12.34");

        ChangeMenuItems changeMenuItems = new ChangeMenuItems();
        String itemString = changeMenuItems.itemToString(item);

        assertEquals("Category,ItemName,12.34", itemString);
    }

}
