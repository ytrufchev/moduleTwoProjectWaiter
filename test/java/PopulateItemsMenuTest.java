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
        // Create a temporary test file for menuItems.csv
        String testFilePath = "src/persistent/testMenuItems.csv";
        createTestFile(testFilePath);

        // Instantiate the class under test
        PopulateItemsMenu populateItemsMenu = new PopulateItemsMenu();

        // Execute the method under test
        List<ItemsMenu> itemsMenuList = populateItemsMenu.populateItemsMenu();

        // Verify the results
        assertEquals(23, itemsMenuList.size()); // Assuming two valid entries in the test file

        // Clean up: Delete the temporary test file
        deleteTestFile(testFilePath);
    }

    private void createTestFile(String filePath) {
        // Create a test file with sample data
        try {
            String fileContent = "food,Item1,10.00\nbeverages,Item2,5.00";
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
