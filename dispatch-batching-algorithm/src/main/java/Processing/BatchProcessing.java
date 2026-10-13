package Processing;

import EnumType.VechileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import structure.*;

import java.util.ArrayList;
import java.util.HashMap;
import utils.DistanceCalculation;


@Service
public class BatchProcessing {

    @Autowired
    CourierDetailList courierDetailList;

    @Autowired
    OrderDetailList orderDetailList;

    public HashMap<CourierDetail, ArrayList<OrderDetail>> Process(){
        // 把这里的处理过程写了就行
        HashMap<CourierDetail, ArrayList<OrderDetail>> courierOrderList = new HashMap<>();
        ArrayList<CourierDetail> courierList = courierDetailList.getCourierDetailArrayList();
        ArrayList<OrderDetail> orderList = orderDetailList.getOrderDetailList();
        ArrayList<ArrayList<OrderDetail>> orderListSet = new ArrayList<>();
        // 把每个OrderDetail 单独组成ArrayList

        for(OrderDetail j: orderList){
            ArrayList<OrderDetail> tmpOrderList = new ArrayList<>();
            tmpOrderList.add(j);
            orderListSet.add(tmpOrderList);
        }

        // 订单两两组包
        for (int i = 0; i < orderList.size(); i++) {
            for (int j = i + 1; j < orderList.size(); j++) {
                orderListSet.add(OrderPair(orderList.get(i),orderList.get(j)));
            }
        }

        // 构建 骑手-订单对
        for(CourierDetail i : courierList){
            for(ArrayList<OrderDetail> j: orderListSet){
                boolean allPass = true;
                for (OrderDetail k : j) {
                    if (!filter(i, k) || !compute(i, k)) {
                        // 订单对中有一个不符合过滤条件 就直接Pass
                        allPass = false;
                        break;
                    }
                }
                if (!allPass)
                {
                    // 全部都通过 才允许进入集合
                    courierOrderList.put(i, j);

                    // 把订单集合中的订单的指派状态修改为指派

                    // 这里复杂度太高了 后续改一下
                    // todo
                    for (OrderDetail o1 : j) {
                        for (int o2 = 0; o2 < orderList.size(); o2++) {
                            if(o1.getOrderId().equals(orderList.get(o2).getOrderId())){
                                orderList.get(o2).setDispatch(true);
                            }
                        }
                    }
                }
            }

        }
        // 被过滤的订单 需要打标存下来
        // isDispatch


        // 把可被指派的骑手-订单对 保存
        return courierOrderList;
    }

    public boolean filter(CourierDetail i, OrderDetail j){

        double courierStartPontDistance = DistanceCalculation.geoCalculation(i.getGeoInfo().getLatitude(),i.getGeoInfo().getLongitude(),j.getStartPointGeo().getLatitude(),j.getStartPointGeo().getLongitude());
        double startPEndpointDistance = DistanceCalculation.geoCalculation(j.getEndPointGeo().getLatitude(),j.getEndPointGeo().getLongitude(),j.getStartPointGeo().getLatitude(),j.getStartPointGeo().getLongitude());

        if(!(i.isAvailable() && j.isReady())){
            return false;
        }

        if(!(i.getDistinct().equals(j.getDistinct()))){
            return false;
        }

        if(!(i.getAvailableCapacity() > 0)){
            return false;
        }

        if(!(courierStartPontDistance <= 5000)){
            // 骑手距离订单5km以外过滤；
            return false;
        }

        if(!(startPEndpointDistance > 5000 && i.getVechileType() != VechileType.FOOT)){
            return false;
        }

        if(!(DistanceCalculation.angleCalculation(i.getGeoInfo().getLatitude(),i.getGeoInfo().getLongitude(),i.getNextPoint().getLatitude(),i.getNextPoint().getLongitude(),j.getStartPointGeo().getLatitude(),j.getStartPointGeo().getLongitude()) < 60)){
             return false;
        }

        return true;
    }

    public  boolean compute(CourierDetail i, OrderDetail j){
        // 为其他特殊计算作准备
        return true;
    }

    public ArrayList<OrderDetail> OrderPair(OrderDetail o1, OrderDetail o2){
        ArrayList<OrderDetail> tmp = new ArrayList<>();
        if(true) {
            tmp.add(o1);
            tmp.add(o2);
        }
        return tmp;
    }

}
