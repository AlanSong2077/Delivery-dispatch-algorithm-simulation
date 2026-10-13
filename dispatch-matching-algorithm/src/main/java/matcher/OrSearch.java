package matcher;
import structure.CourierDetail;
import structure.CourierOrderScore;
import structure.OrderDetailList;
import java.util.HashMap;
import java.util.List;

public class OrSearch implements Matcher{


    @Override
    public HashMap<CourierDetail, OrderDetailList> compute(CourierDetail courierDetail, List<CourierOrderScore> orderDetailLists) {
        return null;
    }
}
