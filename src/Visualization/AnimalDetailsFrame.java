package Visualization;

import Map.SimulationMap;
import ObjectsOnMap.Animal;
import ObjectsOnMap.AnimalSorter;
import ObjectsOnMap.Vector2d;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// todo check if this causes problems
public class AnimalDetailsFrame extends JFrame implements ActionListener {

    private Animal animal;
    private JSpinner whichEra;
    private JList animalDetails = new JList();

    public AnimalDetailsFrame(Vector2d animalPosition, SimulationMap map, Frame frame){
        super("AnimalDetails");
        setSize(510,200);
        setVisible(true);
        setLocation(frame.getX(), frame.getY()+frame.getHeight());
        List<Animal> animalList = map.getAnimalMap().get(animalPosition);
        animalList.sort(new AnimalSorter());
        this.animal = animalList.get(0);
        SpinnerModel whichEra = new SpinnerNumberModel(this.animal.dayOfBirth ,this.animal.dayOfBirth,null,1);
        JSpinner whichEraSpinner = new JSpinner(whichEra);
        whichEraSpinner.setBounds(220,100,80,30);
        JButton apply = new JButton("Apply");
        apply.addActionListener(this::actionPerformed);
        apply.setBounds(310,100,80,30);
        JLabel whichEraLabel = new JLabel("Pick a era to check nmb of childs");
        whichEraLabel.setBounds(220, 70, 170,30);
        this.animalDetails.add(whichEraLabel);
        this.animalDetails.add(apply);
        this.whichEra = whichEraSpinner;
        this.animalDetails.add(whichEraSpinner);
        updateAnimalDetails();
        add(this.animalDetails);


    }

    public void updateAnimalDetails(){
        DefaultListModel<String> statsList = new DefaultListModel<>();
        statsList.addElement("Position: " + this.animal.getPosition());
        statsList.addElement("Gene: " + Arrays.toString(this.animal.getGene().getGene()));
        statsList.addElement("Dominant gene: " + (this.animal.getDominantGene()>0 ? this.animal.getDominantGene() : "None"));
        statsList.addElement("Number of its childs in " + this.whichEra.getValue() + " era is " + this.animal.getNumberOfChildsInEra((int)this.whichEra.getValue()));
        statsList.addElement("Energy: " +this.animal.getEnergy());
        if(this.animal.dayOfDeath!= 0){
            statsList.addElement("Day of death: " + this.animal.dayOfDeath);
        }
        this.animalDetails.setModel(statsList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateAnimalDetails();
    }
}
