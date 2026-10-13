package structure;

import java.util.ArrayList;

public class OrderDetailList {

    private ArrayList<OrderDetail> orderDetailList;

    public OrderDetailList() {
        this.orderDetailList = new ArrayList<>();
    }

    public OrderDetailList(ArrayList<OrderDetail> orders) {
        this.orderDetailList = new ArrayList<>(orders);
    }

    public ArrayList<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(
            ArrayList<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}