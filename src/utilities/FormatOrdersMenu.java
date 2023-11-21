package utilities;

import entities.Order;

import java.util.ArrayList;

public class FormatOrdersMenu {
    public String formatOrdersMenu(ArrayList<Order> orders, String title) {
        StringBuilder sb = new StringBuilder();
        int frameWidth = 120;
        String frameGlyph = "*";
        String border = frameGlyph + frameGlyph.repeat(frameWidth - 2) + frameGlyph + "\n";

        sb.append(border);

        // Print the title centered under the top border
        int titlePadding = (frameWidth - 2 - title.length()) / 2;
        String formattedTitle = frameGlyph + " ".repeat(titlePadding) + title + " ".repeat(titlePadding) + frameGlyph + "\n";
        sb.append(formattedTitle);

        sb.append(border);

        String header = "Order Number | Time of order | Table | Price | Status";
        int headerPadding = (frameWidth - 2 - header.length()) / 2;
        String headerFormatted = frameGlyph + " ".repeat(headerPadding) + header + " ".repeat(headerPadding) + frameGlyph + "\n" +
                frameGlyph + " ".repeat(headerPadding) + "-".repeat(header.length()) + " ".repeat(headerPadding) + frameGlyph + "\n";
        sb.append(headerFormatted);

        // Iterate over orders and format each line
        for (Order order : orders) {
            int orderString = (order.getTime().toString() + order.getTable().toString() + String.valueOf(order.getSumPrice()) + order.getStatus()).length();
            int orderLength = (frameWidth - 2 - orderString);
            String formattedLine = frameGlyph + " ".repeat(37) +
                    String.format("%-5d", orders.indexOf(order)+1) + " | " +
                    String.format("%-15s", order.getTime()) + " | " +
                    String.format("%-5d", order.getTable().getNumberOfTable()) + " | " +
                    String.format("%-5.2f", order.getSumPrice()) + " | " +
                    String.format("%-15s", order.getStatus()) + " ".repeat((frameWidth - 2 - (orderLength+50) / 2)) +
                    frameGlyph + "\n";
            sb.append(formattedLine);
        }

        sb.append(border);
        return sb.toString();
    }
}
