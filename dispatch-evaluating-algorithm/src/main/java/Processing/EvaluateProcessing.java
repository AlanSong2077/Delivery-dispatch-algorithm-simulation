package Processing;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import scorer.Score;
import structure.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class EvaluateProcessing {

    @Autowired
    CourierDetailList courierDetailList;

    @Autowired
    OrderDetailList orderDetailList;

    @Autowired
    private BatchProcessing batchProcessing;

    @Autowired
    private List<Score> scoreCalculators;

    private static final Logger log = LoggerFactory.getLogger(EvaluateProcessing.class);

    // 注意跨模块依赖
    public HashMap<CourierDetail, List<CourierOrderScore>> Process() {
        // 数据处理
        HashMap<CourierDetail, ArrayList<OrderDetail>> batchResult = batchProcessing.process();
        Set<Map.Entry<CourierDetail, ArrayList<OrderDetail>>> entries = batchResult.entrySet();
        HashMap<CourierDetail, List<CourierOrderScore>> evaluateResult = new HashMap<>();

        for (Map.Entry<CourierDetail, ArrayList<OrderDetail>> entry : entries) {

            CourierDetail courier = entry.getKey();
            ArrayList<OrderDetail> orders = entry.getValue();
            double score = scoreCal(courier, orders,scoreCalculators);
            log.info("Courier - Order Score is {}",score);
            if(score <= 3) {
                CourierOrderScore cos = new CourierOrderScore(orders, score);
                // 一个骑手对应一个 List，没有就新建
                evaluateResult.computeIfAbsent(courier, k -> new ArrayList<>()).add(cos);
            }
        }
        // 修改被过滤的订单到订单集合；把过滤掉的集合标记上
        // 这个数据结构更适合被设计为Map
        for (int i = 0; i < orderDetailList.getOrderDetailList().size(); i++) {
            boolean isAdjusted = false;
            for (Map.Entry<CourierDetail, List<CourierOrderScore>> courierDetailListEntry : evaluateResult.entrySet()) {
                for (CourierOrderScore courierOrderScore : courierDetailListEntry.getValue()) {
                    for (OrderDetail order : courierOrderScore.getOrders()) {
                        if (orderDetailList.getOrderDetailList().get(i).getOrderId().equals(order.getOrderId())) {
                            isAdjusted = true;
                            break;
                        }
                    }
                    orderDetailList.getOrderDetailList().get(i).setDispatch(false);
                }
            }
        }

        // 返回可被指派的骑手-订单对；
        return evaluateResult;


        }

        public double scoreCal(CourierDetail courier, ArrayList<OrderDetail> ordersPair,List<Score> scoreCalculators){
            double scores = 0;
            for (Score scoreCalculator : scoreCalculators) {
                    scores += scoreCalculator.score(courier,ordersPair);
            }
            return scores;
        }
    }


