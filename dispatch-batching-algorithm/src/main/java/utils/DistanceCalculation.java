package utils;

import java.math.*;

public class DistanceCalculation {

    private DistanceCalculation(){
    }

    public static double geoCalculation(double lon1,double lat1, double lon2, double lat2){
        return Math.sqrt( lon2 * lon2 + lat2 * lat2 - lon1 * lon1 - lat1 * lat1);
    }

    public static double geoCalculation(double lon1,double lat1, double lon2, double lat2, double lon3, double lat3){

        double val1 = Math.sqrt(Math.abs( lon2 * lon2 + lat2 * lat2 - lon1 * lon1 - lat1 * lat1)) + Math.sqrt(Math.abs( lon3 * lon3 + lat3 * lat3 - lon2 * lon2 - lat2 * lat2));
        double val2 = Math.sqrt(Math.abs( lon3 * lon3 + lat3 * lat3 - lon1 * lon1 - lat1 * lat1)) + Math.sqrt(Math.abs(lon2 * lon2 + lat2 * lat2 - lon3 * lon3 - lat3 * lat3));
        return val1 > val2 ? val1: val2;

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

 }
