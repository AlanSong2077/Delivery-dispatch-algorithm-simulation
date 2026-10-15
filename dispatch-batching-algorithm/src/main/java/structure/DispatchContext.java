package structure;

public class DispatchContext {
    private final int currentTime;
    private final int weather;
    private final int rainFall;
    private int courierCnt;
    private int orderCnt;
    private final String province;
    private final String city;
    private final String distinct;
    // 留一个供需比的位置 未来设计相关策略
    private double supplyemandRatio;



    public DispatchContext(int currentTime, int weather, int rainFall, String province, String city, String distinct) {
        this.currentTime = currentTime;
        this.weather = weather;
        this.rainFall = rainFall;
        this.province = province;
        this.city = city;
        this.distinct = distinct;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getWeather() {
        return weather;
    }

    public int getRainFall() {
        return rainFall;
    }

    public int getCourierCnt() {
        return courierCnt;
    }

    public int getOrderCnt() {
        return orderCnt;
    }

    public void setOrderCnt(int orderCnt) {
        this.orderCnt = orderCnt;
        if(this.orderCnt > 0 && this.courierCnt > 0){
            ComputeSupplyDemandRatio();
        }
    }

    public void setCourierCnt(int courierCnt) {
        this.courierCnt = courierCnt;
        if(this.orderCnt > 0 && this.courierCnt > 0){
            ComputeSupplyDemandRatio();
        }
    }

    public double getSupplyDemandRatio() {
        return supplyemandRatio;
    }

    public void ComputeSupplyDemandRatio(){
        this.supplyemandRatio = (double) this.orderCnt/this.courierCnt;
    }
}
