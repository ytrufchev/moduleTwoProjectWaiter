package utilities;

import entities.Order;

import java.util.ArrayList;
import java.util.List;

public class DisplayOrders {
    List<Order> orders;
    public void displayOrders(){
        ReadOrdersFromFile readOrdersFromFile = new ReadOrdersFromFile();
        this.orders = readOrdersFromFile.readOrdersFromFile();
            FormatOrdersMenu formatOrdersMenu = new FormatOrdersMenu();
            System.out.println(formatOrdersMenu.formatOrdersMenu((ArrayList<Order>) this.orders, "View all orders"));
    }
}
