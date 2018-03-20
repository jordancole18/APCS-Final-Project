package me.jordan.zombiegame.utils;

public class Utils {

	public static double calculateAngle(double x1, double y1, double x2, double y2){
	    double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
	    angle = angle + Math.ceil( -angle / 360 ) * 360;
	    return angle;
	}
	
}
