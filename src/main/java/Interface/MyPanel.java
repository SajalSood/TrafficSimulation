package main.java.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class MyPanel extends JPanel implements Constants, Observer {

    private int counter = 0;
    private CarRules rule = new CarRules();
    private Graphics2D g2d;
    private ArrayList<Timer> timers = new ArrayList<Timer>(); //List to initiate Timers.
    private Color color; //store the color of the vehicle.
    private String status = statusMsg; //To show the message on the top panel based on the status number.
    private CarSimulation sim = null;
    private int count = 1;
    private int index;


    public void paint(Graphics g) {
        g2d = (Graphics2D) g;

        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 0, frameWidth, 100);

        g2d.setColor(Color.gray);
        //road-1
        g2d.fillRect(0, 100, frameWidth-50, roadHeight);
        //road-2
        g2d.fillRect(0, 500, frameWidth-50, roadHeight);
        //road-patch
        g2d.fillRect(1250, 200, roadHeight, 300);

        g2d.setColor(Color.red);
        g2d.fillRect(1000, 160, 30, 30);
        g2d.fillRect(950, 160, 30, 30);


        Stroke dashedLane = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setColor(Color.getHSBColor(148,93,20));
        g2d.setStroke(dashedLane);
        //road-1 line
        g2d.drawLine(0, 150, frameWidth - 100, 150);
        //road-2 line
        g2d.drawLine(0, 550, frameWidth - 100, 550);
        //patch line
        g2d.drawLine(1300, 150, 1300, 550);


        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 100+roadHeight, frameWidth - 150, 300);
        g2d.fillRect(0, 500+roadHeight, frameWidth, 300);
        g2d.fillRect(1250+roadHeight, 100, frameWidth, frameHeight);


        g2d.fillRect(1100, 510, vehicleWidth, vehicleHeight );


        createVehiclesByVehicleInstances();
    }

    private void createVehiclesByVehicleInstances(){

        ArrayList<Car> allVehicles = new ArrayList<Car>();
        allVehicles.addAll(lane1);
        allVehicles.addAll(lane2);

        for(int i =0; i<allVehicles.size(); i++) {
            drawRectangle(allVehicles.get(i), Color.pink);
        }
    }

    //Method to create vehicles in the respective lanes.
    private void drawRectangle(Car vehicle, Color color) {
        Rectangle rect = new Rectangle(vehicle.getVehLocationX(), vehicle.getVehLocationY(),
                vehicle.getVehWidth(), vehicle.getVehHeight());
        g2d.setColor(color);
        g2d.fill(rect);
    }


    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof CarSimulation) {
            sim = (CarSimulation) o;
            if(sim.getCtr() == count) {
                //if(count <= 3) {
                    createInstance();
                //}
                updateLocations();
                repaint();
                count++;
            }
        }
    }

    private void updateLocations(){
        for(int i=0; i <lane1.size(); i++){
            if(i==0){
                lane1.get(i).setVehLocationX(lane1.get(i).getVehLocationX() + lane1.get(i).getVehSpeed());
                if(lane1.get(i).getVehLocationX() > frameWidth){
                    lane1.remove(lane1.get(i));
                }

            }
            else{
                if(lane1.get(i).getVehLocationX()+vehicleWidth < (lane1.get(i-1).getVehLocationX() - lane1.get(i).getStopDistance())) {
                    if(lane1.get(i).getVehSpeed() == 0){
                        lane1.get(i).setVehSpeed(8);
                    }
                    lane1.get(i).setVehLocationX(lane1.get(i).getVehLocationX() + lane1.get(i).getVehSpeed());
                }
                else{
                    lane1.get(i).setVehSpeed(0);
                }
            }
        }

        for(int i=0; i<lane2.size(); i++){
            if(i==0){
                if((lane2.get(i).getVehLocationX()+vehicleWidth+lane2.get(i).getStopDistance()) < 950) {
                    if(lane2.get(i).getVehSpeed() == 0){
                        lane2.get(i).setVehSpeed(8);
                    }
                    lane2.get(i).setVehLocationX(lane2.get(i).getVehLocationX() + lane2.get(i).getVehSpeed());
                }
                else{
                    lane2.get(i).setVehSpeed(0);
                    checkIfCarCanMerge(lane2.get(i));
                }
            }
            else{
                if(lane2.get(i).getVehLocationX()+vehicleWidth < (lane2.get(i-1).getVehLocationX() - lane2.get(i).getStopDistance())) {
                    if(lane2.get(i).getVehSpeed() == 0){
                        lane2.get(i).setVehSpeed(8);
                    }
                    lane2.get(i).setVehLocationX(lane2.get(i).getVehLocationX() + lane2.get(i).getVehSpeed());
                }
                else{
                    lane2.get(i).setVehSpeed(0);
                }
            }
        }
    }

    private void checkIfCarCanMerge(Car car){
        for(int i=0; i<lane1.size(); i++) {
            int rearSafeDistance = car.getVehLocationX() - 10;
            int frontSafeDistance = car.getVehLocationX() + vehicleWidth + 10;
            if(!carExists(rearSafeDistance, frontSafeDistance)){
                car.setLaneNumber("First");
                car.setVehLocationY(firstLaneY);
                car.setStopDistance(lane1StopDistance);
                car.setVehLocationX(rearSafeDistance+10);
                car.setVehSpeed(8);
                lane1.add(getNearCarIndex(rearSafeDistance, frontSafeDistance), car);
                lane2.remove(car);
                updateLocations();
                repaint();
            }
        }
    }


    private boolean carExists(int rearLocX, int frontLocX){
        for(int i =0 ; i<lane1.size(); i++){
            if(lane1.get(i).getVehLocationX()+vehicleWidth > rearLocX && lane1.get(i).getVehLocationX() < frontLocX)
            {
                return true;
            }
        }
        return false;
    }

    private int getNearCarIndex(int rearLocX, int frontLocX){
        int cntr = 0;
        for(int i =0; i<lane1.size(); i++){
            if(lane1.get(i).getVehLocationX()+vehicleWidth > rearLocX-lane1StopDistance && lane1.get(i).getVehLocationX() < frontLocX){
                return i;
            }
            cntr++;
        }
        if(lane2.size() == 1) {
            return cntr;
        }
        return 0;
    }

    private void createInstance(){
        Car.createVehicleInstances();
    }


}
