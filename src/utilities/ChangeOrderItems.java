package utilities;

import entities.ItemsMenu;
import entities.Order;
import menus.DisplayMenu;
import menus.WaiterMenu;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeOrderItems {
    private ArrayList<Order> orders;

    public void changeOrderItems() throws FileNotFoundException, NoSuchAlgorithmException {
        WaiterMenu waiterMenu = new WaiterMenu();
        ReadOrdersFromFile readOrdersFromFile = new ReadOrdersFromFile();
        this.orders = readOrdersFromFile.readOrdersFromFile();
        FormatOrdersMenu formatOrdersMenu = new FormatOrdersMenu();
        FormatOrderItems formatOrderItems = new FormatOrderItems();
        System.out.println(formatOrdersMenu.formatOrdersMenu(this.orders, "Change order items"));
        System.out.println("Enter number of order to change item: ");
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();
        String order = "";
        if (selection > 0 && selection <= orders.size()) {
            for (int i = 0; i < orders.get(selection - 1).getMenuItems().size(); i++) {
                order = orders.get(selection - 1).getMenuItems().get(i).replace("[", "");
            }
            System.out.println(formatOrderItems.formatOrderItems(orders.get(selection - 1), "items in the order"));
            System.out.println("1. Add item\n2. Remove item\n3. Back");
            int operationIndex = sc.nextInt();
            switch (operationIndex) {
                case 1:
                    addItemToOrder(selection);
                    break;
                case 2:
                    removeItemFromOrder(selection);
                    break;
                case 3:
                    waiterMenu.waiterMenu();
                default:
                    System.out.println("Invalid operation");
            }
        }
    }

    private void updateOrderFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/persistent/orders.csv"))) {
            for (Order order : orders) {
                String orderString = orderToString(order);
                writer.write(orderString);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        for (int i = 0; i < order.getMenuItems().size(); i++) {
            if(i < order.getMenuItems().size()) {
                sb.append(order.getMenuItems().get(i)).append(", ");
            }
            else {
                sb.append(order.getMenuItems().get(i));
            }
        }
        if (!order.getMenuItems().isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");

        return sb.toString();
    }

    private void removeItemFromOrder(int selection) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the index of the item to remove: ");
        int itemIndex = sc.nextInt();
        if (itemIndex > 0 && itemIndex <= orders.get(selection - 1).getMenuItems().size()) {
            orders.get(selection - 1).getMenuItems().remove(itemIndex - 1);
            System.out.println("Item removed successfully.");
            updateOrderFile();
        } else {
            System.out.println("Invalid item index.");
        }
    }

    private void addItemToOrder(int selection) throws FileNotFoundException, NoSuchAlgorithmException {
        PopulateItemsMenu populateItemsMenu = new PopulateItemsMenu();
        DisplayMenu displayMenu = new DisplayMenu();
        Scanner sc = new Scanner(System.in);
        List<ItemsMenu> menuItems = populateItemsMenu.populateItemsMenu();  // Initialize menuItems

        displayMenu.displayMenu(menuItems);

        while (selection > 0 && selection <= orders.size()) {
            System.out.println("Enter item number (or enter 24 to finish adding items): ");
            int newItemIndex = sc.nextInt();

            if (newItemIndex == 24) {
                System.out.println("Finished adding items.");
                break;
            }

            // Check if the newItemIndex is within the valid range
            if (newItemIndex > 0 && newItemIndex <= menuItems.size()) {
                String newItem = menuItems.get(newItemIndex - 1).toString();
                orders.get(selection - 1).getMenuItems().add(newItem);
                System.out.println("Item added successfully.");
                updateOrderFile();
            } else {
                System.out.println("Invalid item number.");
            }
        }

        System.out.println("Invalid order selection.");
    }

}
