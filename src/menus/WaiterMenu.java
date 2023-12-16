package menus;

import entities.ItemsMenu;
import entities.Order;
import utilities.*;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class WaiterMenu {

    public void waiterMenu() throws FileNotFoundException, NoSuchAlgorithmException {
        ChangeOrderStatus changeOrderStatus = new ChangeOrderStatus();
        ChangeMenuItems changeMenuItems = new ChangeMenuItems();
        DisplayMenu displayMenu = new DisplayMenu();
        PopulateItemsMenu populateItemsMenu = new PopulateItemsMenu();
        ChangeOrderItems changeOrderItems = new ChangeOrderItems();
        List<ItemsMenu> PopulateItemsMenu = populateItemsMenu.populateItemsMenu();
        DisplayOrders displayOrders = new DisplayOrders();
        String menu = "1.Create order\n2.Edit order\n3.Change order status\n4.View menu\n5.Edit menu\n6.View orders\n7.Back";
        String title = "Waiter Menu";
        Formatter formatter = new Formatter();
        System.out.println(formatter.formatter(menu, title));
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        switch (selection){
            case 1 : NewOrderMenu.selectTable(); break;
            case 2 : changeOrderItems.changeOrderItems(); break;
            case 3 : changeOrderStatus.changeOrderStatus("waiter"); break;
            case 4 : displayMenu.displayMenu(PopulateItemsMenu); break;
            case 5 : changeMenuItems.modifyMenuItems(); break;
            case 6 : displayOrders.displayOrders(); break;
            case 7 : MainMenu.mainMenu();
            default : System.out.println("Invalid option"); break;
        }
        scanner.close();
    }
}
