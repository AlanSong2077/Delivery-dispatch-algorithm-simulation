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
 }
