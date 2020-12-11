package Visualization;


import Map.SimulationMap;

import javax.swing.*;
import java.awt.*;

public class StatistickPanel extends JPanel {


    public SimulationMap map;
    private JList stats = new JList();

    public StatistickPanel(SimulationMap map){
        this.map = map;
        setBounds(500,0,300,500);
        updateStats();
        setVisible(true);
        add(this.stats);


    }

    public void updateStats(){
        DefaultListModel<String> statsList = new DefaultListModel<>();
        statsList.addElement("Day: " + this.map.getDays());
        statsList.addElement("Number of living animals: " + this.map.getNumberOfAnimals());
        statsList.addElement("Number of grasses on the map: " + this.map.getNumberOfGrasses());
        statsList.addElement("Average energy: " + (double)Math.round(this.map.getAverageEnergy() * 10000)/10000);
        statsList.addElement("Most popular gene: " + this.map.getDominantGenotype());
        statsList.addElement("Average number of childs: " + (double)Math.round(this.map.getAverageChildCount() * 10000)/10000);
        statsList.addElement("Average days lived by animal: " + (double)Math.round(this.map.getAverageDaysLivedByAnimal() * 10000)/10000);
        this.stats.setModel(statsList);
    }
}
