package menus;

import entities.ItemsMenu;
import entities.Order;
import entities.Table;
import enums.OrderStatus;
import menus.DisplayMenu;
import utilities.ChangeOrderStatus;
import utilities.Formatter;
import utilities.PopulateItemsMenu;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import utilities.WriteOrderToFile;
import utilities.WriteOrderToFile;


public class NewOrderMenu {
    public static void  selectTable() throws FileNotFoundException, NoSuchAlgorithmException {
        Formatter formatter = new Formatter();
        String title = ("Select Table");
        String menu = "Select a table of 1 to 10";
        System.out.println(formatter.formatter(menu, title));
        Scanner sc = new Scanner(System.in);
        int tableNumber = (sc.nextInt()-1);
        if(tableNumber > 0 && tableNumber <= 10) {
            newOrderMenu(tableNumber);
        }
        else {
            System.out.println("The table number should be between 1 and 10");
            selectTable();
        }
    }

    public static void newOrderMenu(int table) throws FileNotFoundException, NoSuchAlgorithmException {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        Formatter formatter = new Formatter();
        PopulateItemsMenu populateItemsMenu = new PopulateItemsMenu();
        List<ItemsMenu> menu = populateItemsMenu.populateItemsMenu();
        boolean isConfirmed = false;
        DisplayMenu displayMenu = new DisplayMenu();
        Scanner scanner = new Scanner(System.in);
        double total = 0;
        ArrayList<String> items = new ArrayList<>();
        while (!isConfirmed) {
            displayMenu.displayMenu(menu);
            System.out.println(formatter.formatter(String.valueOf(total), "Total"));
            System.out.println("Enter the number of the item you want to add to your order");

            int selection = scanner.nextInt();
            if (selection == menu.size()+1) {
                isConfirmed = true;
                System.out.println("Order confirmed!");
            } else if (selection >= 0 && selection <= menu.size()+1) {
                ItemsMenu selectedMenu = menu.get(selection-1);
                total += Double.parseDouble(selectedMenu.getPrice());
                items.add(selectedMenu.getType().toString() + "," + selectedMenu.getName().toString() + "," + selectedMenu.getPrice().toString());
            } else {
                System.out.println("Invalid selection. Please enter a valid item number.");
            }
        }
        Order order = new Order(new Table(table, false), date, time, total, OrderStatus.NON_PROCESSED, items);
        WriteOrderToFile.writeOrderToFile(order, "./src/persistent/orders.csv");
        WaiterMenu waiterMenu = new WaiterMenu();
        waiterMenu.waiterMenu();
    }
}