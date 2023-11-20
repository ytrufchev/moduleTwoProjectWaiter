package menus;

import entities.Order;
import utilities.Formatter;

public class WaiterMenu {

    public void waiterMenu(){
        String menu = "1.Create order\n2.Edit order\n3.Change order status\n4.View menu\n5.Edit menu\n6.View orders";
        String title = "Waiter Menu";
        Formatter formatter = new Formatter();
        System.out.println(formatter.formatter(menu, title));

    }
}
