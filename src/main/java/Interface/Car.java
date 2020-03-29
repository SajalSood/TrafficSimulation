package main.java.Interface;

import java.io.*;
import java.util.ArrayList;

public class Car implements Constants{

    private int vehLocation;
    private String vehHeight;
    private String vehWidth;
    private String stopDistance;
    private String sensingDistance;
    private String laneNumber;

    Car(int vehLocation, String vehHeight, String vehWidth,
        String stopDistance, String sensingDistance, String laneNumber) {
        this.vehLocation = vehLocation;
        this.vehHeight = vehHeight;
        this.vehWidth = vehWidth;
        this.stopDistance = stopDistance;
        this.sensingDistance = sensingDistance;
        this.laneNumber = laneNumber;
    }


    public static void createVehicleInstances() {
        for (int i = 0; i < numberOfVehicles; i++) {
            if (i < 2) {
                Car vehicle = new Car( 0, Integer.toString(vehicleHeight),
                        Integer.toString(vehicleWidth), "", "", "First");
                lane1.add(vehicle);
            } else {
                Car vehicle = new Car( 0, Integer.toString(vehicleHeight),
                        Integer.toString(vehicleWidth), "", "", "Second");
                lane2.add(vehicle);
            }
        }
    }

    //getters and setters

    public int getVehLocation() {
        return vehLocation;
    }

    public void setVehLocation(int vehLocation) {
        this.vehLocation = vehLocation;
    }

    public String getVehHeight() {
        return vehHeight;
    }

    public void setVehHeight(String vehHeight) {
        this.vehHeight = vehHeight;
    }

    public String getVehWidth() {
        return vehWidth;
    }

    public void setVehWidth(String vehWidth) {
        this.vehWidth = vehWidth;
    }

    public String getStopDistance() {
        return stopDistance;
    }

    public void setStopDistance(String stopDistance) {
        this.stopDistance = stopDistance;
    }

    public String getSensingDistance() {
        return sensingDistance;
    }

    public String getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(String laneNumber) {
        this.laneNumber = laneNumber;
    }

    public void setSensingDistance(String sensingDistance) {
        this.sensingDistance = sensingDistance;
    }
}


