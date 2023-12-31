package menus;

import entities.ItemsMenu;
import utilities.FormatCustomerMenu;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayMenu {
    public void displayMenu(List<ItemsMenu> menu) throws FileNotFoundException, NoSuchAlgorithmException {
        ArrayList<String> items = new ArrayList<>();

        for (ItemsMenu itemsMenu : menu) {
            String category = itemsMenu.getType();
            String name = itemsMenu.getName();
            String price = itemsMenu.getPrice();

            if (category.equalsIgnoreCase("food")) {
                items.add(name + "-" + price);
            } else if (category.equalsIgnoreCase("beverages")) {
                items.add(name + " - " + price);
            } else {
                System.out.println("Check for errors in menuItems.csv");
            }
        }

        int lineNumber = 1;
        for (int i = 0; i < items.size(); i++) {
            String item = lineNumber + ". " + items.get(i);
            items.set(i, item);
            lineNumber++;
        }

        items.add(lineNumber++ + ". Confirm order");
        items.add(lineNumber++ + ". Cancel");
        FormatCustomerMenu formatCustomerMenu = new FormatCustomerMenu();
        System.out.println(formatCustomerMenu.formatter(items, "Items Menu"));
    }
    public void displayMenuOption(List<ItemsMenu> menu) throws FileNotFoundException, NoSuchAlgorithmException {
        ArrayList<String> items = new ArrayList<>();

        for (ItemsMenu itemsMenu : menu) {
            String category = itemsMenu.getType();
            String name = itemsMenu.getName();
            String price = itemsMenu.getPrice();

            if (category.equalsIgnoreCase("food")) {
                items.add(name + "-" + price);
            } else if (category.equalsIgnoreCase("beverages")) {
                items.add(name + " - " + price);
            } else {
                System.out.println("Check for errors in menuItems.csv");
            }
        }

        int lineNumber = 1;
        for (int i = 0; i < items.size(); i++) {
            String item = lineNumber + ". " + items.get(i);
            items.set(i, item);
            lineNumber++;
        }

        items.add(lineNumber++ + ". Confirm order");
        items.add(lineNumber++ + ". Cancel");
        FormatCustomerMenu formatCustomerMenu = new FormatCustomerMenu();
        System.out.println(formatCustomerMenu.formatter(items, "Items Menu"));
        Scanner sc = new Scanner(System.in);
        int returnOption = sc.nextInt();
        if(returnOption == 25){
            WaiterMenu waiterMenu = new WaiterMenu();
            waiterMenu.waiterMenu();
        }
    }
}