package utilities;

import entities.ItemsMenu;
import entities.Order;

public class FormatOrderItems {
    public String formatOrderItems(Order order, String title) {
        StringBuilder sb = new StringBuilder();

        // Calculate the maximum width needed for the content
        int maxContentWidth = calculateMaxContentWidth(order, title);

        // Set the frame width to be the maximum of content width and a minimum width
        int frameWidth = (maxContentWidth + 6); // Increased by 2 for the item number

        String frameGlyph = "*";
        String border = frameGlyph + frameGlyph.repeat(frameWidth - 2) + frameGlyph + "\n";

        sb.append(border);

        // Print the title centered under the top border
        int titlePadding = (frameWidth - title.length() - 4) / 2;
        String formattedTitle = frameGlyph + " ".repeat(titlePadding) + title + " ".repeat(titlePadding+3) + frameGlyph + "\n";
        sb.append(formattedTitle);

        sb.append(border);

        String header = "# | Category | Name | Price | Status | Table";
        int headerPadding = (frameWidth - 2 - header.length()) / 2;
        String headerFormatted = frameGlyph + " ".repeat(headerPadding) + header + " ".repeat(headerPadding+1) + frameGlyph + "\n" +
                frameGlyph + " ".repeat(headerPadding) + "-".repeat(header.length()) + " ".repeat(headerPadding+1) + frameGlyph + "\n";
        sb.append(headerFormatted);

        // Iterate over orders and format each line
        for (int i = 0; i < order.getMenuItems().size(); i++) {
            String menuItemString = order.getMenuItems().get(i);

            // Remove extra "[" characters
            menuItemString = menuItemString.replaceAll("\\[", " ");

            // Split the menuItemString by commas
            String[] menuItemFields = menuItemString.split(",");

            // Assuming menuItemFields has at least 3 elements: category, name, price
            if (menuItemFields.length >= 3) {
                String formattedLine = frameGlyph + " " +
                        String.format("%-2d", i + 1) + " | " +  // Item number
                        String.format("%-9s", menuItemFields[0]) + " | " +
                        String.format("%-50s", menuItemFields[1]) + " | " +
                        String.format("%-6.2f", Double.parseDouble(menuItemFields[2])) + " | " +
                        String.format("%-7s", order.getStatus()) + " | " +
                        String.format("%-5s", order.getTable().getNumberOfTable());

                // Calculate remaining space after appending the string
                int remainingSpace = frameWidth - formattedLine.length();

                // Adjust the spacing to move the last glyph to the end of the frame width
                sb.append(formattedLine + " ".repeat(remainingSpace - 1) + frameGlyph + "\n");
            } else {
                // Handle the case where menuItemFields doesn't have enough elements
                System.err.println("Invalid menu item format: " + menuItemString);
            }
        }

        sb.append(border);
        return sb.toString();
    }

    private int calculateMaxContentWidth(Order order, String title) {
        int maxContentWidth = 0;

        // Check the width needed for the title
        int titleWidth = title.length();

        // Check the width needed for the header
        int headerWidth = "Category | Name | Price | Status | Table".length();

        // Iterate over orders and find the maximum width needed for the content
        for (int i = 0; i < order.getMenuItems().size(); i++) {
            String menuItemString = order.getMenuItems().get(i);

            // Remove extra "[" characters
            menuItemString = menuItemString.replaceAll("\\[", "");

            // Split the menuItemString by commas
            String[] menuItemFields = menuItemString.split(",");

            // Assuming menuItemFields has at least 3 elements: category, name, price
            if (menuItemFields.length >= 3) {
                int orderString = (menuItemFields[0] + menuItemFields[1] + menuItemFields[2] + order.getStatus() + order.getTable()).length();
                maxContentWidth = Math.max(maxContentWidth, orderString);
            } else {
                // Handle the case where menuItemFields doesn't have enough elements
                System.err.println("Invalid menu item format: " + menuItemString);
            }
        }

        // Return the maximum width needed for the content, title, and header
        return Math.max(maxContentWidth, Math.max(titleWidth, headerWidth));
    }
}
