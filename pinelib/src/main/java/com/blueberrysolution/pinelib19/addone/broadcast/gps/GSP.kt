package com.blueberrysolution.pinelib19.addone.broadcast.gps

object GPS{
    fun GetDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Long{
        var radLat1 = lat1 *  Math.PI/ 180.0;   //PI()圆周率
        var radLat2 = lat2 * Math.PI / 180.0;
        var a = radLat1 - radLat2;
        var b = (lng1 * Math.PI / 180.0) - (lng2 * Math.PI / 180.0);
        var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2), 2.0) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b/2), 2.0)));
        s = s * 6378.137;


        return Math.round(s * 1000);
    }
}