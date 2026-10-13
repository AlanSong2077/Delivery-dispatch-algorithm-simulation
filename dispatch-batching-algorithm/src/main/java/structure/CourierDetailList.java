package structure;

import java.util.ArrayList;

public class CourierDetailList {

    // 定义属性

    ArrayList<CourierDetail> courierDetailArrayList;

    public CourierDetailList() {
    }

    public CourierDetailList(ArrayList<CourierDetail> courierDetailArrayList) {
        this.courierDetailArrayList = courierDetailArrayList;
    }

    public ArrayList<CourierDetail> getCourierDetailArrayList() {
        return courierDetailArrayList;
    }

    public void setCourierDetailArrayList(ArrayList<CourierDetail> courierDetailArrayList) {
        this.courierDetailArrayList = courierDetailArrayList;
    }
}
