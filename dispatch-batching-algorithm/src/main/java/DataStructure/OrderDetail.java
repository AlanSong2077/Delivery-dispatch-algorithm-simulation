package DataStructure;

public class OrderDetail {
    // 应该记录准备匹配的单个订单池

    boolean isDispatch; // 是否准备指派；
    GeoEnvDetail GeoEnv; // 订单的地理位置；
    boolean isReady; //餐是否备好；
    int Price; //餐品价格；
    CourierDetailList courierDetailSet; // 已指派骑手集合

}
