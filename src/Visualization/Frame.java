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
    public int animalsAtStart;

    public Frame(SimulationEngine engine, int animalsAtStart){
        super("EvolutionSimulator");
        this.engine = engine;
        this.map = engine.map;
        this.timer = new Timer(100, this::actionPerformed);
        this.animalsAtStart = animalsAtStart;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setVisible(true);
        this.mapPanel = new MapPanel(this.map, this.getSize());
        add(mapPanel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this::startTimer);
        mapPanel.add(startButton);
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(this::stopTimer);
        mapPanel.add(stopButton);

        

        this.startSimulation();



    }
    //infinite if number of start animals are higher than number of fields
    public void startSimulation(){
        int i = 0;
        while (i < this.animalsAtStart){
            Animal animal = new Animal(new Vector2d(this.map), this.map.getStartEnergy(), this.map, this.map, new Genes(32,8));
            if(this.map.placeAnimalAtFreePosition(animal)){
                i++;
            }
        }

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
