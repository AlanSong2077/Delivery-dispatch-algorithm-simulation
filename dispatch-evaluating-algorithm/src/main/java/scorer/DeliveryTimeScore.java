package scorer;

import org.springframework.stereotype.Component;
import structure.CourierDetail;
import structure.OrderDetail;
import utils.DistanceCalculation;

import java.util.List;

@Component
public class DeliveryTimeScore implements Score {

    @Override
    public double score(CourierDetail i, List<OrderDetail> orders) {
        double speed = i.getSpeed();
        double startPEndpointTime = 0;
        for (OrderDetail j : orders) {
            // 拿到了每个订单的距离 距离不是考虑当前配送路径的修改后距离 需要改
            // todo
            double startPEndpointDistance = DistanceCalculation.geoCalculation(j.getEndPointGeo().getLatitude(),j.getEndPointGeo().getLongitude(),j.getStartPointGeo().getLatitude(),j.getStartPointGeo().getLongitude());
            startPEndpointTime += startPEndpointDistance/speed;
        }
        // 计算平均配送时长、平均取餐时长
        startPEndpointTime = startPEndpointTime/orders.size();

        return startPEndpointTime/100.0f;


    }
}
