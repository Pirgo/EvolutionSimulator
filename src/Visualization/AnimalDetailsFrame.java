package Visualization;

import Map.SimulationMap;
import ObjectsOnMap.Animal;
import ObjectsOnMap.AnimalSorter;
import ObjectsOnMap.Vector2d;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// todo check if this causes problems
public class AnimalDetailsFrame extends JFrame {

    Animal animal;
    private JList animalDetails = new JList();

    public AnimalDetailsFrame(Vector2d animalPosition, SimulationMap map, Frame frame){
        super("AnimalDetails");
        setSize(510,200);
        setVisible(true);
        setLocation(frame.getX(), frame.getY()+frame.getHeight());
        List<Animal> animalList = map.getAnimalMap().get(animalPosition);
        animalList.sort(new AnimalSorter());
        this.animal = animalList.get(0);
        updateAnimalDetails();
        add(this.animalDetails);

    }

    public void updateAnimalDetails(){
        DefaultListModel<String> statsList = new DefaultListModel<>();
        statsList.addElement("Position: " + this.animal.getPosition());
        statsList.addElement("Gene: " + Arrays.toString(this.animal.getGene().getGene()));
        statsList.addElement("Number of its childs: " + this.animal.getNumberOfchilds());
        statsList.addElement("Energy: " +this.animal.getEnergy());
        if(this.animal.dayOfDeath!= 0){
            statsList.addElement("Day of death: " + this.animal.dayOfDeath);
        }
        this.animalDetails.setModel(statsList);
    }
}
