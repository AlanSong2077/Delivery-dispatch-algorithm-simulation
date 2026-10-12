package scoreutils;

import structure.CourierDetail;
import structure.OrderDetail;

import java.util.List;

public class CostScore implements Score{
    @Override
    public double Score(CourierDetail courier, List<OrderDetail> orders) {
        if(orders.size() >= 2){
            return 0;
        }
        else{
            return 1;
        }
    }
}
