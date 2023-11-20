package menus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.Format;
import java.text.Normalizer;
import java.util.ArrayList;

import utilities.FormatCustomerMenu;
import utilities.Formatter;

public class DisplayMenu {
    public void displayMenu(){
        //TODO: must remove the square brackets somehow
        try (BufferedReader reader = new BufferedReader(new FileReader("src/persistent/menuItems.csv"))) {
            String line;
            ArrayList<String> foods = new ArrayList<>();
            ArrayList<String> beverages = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                    if(values[0].equalsIgnoreCase("Food")){
                        foods.add("\n" + values[1] + " " + values[2]);
                    }
                    else if (values[0].equalsIgnoreCase("Beverages")){
                        beverages.add("\n" +  values[1] + " " + values[2]);
                    }
                    else {
                        System.out.println("Check menu for issues");
                    }
            }
            FormatCustomerMenu formatCustomerMenu = new FormatCustomerMenu();
            System.out.println(formatCustomerMenu.formatter(foods, "Foods"));
            System.out.println(formatCustomerMenu.formatter(beverages, "Beverages"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
