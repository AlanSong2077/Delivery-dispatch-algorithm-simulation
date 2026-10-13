package structure;

import java.util.ArrayList;

public class OrderDetailList {
    ArrayList<OrderDetail> OrderDetailList;

    public <E> OrderDetailList(ArrayList<E> es) {
    }

    public ArrayList<OrderDetail> getOrderDetailList() {
        return OrderDetailList;
    }

    public void setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
        OrderDetailList = orderDetailList;
    }
}
