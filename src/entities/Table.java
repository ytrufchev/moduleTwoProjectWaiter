package entities;

public class Table {
    private int numberOfTable;
    private boolean statusFree;

    public Table(int numberOfTable, boolean statusFree) {
        this.numberOfTable = numberOfTable;
        this.statusFree = statusFree;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
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
                ", Status 'free' = " + statusFree +
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
