package matcher;

import structure.CourierDetail;
import structure.CourierDetailList;
import structure.CourierOrderScore;
import structure.OrderDetailList;

import java.util.HashMap;
import java.util.List;

public interface Matcher {
    public abstract HashMap<CourierDetail,OrderDetailList> compute(CourierDetail courierDetail, List<CourierOrderScore> orderDetailLists);

}
