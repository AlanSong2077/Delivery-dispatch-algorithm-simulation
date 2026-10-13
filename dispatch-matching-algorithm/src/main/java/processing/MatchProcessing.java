package processing;

import Processing.EvaluateProcessing;
import matcher.GreedySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import structure.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MatchProcessing {

    @Autowired
    private OrderDetailList orderDetailList;

    @Autowired
    private EvaluateProcessing evaluateProcessing;

    public HashMap<CourierDetail, OrderDetailList> process() {

        HashMap<CourierDetail, OrderDetailList> matchResult = new HashMap<>();

        List<OrderDetail> pendingOrders = orderDetailList.getOrderDetailList();

        // NPE 防御性编程
        if (pendingOrders == null || pendingOrders.isEmpty()) {
            return matchResult;
        }

        HashMap<CourierDetail, List<CourierOrderScore>> evaluateResult = evaluateProcessing.Process();

        // NPE 防御性编程
        if (evaluateResult == null || evaluateResult.isEmpty()) {
            return matchResult;
        }

        GreedySearch gs = new GreedySearch();

        // 获取本轮尚未分配的订单，只修改临时集合，不删除原始订单记录。

        Set<OrderDetail> remainingOrders = new HashSet<>(pendingOrders);

        // 为每个骑手选择一个不与之前分配冲突的组合
        for (Map.Entry<CourierDetail, List<CourierOrderScore>> entry : evaluateResult.entrySet()) {
            CourierDetail courier = entry.getKey();
            List<CourierOrderScore> candidates = entry.getValue();

            if (courier == null || candidates == null || candidates.isEmpty() || !courier.isAvailable() || courier.getAvailableCapacity() <= 0) {
                continue;
            }

            List<CourierOrderScore> availableCandidates = new ArrayList<>();

            CandidatesCheck(candidates, remainingOrders, courier, availableCandidates);

            if (availableCandidates.isEmpty()) {
                continue;
            }

            HashMap<CourierDetail, OrderDetailList> selected = gs.compute(courier, availableCandidates);

            if (selected == null) {
                continue;
            }

            OrderDetailList assigned = selected.get(courier);

            if (assigned == null || assigned.getOrderDetailList() == null || assigned.getOrderDetailList().isEmpty()) {
                continue;
            }

            matchResult.put(courier, assigned);

            // 立即移除，避免后续骑手重复选择
            remainingOrders.removeAll(assigned.getOrderDetailList());
        }

        UpdateCourierOrderContext(matchResult);

        return matchResult;
    }

    private static void CandidatesCheck(List<CourierOrderScore> candidates, Set<OrderDetail> remainingOrders, CourierDetail courier, List<CourierOrderScore> availableCandidates) {
        for (CourierOrderScore candidate : candidates) {
            if (candidate == null) {
                continue;
            }
            List<OrderDetail> candidateOrders = candidate.getOrders();

            if (candidateOrders == null || candidateOrders.isEmpty() || !Double.isFinite(candidate.getScore())) {
                continue;
            }

            // 无效订单或组合内重复订单，跳过
            if (candidateOrders.contains(null) || new HashSet<>(candidateOrders).size() != candidateOrders.size()) {
                continue;
            }

            // 全部订单都必须仍然待分配
            if (!remainingOrders.containsAll(candidateOrders)) {
                continue;
            }

            // 不能超过骑手剩余容量
            if (candidateOrders.size() > courier.getAvailableCapacity()) {
                continue;
            }

            availableCandidates.add(candidate);
        }
    }

    private static void UpdateCourierOrderContext(HashMap<CourierDetail, OrderDetailList> matchResult) {
        // 更新实际匹配成功的骑手和订单
        for (Map.Entry<CourierDetail, OrderDetailList> entry : matchResult.entrySet()) {

            CourierDetail courier = entry.getKey();

            List<OrderDetail> assignedOrders = entry.getValue().getOrderDetailList();

            for (OrderDetail order : assignedOrders) {

                // 加入骑手当前配送订单集合
                courier.addOrderDetailList(order);
                // 记录订单对应的骑手
                order.setCourierId(courier.getCourierId());
            }
            // 接单后减少剩余容量
            courier.setAvailableCapacity(courier.getAvailableCapacity() - assignedOrders.size());
        }
    }
}