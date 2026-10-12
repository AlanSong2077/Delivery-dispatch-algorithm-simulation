package scoreutils;

import structure.CourierDetail;
import structure.OrderDetail;
import java.util.List;

public interface Score {
    double Score(CourierDetail courier,
                 List<OrderDetail> orders);
}
