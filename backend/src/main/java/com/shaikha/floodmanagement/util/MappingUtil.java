package com.shaikha.floodmanagement.util;

import com.shaikha.floodmanagement.drone.Drone;
import com.shaikha.floodmanagement.drone.dto.DroneInfo;
import com.shaikha.floodmanagement.location.Location;
import com.shaikha.floodmanagement.location.dto.LocationInfo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MappingUtil {
    public static final String DATE_FORMAT = "dd MMM yyyy";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm";

    public static String getNotificationTime(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        boolean isToday = now.toLocalDate().isEqual(now.toLocalDate());
        if (isToday){
            return getNotificationTimeToday(dateTime);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return dateTime.format(formatter);
    }

    public static String getReportDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return dateTime.format(formatter);
    }

    public static String getReportTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return dateTime.format(formatter);
    }

    private static String getNotificationTimeToday(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        LocalDateTime now = LocalDateTime.now();
        int hourDifference = now.getHour() - time.getHour();
        if (hourDifference > 0) return hourDifference + " hours ago";
        int minDifference = now.getMinute() - time.getMinute();
        if (minDifference > 0) return minDifference + " mins ago";
        return "now";
    }

    public static LocationInfo getLocationInfo(Location location) {
        return new LocationInfo(location.getLat(), location.getLng());
    }

    public static DroneInfo getDroneInfo(Drone drone) {
        return new DroneInfo(
                drone.getDroneId(),
                drone.getDroneName(),
                drone.getCameraId(),
                drone.getGpsModule(),
                new LocationInfo(drone.getLocation().getLat(), drone.getLocation().getLng())
        );
    }
}
