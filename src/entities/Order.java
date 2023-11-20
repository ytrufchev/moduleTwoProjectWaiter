package entities;

import enums.OrderStatus;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Order {
    Table table;
    private LocalDate date;
    private LocalTime time;
    private double sumPrice;
    private OrderStatus status;
    private List<String> menuItems;

    // Конструктор
    public Order(Table table, LocalDate date, LocalTime time, double sumPrice, OrderStatus status, List<String> menuItems) {
        this.table = table;
        this.date = date;
        this.time = time;
        this.sumPrice = sumPrice;
        this.status = status;
        this.menuItems = menuItems;
    }


    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<String> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<String> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "Order {" +
                "table = " + table +
                ", date = " + date +
                ", time = " + time +
                ", sumPrice = " + sumPrice +
                ", status = " + status +
                ", menuItems = " + menuItems +
                '}';
    }
}
