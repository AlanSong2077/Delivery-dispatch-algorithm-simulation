package DataStructure;

public class CourierDetail {
    // 记录准备匹配的单个骑手细节

    // 骑手的固有属性
    boolean isAvailable; // 骑手是否可用
    int maxCapacity = 3; // 骑手可负载的最大容量
    VechileType vechileType = VechileType.BICYCLE; // 骑手载具
    int workDuration = 0; // 已工作时间小时
    CourierType courierType = CourierType.SelfOperated; // 骑手类型；

    int availableCapacity;

    // 骑手关联的属性
    GeoEnvDetail geoInfo;// 骑手现在的地理位置

    OrderDetailList orderDetailList;// 已指派订单集合

    public CourierDetail(boolean isAvailable, GeoEnvDetail geoInfo) {
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

    public GeoEnvDetail getGeoInfo() {
        return geoInfo;
    }

    public void setGeoInfo(GeoEnvDetail geoInfo) {
        this.geoInfo = geoInfo;
    }

    public OrderDetailList getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(OrderDetailList orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
