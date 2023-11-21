package entities;

public class ItemsMenu implements Comparable{
    private String type;
    private String name;
    private String price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ItemsMenu(String type, String name, String price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
