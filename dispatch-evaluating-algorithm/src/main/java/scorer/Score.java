package scorer;

import structure.CourierDetail;
import structure.OrderDetail;
import java.util.List;

public interface Score {
    double score(CourierDetail courier,
                 List<OrderDetail> orders);
}
