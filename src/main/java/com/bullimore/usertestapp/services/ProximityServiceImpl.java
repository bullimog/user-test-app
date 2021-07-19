package com.bullimore.usertestapp.services;

import org.springframework.stereotype.Component;

@Component
public class ProximityServiceImpl implements ProximityService{
    static int MILE_RADIANS = 3936;

    @Override
    public Float lonLatDifference(Double latA, Double lonA, Double latB, Double lonB) {
        double lonAr = Math.toRadians(lonA);
        double latAr = Math.toRadians(latA);
        double lonBr = Math.toRadians(lonB);
        double latBr = Math.toRadians(latB);

        double lonDiff = lonBr - lonAr;
        double latDiff = latBr - latAr;
        double difference = Math.pow(Math.sin(latDiff/2),2)+
                Math.cos(latAr)*Math.cos(latBr)*Math.pow(Math.sin(lonDiff/2),2);

        double miles = MILE_RADIANS * (2 * Math.asin(Math.sqrt(difference)));
        return (float) miles;
    }
}
