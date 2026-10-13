package org.example;

import EnumType.VechileType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import processing.MatchProcessing;
import structure.CourierDetail;
import structure.CourierDetailList;
import structure.GeoDetail;
import structure.OrderDetail;
import structure.OrderDetailList;

import java.util.ArrayList;

@SpringBootApplication(scanBasePackages = {
        "org.example",
        "processing",
        "Processing",
        "scorer"
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 创建模拟骑手池
    @Bean
    public CourierDetailList courierDetailList() {
        ArrayList<CourierDetail> couriers = new ArrayList<>();

        couriers.add(createCourier("C001", true, 3, 5.0, VechileType.BICYCLE, 121.4700, 31.2300));
        couriers.add(createCourier("C002", true, 1, 8.0, VechileType.MOTORCYCLE, 121.4710, 31.2310));
        couriers.add(createCourier("C003", false, 3, 5.0, VechileType.BICYCLE, 121.4720, 31.2320));
        couriers.add(createCourier("C004", true, 0, 8.0, VechileType.MOTORCYCLE, 121.4730, 31.2330));

        return new CourierDetailList(couriers);
    }

    // 创建模拟订单池
    @Bean
    public OrderDetailList orderDetailList() {
        ArrayList<OrderDetail> orders = new ArrayList<>();


        orders.add(createOrder("O001", true, "黄浦区", 35, 121.4740, 31.2340, 121.4800, 31.2400));
        orders.add(createOrder("O002", true, "黄浦区", 48, 121.4750, 31.2350, 121.4810, 31.2410));
        orders.add(createOrder("O003", true, "黄浦区", 62, 121.4760, 31.2360, 121.4820, 31.2420));
        orders.add(createOrder("O004", false, "黄浦区", 28, 121.4745, 31.2345, 121.4805, 31.2405));
        orders.add(createOrder("O005", true, "徐汇区", 50, 121.4400, 31.1900, 121.4500, 31.2000));

        return new OrderDetailList(orders);
    }

    // Spring启动后执行一次匹配
    @Bean
    public CommandLineRunner run(MatchProcessing matchProcessing, CourierDetailList couriers, OrderDetailList orders) {

        return args -> {
            System.out.println("Spring 启动成功，开始调用匹配 Service");
            System.out.println("骑手池数量：" + couriers.getCourierDetailArrayList().size());
            System.out.println("订单池数量：" + orders.getOrderDetailList().size());
            var result = matchProcessing.process();
            System.out.println("匹配完成，骑手数量：" + result.size());

            // 打印每个骑手实际接到的订单
            result.forEach((courier, assignedOrders) -> {
                System.out.println("骑手：" + courier.getCourierId() + "，剩余容量：" + courier.getAvailableCapacity());
                for (OrderDetail order : assignedOrders.getOrderDetailList()) {
                    System.out.println("  接到订单：" + order.getOrderId());
                }
            });

            // 打印所有订单的最终指派结果
            System.out.println("----- 订单指派结果 -----");

            for (OrderDetail order : orders.getOrderDetailList()) {
                String courierId = order.getCourierId();
                System.out.println("订单：" + order.getOrderId() + "，骑手：" + (courierId == null ? "未指派" : courierId)
                );
            }
        };
    }

    // 私有化 避免其他方法调用
    private CourierDetail createCourier(String id, boolean available, int capacity, double speed, VechileType vehicle, double longitude, double latitude) {

        CourierDetail courier = new CourierDetail();

        courier.setCourierId(id);
        courier.setAvailable(available);
        courier.setMaxCapacity(3);
        courier.setAvailableCapacity(capacity);

        // 测试数据约定：速度为米/秒
        courier.setSpeed(speed);
        courier.setVechileType(vehicle);

        courier.setRegion("华东");
        courier.setProvince("上海");
        courier.setCity("上海");
        courier.setDistinct("黄浦区");

        // GeoDetail 参数顺序：经度、纬度
        courier.setGeoInfo(new GeoDetail(longitude, latitude));

        // 模拟下一配送点，避免 nextPoint 为 null
        courier.setNextPoint(new GeoDetail(longitude + 0.01, latitude + 0.01));

        // 初始化已接订单集合，避免添加订单时空指针
        courier.setOrderDetailList(new OrderDetailList());

        return courier;
    }

    // 创建订单，避免重复填写属性
    private OrderDetail createOrder(String id, boolean ready, String district, int price, double startLongitude, double startLatitude, double endLongitude, double endLatitude) {

        OrderDetail order = new OrderDetail();

        order.setOrderId(id);
        order.setReady(ready);
        order.setDispatch(false);
        order.setCourierId(null);
        order.setPrice(price);

        order.setRegion("华东");
        order.setProvince("上海");
        order.setCity("上海");
        order.setDistinct(district);

        order.setStartPointGeo(new GeoDetail(startLongitude, startLatitude));

        order.setEndPointGeo(new GeoDetail(endLongitude, endLatitude));

        order.setCourierDetailSet(new CourierDetailList(new ArrayList<>()));

        return order;
    }
}