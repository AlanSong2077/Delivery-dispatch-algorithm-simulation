package utils;
import org.apache.commons.lang3.tuple.Pair;
import structure.GeoDetail;
import structure.GeoDetailList;

import java.lang.reflect.Array;
import java.math.*;
import java.util.ArrayList;
import java.util.List;

public class DistanceCalculation {

    private DistanceCalculation(){
    }

    public static double geoCalculation(double lon1,double lat1, double lon2, double lat2){
        // 使用平面坐标距离；经纬度输入时仅作为路径比较的近似值。
        return Math.hypot(lon2 - lon1, lat2 - lat1);
    }


    // 这里也需要同步做改造 都使用Planner 计算
    public static Pair<GeoDetailList,Double> geoCalculation(GeoDetail geoDetail1, GeoDetail geoDetail2, GeoDetail geoDetail3){

        double lon1 = geoDetail1.getLongitude();
        double lat1 = geoDetail1.getLatitude();
        double lon2 = geoDetail2.getLongitude();
        double lat2 = geoDetail2.getLatitude();
        double lon3 = geoDetail3.getLongitude();
        double lat3 = geoDetail3.getLatitude();

        double val1 = Math.sqrt(Math.abs( lon2 * lon2 + lat2 * lat2 - lon1 * lon1 - lat1 * lat1)) + Math.sqrt(Math.abs( lon3 * lon3 + lat3 * lat3 - lon2 * lon2 - lat2 * lat2));
        double val2 = Math.sqrt(Math.abs( lon3 * lon3 + lat3 * lat3 - lon1 * lon1 - lat1 * lat1)) + Math.sqrt(Math.abs(lon2 * lon2 + lat2 * lat2 - lon3 * lon3 - lat3 * lat3));
        GeoDetailList geoDetails = new GeoDetailList();
        if(val1 > val2){
            geoDetails.addGeoDetails(geoDetail1);
            geoDetails.addGeoDetails(geoDetail2);
            geoDetails.addGeoDetails(geoDetail3);
            return Pair.of(geoDetails,val2);
        }
        else{
            geoDetails.addGeoDetails(geoDetail1);
            geoDetails.addGeoDetails(geoDetail3);
            geoDetails.addGeoDetails(geoDetail2);
            return Pair.of(geoDetails,val2);

        }
    }

    public static int timeCalculation(int millisecond1, int millisecond2){
        return millisecond1 - millisecond2;
    }

    public static double angleCalculation(double ax, double ay,
                                  double bx, double by,
                                  double cx, double cy) {

        // 向量 AB = B - A
        double x1 = bx - ax;
        double y1 = by - ay;

        // 向量 AC = C - A
        double x2 = cx - ax;
        double y2 = cy - ay;

        double dot = x1 * x2 + y1 * y2;
        double lenA = Math.sqrt(x1 * x1 + y1 * y1);
        double lenB = Math.sqrt(x2 * x2 + y2 * y2);

        if (lenA == 0 || lenB == 0) {
            return 0; // 有重合点，夹角无意义
        }

        double cos = dot / (lenA * lenB);
        cos = Math.max(-1.0, Math.min(1.0, cos));

        return Math.toDegrees(Math.acos(cos));
    }


    // todo 单独改造 做一个Planner 这里不好函数方法来做
    public static void DeliveryPointPlan(GeoDetailList geoDetailList, GeoDetail startPointGeoDetail, GeoDetail endPointGeoDetail){

        // geoDetailList 包含了配送路径的顺序 所以要维护他 骑手中间配送过程中 可能会出现有终点没起点的情况 这种情况只会发生的最近的那个点。

        ArrayList<GeoDetail> geoDetails = geoDetailList.getGeoDetails();

        int pointCnt = geoDetails.size();

        if(pointCnt == 1){
            // 直接插入即可
            geoDetails.add(startPointGeoDetail);
            geoDetails.add(endPointGeoDetail);
        }
        else if(pointCnt % 2 == 0){
            // geoCalculation()
            // 取完了 在送 分别计算送取送和取送取的距离


        }
        else{
            
            // 上一单还没取
        }


        // 首节点一定是骑手当前位置

        // 一定订单关联起点和终点 每次一定要插入2个点 并且保持插入顺序  start < end;


        //根据距离判断插入节点位置

        // 1. 取取送送

        // 2. 取送取送

        // 3. 送取送

        // 4. 取送送
    }

    // 这里需要维护每个骑手的配送路径

 }
