package Map;

import ObjectsOnMap.Vector2d;


import java.util.ArrayList;
import java.util.List;

public class SimulationStartData {
    public int width;
    public int height;
    public int energyStart;
    public int energyMove;
    public int energyGrass;
    public double jungleRatio;
    public int numberOfMaps;
    public int numberOfAnimal;
    public List<Vector2d> startingPositions = new ArrayList<>();

    public SimulationStartData(int width,
            int height,
            int energyStart,
            int energyMove,
            int energyGrass,
            double jungleRatio,
            int numberOfMaps,
            int numberOfAnimal){

        this.width = width;
        this.height = height;
        this.energyStart = energyStart;
        this.energyMove = energyMove;
        this.energyGrass = energyGrass;
        this.jungleRatio = jungleRatio;
        this.numberOfMaps = numberOfMaps;
        this.numberOfAnimal = numberOfAnimal;
        int i=0;
        while (i<numberOfAnimal){
            Vector2d position = new Vector2d(this);
                    if(!this.startingPositions.contains(position)){
                        this.startingPositions.add(position);
                        i++;
                    }
        }


    }


}
