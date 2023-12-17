package entities;

import menus.NewOrderMenu;
import utilities.ReadOrdersFromFile;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Table {
    private int numberOfTable;
    private boolean statusFree;

    public Table(int numberOfTable, boolean statusFree) {
        this.numberOfTable = numberOfTable;
        this.statusFree = statusFree;
    }
    public Table(){
        this.numberOfTable = 1;
        this.statusFree = false;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable){
        if(numberOfTable > 0 && numberOfTable <= 10) {
            this.numberOfTable = numberOfTable;
        }
        else {
            try {
                NewOrderMenu.selectTable();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isStatusFree() {
        return statusFree;
    }

    public void setStatusFree(boolean statusFree) {
        this.statusFree = statusFree;
    }

    @Override
    public String toString() {
        return "Table {" +
                "Number of table = " + numberOfTable +
                ", OrderStatus 'free' = " + statusFree +
                '}';
    }

    public static void tableInit(String[] args) {

        Table[] tables = new Table[10];

        for (int i = 0; i < 10; i++) {
            tables[i] = new Table(i + 1, true);
        }


        for (Table table : tables) {
            System.out.println(table);
        }
    }
}
