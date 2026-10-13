package scorer;

import org.springframework.stereotype.Component;
import structure.CourierDetail;
import structure.OrderDetail;
import utils.DistanceCalculation;

import java.util.List;

@Component
public class PickUpTimeScore implements Score {

    @Override
    public double score(CourierDetail i, List<OrderDetail> orders) {
        double speed = i.getSpeed();
        double courierStartPontTime = 0;
        for (OrderDetail j : orders) {
            // 拿到了每个订单的距离
            double courierStartPontDistance = DistanceCalculation.geoCalculation(i.getGeoInfo().getLatitude(),i.getGeoInfo().getLongitude(),j.getStartPointGeo().getLatitude(),j.getStartPointGeo().getLongitude());
            // 距离不是考虑当前配送路径的修改后距离 需要改
            // todo
            courierStartPontTime += courierStartPontDistance/speed;
        }
        // 计算平均配送时长、平均取餐时长
        courierStartPontTime = courierStartPontTime/orders.size();

        return courierStartPontTime/100.0f;
    }
}
