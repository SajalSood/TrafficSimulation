package main.java.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyPanel extends JPanel implements Constants, Observer, ActionListener {

    private int counter = 0;
    private CarRules rule = new CarRules();
    private Graphics2D g2d;
    private ArrayList<Timer> timers = new ArrayList<Timer>(); //List to initiate Timers.
    private Color color; //store the color of the vehicle.
    private String status = statusMsg; //To show the message on the top panel based on the status number.
    private CarSimulation sim = null;


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
            if(sim.getCtr() == 1) {
                initTimers();
            }
        }
    }


    private void initTimers(){
        for(int i=1; i<=numberOfVehicles; i++) {
            Timer tm = new Timer(80, this);
            tm.setActionCommand(Integer.toString(i));
            timers.add(tm);
            tm.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // string storing the action command. Passed to switch statement
        String str = actionEvent.getActionCommand();

        switch (str){
            case "1":
                lane1.get(0).setVehLocationX(lane1.get(0).getVehLocationX() + lane1.get(0).getVehSpeed());
                repaint();
                break;

            case "2":
                if((lane2.get(0).getVehLocationX()+vehicleWidth+lane2.get(0).getStopDistance()) < 950) {
                    lane2.get(0).setVehLocationX(lane2.get(0).getVehLocationX() + lane2.get(0).getVehSpeed());
                }
                else{
                    lane2.get(0).setVehSpeed(0);
                }
                repaint();
                break;

            case "3":
                if(lane1.get(1).getVehLocationX()+vehicleWidth < (lane1.get(0).getVehLocationX() - lane1.get(0).getStopDistance())) {
                    if(lane1.get(1).getVehSpeed() == 0){
                        lane1.get(1).setVehSpeed(8);
                    }
                    lane1.get(1).setVehLocationX(lane1.get(1).getVehLocationX() + lane1.get(1).getVehSpeed());
                }
                else{
                    lane1.get(1).setVehSpeed(0);
                }
                repaint();
                break;

            case "4":
                if(lane2.get(1).getVehLocationX()+vehicleWidth < (lane2.get(0).getVehLocationX() - lane2.get(0).getStopDistance())) {
                    if(lane2.get(1).getVehSpeed() == 0){
                        lane2.get(1).setVehSpeed(10);
                    }
                    lane2.get(1).setVehLocationX(lane2.get(1).getVehLocationX() + lane2.get(1).getVehSpeed());
                }
                else{
                    lane2.get(1).setVehSpeed(0);
                }
                repaint();
                break;

            case "5":
                if(lane1.get(2).getVehLocationX()+vehicleWidth < (lane1.get(1).getVehLocationX() - lane1.get(1).getStopDistance())) {
                    if(lane1.get(2).getVehSpeed() == 0){
                        lane1.get(2).setVehSpeed(8);
                    }
                    lane1.get(2).setVehLocationX(lane1.get(2).getVehLocationX() + lane1.get(2).getVehSpeed());
                }
                else{
                    lane1.get(2).setVehSpeed(0);
                }
                repaint();
                break;

            case "6":
                if(lane2.get(2).getVehLocationX()+vehicleWidth < (lane2.get(1).getVehLocationX() - lane2.get(1).getStopDistance())) {
                    if(lane2.get(2).getVehSpeed() == 0){
                        lane2.get(2).setVehSpeed(10);
                    }
                    lane2.get(2).setVehLocationX(lane2.get(2).getVehLocationX() + lane2.get(2).getVehSpeed());
                }
                else{
                    lane2.get(2).setVehSpeed(0);
                }
                repaint();

                break;

            case "7":
                if(lane1.get(3).getVehLocationX()+vehicleWidth < (lane1.get(2).getVehLocationX() - lane1.get(2).getStopDistance())) {
                    if(lane1.get(3).getVehSpeed() == 0){
                        lane1.get(3).setVehSpeed(8);
                    }
                    lane1.get(3).setVehLocationX(lane1.get(3).getVehLocationX() + lane1.get(3).getVehSpeed());
                }
                else{
                    lane1.get(3).setVehSpeed(0);
                }
                repaint();
                break;

            case "8":
                if(lane2.get(3).getVehLocationX()+vehicleWidth < (lane2.get(2).getVehLocationX() - lane2.get(2).getStopDistance())) {
                    if(lane2.get(3).getVehSpeed() == 0){
                        lane2.get(3).setVehSpeed(10);
                    }
                    lane2.get(3).setVehLocationX(lane2.get(3).getVehLocationX() + lane2.get(3).getVehSpeed());
                }
                else{
                    lane2.get(3).setVehSpeed(0);
                }
                repaint();
                break;
        }
    }

}
