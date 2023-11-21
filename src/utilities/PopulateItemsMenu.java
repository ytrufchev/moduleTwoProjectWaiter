package utilities;

import entities.ItemsMenu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PopulateItemsMenu {
    public List<ItemsMenu> populateItemsMenu() {
        List<ItemsMenu> itemsMenuList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/persistent/menuItems.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equalsIgnoreCase("food") || values[0].equalsIgnoreCase("beverages")) {
                    ItemsMenu itemsMenu = new ItemsMenu(values[0], values[1], values[2]);
                    itemsMenuList.add(itemsMenu);
                } else {
                    System.out.println("Check for errors in menuItems.csv");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemsMenuList;
    }
}
