package ObjectsOnMap;

import Map.IPositionChangeObserver;
import Map.IWorldMap;
import Map.MapDirection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Animal implements IMapElement {
    private Vector2d position;
    private final Genes gene;
    private int dominantGene;
    private double energy;
    private MapDirection orientation;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private IWorldMap map;
    private int numberOfchilds;
    public int livedDays;
    public int dayOfDeath;


    public Animal(Vector2d position, double energy, IWorldMap map, IPositionChangeObserver observer, Genes gene){
        this.position = position;
        this.energy = energy;
        this.gene = gene;
        this.dominantGene = this.gene.getDominantGene();
        this.orientation = MapDirection.NORTH.random();
        this.map = map;
        this.addObserver(observer);
        this.numberOfchilds = 0;
        this.livedDays = 0;
        this.dayOfDeath = 0;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }

    public int getDominantGene(){
        return this.dominantGene;
    }

    //rotates animal
    public void rotate(){
        for(int i=0; i < this.gene.returnRandomGene(); i++){
            this.orientation = this.orientation.next();
        }
    }

    //moves animal if not dead
    public void move(){

        Vector2d old = new Vector2d(this.position.x, this.position.y);
        this.position = this.map.wrapPosition(new Vector2d(this.position.x + this.orientation.toUnitVector().x, this.position.y + this.orientation.toUnitVector().y));
        this.rotate();
        this.positionChanged(this, old, this.position);
    }

    public void changeEnergy(double energyValue){
        this.energy += energyValue;
    }


    //checks if animal would die while moving, assuming that it cant move if its energy drops below zero, (it will die while moving)
    public boolean isDead(){
        return this.energy - map.getMoveEnergy() < 0;
    }

    public void copulate(){
        this.energy -= this.energy * 0.25;
        this.numberOfchilds += 1;
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer: this.observers){
            observer.positionChanged(animal, oldPosition, newPosition);
        }
    }


    @Override
    public boolean equals(Object other){

        if(this == other) return true;
        return false;
    }

    @Override
    public String toString(){
        return ("Animal" + this.energy + this.position + this.orientation);
    }

    public double getEnergy(){
        return this.energy;
    }

    public Genes getGene(){
        return this.gene;
    }

    public int getNumberOfChilds(){
        return this.numberOfchilds;
    }

    //todo need to change
    public Color animalColor(){
        double startEnergy = map.getStartEnergy();
        if(this.energy < 0.1 * startEnergy ) return new Color(255, 0, 0);
        if(this.energy < 0.2 * startEnergy ) return new Color(206, 25, 25);
        if(this.energy < 0.5 * startEnergy ) return new Color(232, 83, 83);
        if(this.energy < 0.8 * startEnergy ) return new Color(196, 87, 87);
        if(this.energy < startEnergy ) return new Color(119, 255, 0);
        if(this.energy < 2 * startEnergy ) return new Color(20, 146, 11);
        if(this.energy < 4 * startEnergy ) return new Color(21, 71, 10);
        if(this.energy < 8 * startEnergy ) return new Color(11, 52, 9);
        return new Color(11, 52, 9);
    }
}
