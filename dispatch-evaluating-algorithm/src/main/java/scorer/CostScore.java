package scorer;

import org.springframework.stereotype.Component;
import structure.CourierDetail;
import structure.OrderDetail;

import java.util.List;

@Component
public class CostScore implements Score{
    @Override
    public double score(CourierDetail courier, List<OrderDetail> orders) {
        if(orders.size() >= 2){
            return 0;
        }
        else{
            return 1;
        }
    }
}
