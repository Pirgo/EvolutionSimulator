package Visualization;

import Map.SimulationEngine;
import Map.SimulationMap;
import ObjectsOnMap.Animal;
import ObjectsOnMap.Genes;
import ObjectsOnMap.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;


public class Frame extends JFrame implements ActionListener {

    public SimulationMap map;
    public SimulationEngine engine;
    public MapPanel mapPanel;
    public StatistickPanel statPanel;
    public Timer timer;

    public Frame(SimulationEngine engine){
        super("EvolutionSimulator");
        this.engine = engine;
        this.map = engine.map;
        this.timer = new Timer(100, this::actionPerformed);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setVisible(true);
        this.mapPanel = new MapPanel(this.map, this.getSize());
        add(mapPanel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this::startTimer);
        startButton.setBounds(10,525,100,40);
        mapPanel.add(startButton);
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(this::stopTimer);
        stopButton.setBounds(110,525,100,40);
        mapPanel.add(stopButton);

        





    }



    @Override
    public void actionPerformed(ActionEvent e) {
        this.engine.run();
        this.mapPanel.repaint();


    }

    public void stopTimer(ActionEvent e){
        this.timer.stop();
    }

    public void startTimer(ActionEvent e){
        this.timer.start();
    }


}
