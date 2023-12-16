package utilities;

import entities.ItemsMenu;
import menus.DisplayMenu;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeMenuItems {
    private List<ItemsMenu> menuItems;

    public ChangeMenuItems() {
        this.menuItems = readMenuFromFile();
    }

    public List<ItemsMenu> readMenuFromFile() {
        List<ItemsMenu> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/persistent/menuItems.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String category = parts[0].trim();
                    String itemName = parts[1].trim();
                    String price = parts[2].trim();
                    ItemsMenu menuItem = new ItemsMenu(category, itemName, price);
                    items.add(menuItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void displayMenu() throws FileNotFoundException, NoSuchAlgorithmException {
        PopulateItemsMenu populateItemsMenu = new PopulateItemsMenu();
        List<ItemsMenu> PopulateItemsMenu = populateItemsMenu.populateItemsMenu();
        DisplayMenu displayMenu = new DisplayMenu();
        displayMenu.displayMenu(PopulateItemsMenu);
    }

    public void modifyMenuItems() throws FileNotFoundException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add item to menu\n2. Remove item from menu");
        int operationIndex = sc.nextInt();

        switch (operationIndex) {
            case 1:
                addItemToMenu();
                break;
            case 2:
                removeItemFromMenu();
                break;
            default:
                System.out.println("Invalid operation");
        }
    }

    private void addItemToMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter category: ");
        String category = sc.nextLine();
        System.out.println("Enter item name: ");
        String itemName = sc.nextLine();
        System.out.println("Enter item price: ");
        String price = sc.nextLine();

        ItemsMenu newItem = new ItemsMenu(category, itemName, price);
        menuItems.add(newItem);

        updateMenuFile();
        System.out.println("Item added to the menu successfully.");
    }

    private void removeItemFromMenu() throws FileNotFoundException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        displayMenu();
        System.out.println("Enter the number of the item to remove: ");
        int itemNumber = sc.nextInt();

        if (itemNumber > 0 && itemNumber <= menuItems.size()) {
            menuItems.remove(itemNumber - 1);
            updateMenuFile();
            System.out.println("Item removed from the menu successfully.");
        } else {
            System.out.println("Invalid item number.");
        }
    }

    public void updateMenuFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/persistent/menuItems.csv"))) {
            for (ItemsMenu item : menuItems) {
                String itemString = itemToString(item);
                writer.write(itemString);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String itemToString(ItemsMenu item) {
        return item.getType() + "," + item.getName() + "," + item.getPrice();
    }
}