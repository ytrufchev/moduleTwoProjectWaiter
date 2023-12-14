package menus;

import entities.ItemsMenu;
import entities.Order;
import utilities.*;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class CookMenu {

    public void cookMenu() throws FileNotFoundException, NoSuchAlgorithmException {
        ChangeOrderStatus changeOrderStatus = new ChangeOrderStatus();
        DisplayOrders displayOrders = new DisplayOrders();
        String menu = "1.Change order status\n2.Back";
        String title = "Cook Menu";
        Formatter formatter = new Formatter();
        System.out.println(formatter.formatter(menu, title));
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        switch (selection){
            case 1 : changeOrderStatus.changeOrderStatus("cook"); break;
            case 2 : MainMenu.mainMenu(); break;
            default : System.out.println("Invalid option"); break;
        }
        scanner.close();
    }
}
