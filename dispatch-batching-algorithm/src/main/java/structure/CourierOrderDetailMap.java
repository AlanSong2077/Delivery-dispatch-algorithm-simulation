package structure;

import java.util.HashMap;

public class CourierOrderDetailSet {
    HashMap<CourierDetail, OrderDetailList> courierOrderDetailSet;

    public CourierOrderDetailSet(HashMap<CourierDetail, OrderDetailList> courierOrderDetailSet) {
        this.courierOrderDetailSet = courierOrderDetailSet;
    }

    public HashMap<CourierDetail, OrderDetailList> getCourierOrderDetailSet() {
        return courierOrderDetailSet;
    }

    public void setCourierOrderDetailSet(HashMap<CourierDetail, OrderDetailList> courierOrderDetailSet) {
        this.courierOrderDetailSet = courierOrderDetailSet;
    }
}
