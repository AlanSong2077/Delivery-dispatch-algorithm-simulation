package structure;

import EnumType.CourierType;
import EnumType.VechileType;

public class CourierDetail {
    // 记录准备匹配的单个骑手细节

    // 骑手的固有属性
    String courierId;
    boolean isAvailable; // 骑手是否可用
    int maxCapacity = 3; // 骑手可负载的最大容量
    VechileType vechileType = VechileType.BICYCLE; // 骑手载具
    int workDuration = 0; // 已工作时间小时
    CourierType courierType = CourierType.SelfOperated; // 骑手类型；
    String region;
    String province;
    String city;
    String distinct;
    double speed;
    double pickupDistance;
    double deliveryDistance;
    int availableCapacity;
    // 骑手关联的属性
    GeoDetail geoInfo;// 骑手现在的地理位置
    OrderDetailList orderDetailList;// 已指派订单集合
    GeoDetail nextPoint; // 下一个配送点的地理位置;
    GeoDetailList deliveryPath;


    public double getPickupDistance() {
        return pickupDistance;
    }

    public void setPickupDistance(double pickupDistance) {
        this.pickupDistance = pickupDistance;
    }

    public double getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public CourierDetail(boolean isAvailable, GeoDetail geoInfo) {
        this.isAvailable = isAvailable;
        this.geoInfo = geoInfo;
    }

    public CourierDetail() {
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public VechileType getVechileType() {
        return vechileType;
    }

    public void setVechileType(VechileType vechileType) {
        this.vechileType = vechileType;
    }

    public int getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(int workDuration) {
        this.workDuration = workDuration;
    }

    public CourierType getCourierType() {
        return courierType;
    }

    public void setCourierType(CourierType courierType) {
        this.courierType = courierType;
    }

    public GeoDetail getGeoInfo() {
        return geoInfo;
    }

    public void setGeoInfo(GeoDetail geoInfo) {
        this.geoInfo = geoInfo;
    }

    public OrderDetailList getOrderDetailList() {
        return orderDetailList;
    }

    public void addOrderDetailList(OrderDetail o) {
        orderDetailList.getOrderDetailList().add(o);
    }

    public void setOrderDetailList(OrderDetailList orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    // 这个其实应该设计成 private 避免被随便修改 应该一直都是GeoDetails的第一个
    public void setNextPoint(GeoDetail nextPoint) {
        this.nextPoint = nextPoint;
    }

    public void setNextPoint() {
        if(this.deliveryPath.getGeoDetails().size() > 0){
            this.nextPoint = this.deliveryPath.getGeoDetails().get(0);
        }
        else{
            this.nextPoint = new GeoDetail();
        }
    }

    public GeoDetail getNextPoint() {
        return nextPoint;
    }

    public GeoDetailList getDeliveryPath() {
        return deliveryPath;
    }

    public void setDeliveryPath(GeoDetailList deliveryPath) {
        this.deliveryPath = deliveryPath;
        setNextPoint();
    }

}
