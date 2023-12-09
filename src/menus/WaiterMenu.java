package menus;

import entities.ItemsMenu;
import entities.Order;
import utilities.*;

import java.util.List;
import java.util.Scanner;

public class WaiterMenu {

    public void waiterMenu(){
        ChangeOrderStatus changeOrderStatus = new ChangeOrderStatus();
        DisplayMenu displayMenu = new DisplayMenu();
        PopulateItemsMenu populateItemsMenu = new PopulateItemsMenu();
        ChangeOrderItems changeOrderItems = new ChangeOrderItems();
        List<ItemsMenu> PopulateItemsMenu = populateItemsMenu.populateItemsMenu();
        displayMenu.displayMenu(PopulateItemsMenu);
        DisplayOrders displayOrders = new DisplayOrders();
        String menu = "1.Create order\n2.Edit order\n3.Change order status\n4.View menu\n5.Edit menu\n6.View orders";
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
            case 5 : System.exit(0); break;
            case 6 : displayOrders.displayOrders(); break;
            default : System.out.println("Invalid option"); break;
        }
        scanner.close();
    }
}
