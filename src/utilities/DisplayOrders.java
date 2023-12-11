package utilities;

import entities.Order;
import menus.WaiterMenu;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayOrders {
    List<Order> orders;
    public void displayOrders() throws FileNotFoundException, NoSuchAlgorithmException {
        WaiterMenu waiterMenu = new WaiterMenu();
        ReadOrdersFromFile readOrdersFromFile = new ReadOrdersFromFile();
        this.orders = readOrdersFromFile.readOrdersFromFile();
            FormatOrdersMenu formatOrdersMenu = new FormatOrdersMenu();
            System.out.println(formatOrdersMenu.formatOrdersMenu((ArrayList<Order>) this.orders, "View all orders"));
        System.out.println("1. back");
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();
        if(selection == 1){
            waiterMenu.waiterMenu();
        }
    }
}
