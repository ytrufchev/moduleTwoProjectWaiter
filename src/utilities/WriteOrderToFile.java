package utilities;

import entities.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteOrderToFile {
    public static void writeOrderToFile(Order order, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(orderToString(order));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String orderToString(Order order) {
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
        sb.delete(sb.length() - 2, sb.length()); // Remove the trailing comma and space
        sb.append("]");
        return sb.toString();
    }
}
