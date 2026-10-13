package matcher;

import structure.CourierDetail;
import structure.CourierOrderScore;
import structure.OrderDetail;
import structure.OrderDetailList;
import java.util.*;

public class GreedySearch implements Matcher {


    @Override
public HashMap<CourierDetail, OrderDetailList> compute(
        CourierDetail courierDetail,
        List<CourierOrderScore> orderDetailLists) {

    HashMap<CourierDetail, OrderDetailList> result = new HashMap<>();

    if (courierDetail == null
            || orderDetailLists == null
            || orderDetailLists.isEmpty()) {
        return result;
    }

    CourierOrderScore bestCandidate = null;
    double bestScore = Double.POSITIVE_INFINITY;

    for (CourierOrderScore candidate : orderDetailLists) {

        if (candidate == null
                || candidate.getOrders() == null
                || candidate.getOrders().isEmpty()) {
            continue;
        }

        double score = candidate.getScore();

        // 排除 NaN 和无穷大
        if (!Double.isFinite(score)) {
            continue;
        }

        if (score < bestScore) {
            bestScore = score;
            bestCandidate = candidate;
        }
    }

    if (bestCandidate != null) {
        OrderDetailList selectedOrders = new OrderDetailList(new ArrayList<>(bestCandidate.getOrders())
        );

        result.put(courierDetail, selectedOrders);
    }

    return result;
}
}
