package main.java.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements  Constants, ActionListener {

    private Graphics2D g2d;
    private Dimension size;
    private ArrayList<Timer> timers = new ArrayList<Timer>();


    public void paint(Graphics g) {

        g2d = (Graphics2D) g;
        size = getSize();

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, size.width, size.height);

        Stroke dashedLane = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);

        g2d.setColor(Color.white);
        g2d.setStroke(dashedLane);
        g2d.drawLine(0, size.height / 2, size.width, size.height / 2);

        startSimulation();

    }

    public void startSimulation() {

        for (int i = 0; i < lane1.size(); i++) {
            g2d.setColor(Color.orange);
            g2d.fillRoundRect(lane1.get(i).getVehLocation(), firstLaneY, Integer.parseInt(lane1.get(i).getVehWidth()),
                    Integer.parseInt(lane1.get(i).getVehHeight()), roundRectHW, roundRectHW);
        }
        for (int i = 0; i < lane2.size(); i++) {
            g2d.setColor(Color.orange);
            g2d.fillRoundRect(lane2.get(i).getVehLocation(), secondLaneY, Integer.parseInt(lane2.get(i).getVehWidth()),
                    Integer.parseInt(lane2.get(i).getVehHeight()), roundRectHW, roundRectHW);
        }

    }

    public void initTimers() {
        for (int i = 1; i <= 8; i++) {
            if (i == 1 ){
                Timer tm = new Timer(10, this);
                tm.setActionCommand(Integer.toString(i));
                tm.setDelay(10);
                timers.add(tm);
            }
            else if(i == 2){
                Timer tm = new Timer(20, this);
                tm.setActionCommand(Integer.toString(i));
                tm.setDelay(10);
                timers.add(tm);
            }
            else if (i == 3 ){
                Timer tm = new Timer(30, this);
                tm.setActionCommand(Integer.toString(i));
                tm.setDelay(10);
                timers.add(tm);
            }
            else if (i == 4) {
                Timer tm = new Timer(20, this);
                tm.setActionCommand(Integer.toString(i));
                tm.setDelay(20);
                timers.add(tm);
            }
        }
    }

    public void startTimer() {
        for (Timer timer : timers) {
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("1")) {
            int x = lane1.get(0).getVehLocation();
            int velX = 3;
            if (x > 1300) {
                x = 0;
                lane1.get(0).setVehLocation(0);
            }
            x = x + velX;
            lane1.get(0).setVehLocation(x);
            repaint();
        } else if (actionEvent.getActionCommand().equals("2")) {
            int x = lane1.get(1).getVehLocation();
            int velX = 1;
            if (x > 1300) {
                x = 0;
                lane1.get(1).setVehLocation(0);
            }
            x = x + velX;
            lane1.get(1).setVehLocation(x);
            repaint();
        } else if (actionEvent.getActionCommand().equals("3")) {
            int x = lane2.get(0).getVehLocation();
            int velX = 2;
            if (x > 1300) {
                x = 0;
                lane2.get(0).setVehLocation(0);
            }
            x = x + velX;
            lane2.get(0).setVehLocation(x);
            repaint();
        } else if (actionEvent.getActionCommand().equals("4")) {
            int x = lane2.get(1).getVehLocation();
            int velX = 1;
            if (x > 1300) {
                x = 0;
                lane2.get(1).setVehLocation(0);
            }
            x = x + velX;
            lane2.get(1).setVehLocation(x);
            repaint();
        }
    }
}
