package structure;

import java.util.ArrayList;
import java.util.List;

public class CourierOrderScore {

    private List<OrderDetail> orders;
    private double score;

    public CourierOrderScore() {
        this.orders = new ArrayList<>();
    }

    public CourierOrderScore(List<OrderDetail> orders, double score) {
        this.orders = orders;
        this.score = score;
    }

    public List<OrderDetail> getOrders() { return orders; }
    public void setOrders(List<OrderDetail> orders) { this.orders = orders; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}