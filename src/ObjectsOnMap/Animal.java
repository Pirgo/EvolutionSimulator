package ObjectsOnMap;

import Map.IPositionChangeObserver;
import Map.IWorldMap;
import Map.MapDirection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Animal implements IMapElement {
    private Vector2d position;
    private Genes gene;
    private double energy;
    private MapDirection orientation;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private IWorldMap map;

    public Animal(Vector2d position, double energy, IWorldMap map, IPositionChangeObserver observer, Genes gene){
        this.position = position;
        this.energy = energy;
        this.gene = gene;
        this.orientation = MapDirection.NORTH.random();
        this.map = map;
        this.addObserver(observer);
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }

    //rotates animal
    public void rotate(){
        for(int i=0; i < this.gene.returnRandomGene(); i++){
            this.orientation = this.orientation.next();
        }
    }

    //moves animal if not dead
    public void move(){
//        if(this.isDead()){
//            System.out.println("YYYYYYYYYYYYYYYYYY");
//            return;
//        }
        int tmpx;
        int tmpy;

        if(this.position.x + this.orientation.toUnitVector().x < map.getMapLowerLeft().x){
            tmpx = (map.getWidth() - Math.abs((this.position.x + this.orientation.toUnitVector().x)%map.getWidth())) % map.getWidth();
        }
        else{
            tmpx = Math.abs((this.position.x + this.orientation.toUnitVector().x)%map.getWidth());
        }

        if(this.position.y + this.orientation.toUnitVector().y < map.getMapLowerLeft().y){
            tmpy = (map.getHeight() - Math.abs((this.position.y + this.orientation.toUnitVector().y)%map.getHeight())) % map.getHeight();
        }
        else{
            tmpy = Math.abs((this.position.y + this.orientation.toUnitVector().y)%map.getHeight());
        }

        Vector2d old = new Vector2d(this.position.x, this.position.y);
        this.position = new Vector2d(tmpx, tmpy);
        this.rotate();
        this.positionChanged(this, old, this.position);
    }

    public void changeEnergy(double energyValue){
        this.energy += energyValue;
    }

    public boolean isDead(){
        return this.energy - map.getMoveEnergy() < 0;
    }

    public void copulate(){
        this.energy -= this.energy * 0.25;
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
}
