package main.java.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SegmentedRoadView implements Constants, ActionListener {

    private JFrame frame = null;
    private JPanel mainPanel = null;
    private JButton startButton = null;
    private JButton stopButton = null;
    private MyPanel panel = null;

    public SegmentedRoadView() {
        initGui();
    }


    private void initGui() {
        frame = new JFrame();
        frame.setTitle(AppName);
        frame.setResizable(false);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(getNamePanel(), BorderLayout.SOUTH);
        panel = new MyPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public JPanel getNamePanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setBackground(Color.black);
        startButton = new JButton(startButtonTile);
        mainPanel.add(startButton);
        startButton.addActionListener(this);
        startButton.setActionCommand(startButtonTile);
        return mainPanel;
    }


    public static void main(String[] args) {
        Car.createVehicleInstances();
        SegmentedRoadView myApp = new SegmentedRoadView();
    }




    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand() == startButtonTile) {
            panel.initTimers();
            panel.startTimer();
        }
    }
}
