package structure;

public class OrderDetail {
    // 应该记录准备匹配的单个订单池
    String orderId;
    boolean isDispatch; // 是否准备指派；
    GeoDetail StartPointGeo; // 订单的地理位置；
    GeoDetail EndPointGeo; // 订单的地理位置；
    boolean isReady; //餐是否备好；
    int Price; //餐品价格；
    CourierDetailList courierDetailSet; // 过去已指派骑手集合
    String courierId; // 现指派的骑手id
    String region;
    String province;
    String city;
    String distinct;

    public GeoDetail getStartPointGeo() {
        return StartPointGeo;
    }

    public void setStartPointGeo(GeoDetail startPointGeo) {
        StartPointGeo = startPointGeo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public GeoDetail getEndPointGeo() {
        return EndPointGeo;
    }

    public void setEndPointGeo(GeoDetail endPointGeo) {
        EndPointGeo = endPointGeo;
    }

    public OrderDetail(boolean isReady, boolean isDispatch, int price, String region, String province, String city, String distinct, GeoDetail startPointGeo, GeoDetail endPointGeo) {
        this.isReady = isReady;
        this.isDispatch = isDispatch;
        Price = price;
        this.region = region;
        this.province = province;
        this.city = city;
        this.distinct = distinct;
        this.StartPointGeo = startPointGeo;
        this.EndPointGeo = endPointGeo;
    }

    public OrderDetail() {
    }

    public boolean isDispatch() {
        return isDispatch;
    }

    public void setDispatch(boolean dispatch) {
        isDispatch = dispatch;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public CourierDetailList getCourierDetailSet() {
        return courierDetailSet;
    }

    public void setCourierDetailSet(CourierDetailList courierDetailSet) {
        this.courierDetailSet = courierDetailSet;
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

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }
}
