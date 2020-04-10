package main.java.Interface;

public class Car implements Constants{

    private int vehSpeed;
    private int vehLocationX;
    private int vehLocationY;
    private int vehHeight;
    private int vehWidth;
    private String laneNumber;
    private boolean rotate;
    private int stopDistance;


    public Car(int vehSpeed, int vehLocationX, int vehLocationY, int vehHeight, int vehWidth, String laneNumber, boolean rotate, int stopDistance){
        this.vehSpeed = vehSpeed;
        this.vehLocationX = vehLocationX;
        this.vehLocationY = vehLocationY;
        this.vehHeight = vehHeight;
        this.vehWidth = vehWidth;
        this.laneNumber = laneNumber;
        this.rotate = rotate;
        this.stopDistance =  stopDistance;
    }

    /**
     * Vehicle static constructor
     * Creates instances of vehicles
     *
     */
    public static void createVehicleInstances() {
        Car vehicle = null;
        vehicle = new Car(5, 100, firstLaneY, vehicleHeight,
                vehicleWidth, "First", false, 30);
        lane1.add(vehicle);

        vehicle = new Car(4, 50, firstLaneY, vehicleHeight,
                vehicleWidth, "First", false, 30);
        lane1.add(vehicle);

        vehicle = new Car(3, 0, firstLaneY, vehicleHeight,
                vehicleWidth, "First", false, 30);
        lane1.add(vehicle);

        vehicle = new Car(2, 0, firstLaneY, vehicleHeight,
                vehicleWidth, "First", false, 30);
        lane1.add(vehicle);

        vehicle = new Car(6, 100, secondLaneY, vehicleHeight,
                vehicleWidth, "Second", false, 30);
        lane2.add(vehicle);

        vehicle = new Car(5, 50, secondLaneY, vehicleHeight,
                vehicleWidth, "Second", false, 30);
        lane2.add(vehicle);

        vehicle = new Car(4, 0, secondLaneY, vehicleHeight,
                vehicleWidth, "Second", false, 30);
        lane2.add(vehicle);

        vehicle = new Car(3, 0, secondLaneY, vehicleHeight,
                vehicleWidth, "Second", false, 30);
        lane2.add(vehicle);
    }

    //getters and setters
    public int getStopDistance() {
        return stopDistance;
    }

    public void setStopDistance(int stopDistance) {
        this.stopDistance = stopDistance;
    }

    public int getVehSpeed() {
        return vehSpeed;
    }

    public void setVehSpeed(int vehSpeed) {
        this.vehSpeed = vehSpeed;
    }

    public int getVehLocationX() {
        return vehLocationX;
    }

    public void setVehLocationX(int vehLocationX) {
        this.vehLocationX = vehLocationX;
    }

    public int getVehLocationY() {
        return vehLocationY;
    }

    public void setVehLocationY(int vehLocationY) {
        this.vehLocationY = vehLocationY;
    }

    public int getVehHeight() {
        return vehHeight;
    }

    public int getVehWidth() {
        return vehWidth;
    }

    public String getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(String laneNumber) {
        this.laneNumber = laneNumber;
    }

    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }
}
