package utilities;

import entities.Order;
import entities.Table;
import enums.OrderStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class ChangeOrderStatus {
    private ArrayList<Order> orders;
    private ArrayList<Order> cookOrders;

    public void changeOrderStatus(String role) {
        ReadOrdersFromFile readOrdersFromFile = new ReadOrdersFromFile();
        this.orders = readOrdersFromFile.readOrdersFromFile();
        this.cookOrders = new ArrayList<>();
        for (Order order: orders) {
            if(order.getStatus().toString().equals("NON_PROCESSED") || order.getStatus().toString().equals("COOKING") ){
                this.cookOrders.add(order);
            }
        }
        if (role.equalsIgnoreCase("waiter")) {
            FormatOrdersMenu formatOrdersMenu = new FormatOrdersMenu();
            Formatter formatter = new Formatter();
            System.out.println(formatOrdersMenu.formatOrdersMenu(this.orders, "Change order status"));
            System.out.println("Enter number of order to change its status: ");
            Scanner sc = new Scanner(System.in);
            int selection = sc.nextInt();
            if (selection > 0 && selection <= orders.size()) {
                System.out.println(formatter.formatter("1. Cooking\n2. Prepared\n3. Served\n4. Paid\n5. Non processed", "Order statuses"));
                System.out.println("Select new status: ");
                int status = sc.nextInt();
                switch (status) {
                    case 1 : orders.get(selection-1).setStatus(OrderStatus.COOKING); break;
                    case 2 : orders.get(selection-1).setStatus(OrderStatus.PREPARED); break;
                    case 3 : orders.get(selection-1).setStatus(OrderStatus.SERVED); break;
                    case 4 : orders.get(selection-1).setStatus(OrderStatus.PAID); break;
                    case 5 : orders.get(selection-1).setStatus(OrderStatus.NON_PROCESSED); break;
                    default:
                        System.out.println("something is wrong"); break;
                }
                updateOrderFile();
            }
        } else if (role.equalsIgnoreCase("cook")) {
            FormatOrdersMenu formatOrdersMenu = new FormatOrdersMenu();
            Formatter formatter = new Formatter();
            System.out.println(formatOrdersMenu.formatOrdersMenu(this.cookOrders, "Change order status"));
            System.out.println("Enter number of order to change its status: ");
            Scanner sc = new Scanner(System.in);
            int selection = sc.nextInt();
            if (selection > 0 && selection <= cookOrders.size()) {
                System.out.println(formatter.formatter("1. Cooking\n2. Prepared", "Order statuses"));
                System.out.println("Select new status: ");
                int status = sc.nextInt();
                switch (status) {
                    case 1 : orders.get(cookOrders.indexOf(selection-1)).setStatus(OrderStatus.COOKING); break;
                    case 2 : orders.get(cookOrders.indexOf(selection-1)).setStatus(OrderStatus.PREPARED); break;
                    default:
                        System.out.println("something is wrong"); break;
                }
                updateOrderFile();
            }
        } else {
            System.out.println("There's an issue with the roles");
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
        sb.append("[");
        for (String item : order.getMenuItems()) {
            sb.append(item).append(", ");
        }
        if (!order.getMenuItems().isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");

        return sb.toString();
    }
}