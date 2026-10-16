package structure;

import java.util.ArrayList;

public class GeoDetailList {
    // 需要按顺序访问 -> 每次都吐出下一个配送点 当订单来临后 也需要决定插入点

    private ArrayList<GeoDetail> geoDetails;

    // geoDetails 只允许获取 不允许直接修改整个对象。增加对象的增删改查办法；允许空参构造然后去追加

    // 如果不用这个类实现 直接用List<GeoDetail>的设计可能更好？

    public GeoDetailList(ArrayList<GeoDetail> geoDetails) {
        this.geoDetails = geoDetails;
    }

    public GeoDetailList() {
    }

    public ArrayList<GeoDetail> getGeoDetails() {
        return geoDetails;
    }

    public GeoDetail getGeoDetail(int index) {
        return geoDetails.get(index);
    }

    public void addGeoDetails(GeoDetail geoDetail){
        this.geoDetails.add(geoDetail);
    }

    public void addGeoDetails(int index,GeoDetail geoDetail){
        this.geoDetails.add(index,geoDetail);
    }

    public void deleteGeoDetails(int index){
        this.geoDetails.remove(index);
    }

    public void deleteGeoDetails(GeoDetail geoDetail){
        this.geoDetails.remove(geoDetail);
    }
 }
