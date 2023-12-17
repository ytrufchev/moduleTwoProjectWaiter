import entities.ItemsMenu;
import org.junit.jupiter.api.Test;
import utilities.PopulateItemsMenu;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class PopulateItemsMenuTest {
    @Test
    void testPopulateItemsMenu() {
        String testFilePath = "src/persistent/testMenuItems.csv";
        createTestFile(testFilePath);
        PopulateItemsMenu populateItemsMenu = new PopulateItemsMenu();
        List<ItemsMenu> itemsMenuList = populateItemsMenu.populateItemsMenu();
        assertEquals(23, itemsMenuList.size());
        deleteTestFile(testFilePath);
    }
    private void createTestFile(String filePath) {
        try {
            String fileContent = "food,Item1,10.00\nbeverages,Item2,5.00";
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), fileContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteTestFile(String filePath) {
        File testFile = new File(filePath);
        if (testFile.exists()) {
            testFile.delete();
        }
    }
}