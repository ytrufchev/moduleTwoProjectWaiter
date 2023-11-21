package menus;

import entities.ItemsMenu;
import entities.Order;
import utilities.Formatter;
import utilities.PopulateItemsMenu;

import java.util.Scanner;

public class WaiterMenu {

    public void waiterMenu(){
        PopulateItemsMenu populateItemsMenu = new PopulateItemsMenu();
        String menu = "1.Create order\n2.Edit order\n3.Change order status\n4.View menu\n5.Edit menu\n6.View orders";
        String title = "Waiter Menu";
        Formatter formatter = new Formatter();
        System.out.println(formatter.formatter(menu, title));
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        switch (selection){
            case 1 : NewOrderMenu.selectTable(); break;
            case 2 : System.exit(0); break;
            case 3 : System.exit(0); break;
            case 4 : System.exit(0); break;
            case 5 : System.exit(0); break;
            case 6 : System.exit(0); break;
            default : System.out.println("Invalid option"); break;
        }
        scanner.close();
    }
}
