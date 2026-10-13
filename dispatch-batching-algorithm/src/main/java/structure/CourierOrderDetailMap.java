package structure;

import java.util.HashMap;

public class CourierOrderDetailMap {
    HashMap<CourierDetail, OrderDetailList> courierOrderDetailMap;

    double score;

    public CourierOrderDetailMap(HashMap<CourierDetail, OrderDetailList> courierOrderDetailSet) {
        this.courierOrderDetailMap = courierOrderDetailSet;
    }

    public HashMap<CourierDetail, OrderDetailList> getCourierOrderDetailMap() {
        return courierOrderDetailMap;
    }

    public void setCourierOrderDetailMap(HashMap<CourierDetail, OrderDetailList> courierOrderDetailSet) {
        this.courierOrderDetailMap = courierOrderDetailMap;
    }
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
