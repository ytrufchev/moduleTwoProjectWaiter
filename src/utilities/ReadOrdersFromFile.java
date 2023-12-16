package utilities;

import entities.Order;
import entities.Table;
import enums.OrderStatus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadOrdersFromFile {
    public static ArrayList<Order> readOrdersFromFile() {
        // Specify the path to your file
        String filePath = "./src/persistent/orders.csv";
        ArrayList<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                int tableNumber = Integer.parseInt(values[0])+1;
                boolean isStatusFree = Boolean.parseBoolean(values[1]);
                LocalDate date = LocalDate.parse(values[2]);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");
                LocalTime time = LocalTime.parse(values[3], formatter);

                double total = Double.parseDouble(values[4]);
                OrderStatus orderStatus = OrderStatus.valueOf(values[5]);

                List<String> items = new ArrayList<>(Arrays.asList(values).subList(6, values.length));

                List<String> parsedItems = new ArrayList<>();
                for (int i = 0; i < items.size(); i += 3) {
                    String category = items.get(i);
                    String name = items.get(i + 1);

                    String priceString = items.get(i + 2).replaceAll("[^0-9.]", "");
                    double price = Double.parseDouble(priceString);

                    parsedItems.add(category + "," + name + "," + price);
                }
                Order order = new Order(new Table(tableNumber, isStatusFree), date, time, total, orderStatus, (ArrayList<String>) parsedItems);
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return orders;
    }

}
