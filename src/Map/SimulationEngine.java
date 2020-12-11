package Map;

import ObjectsOnMap.Animal;
import ObjectsOnMap.Vector2d;

import java.util.List;
import java.util.Map;

public class SimulationEngine {
    public SimulationMap map;

    public SimulationEngine(IWorldMap map){
        this.map = (SimulationMap)map;
    }

    public void run(){
            map.removeDeadAnimals();
            map.moveAllAnimals();
            map.tryEatGrasses();
            map.copulateAll();
            map.placeTwoGrasses();
            map.increaseDay();
            Map<Vector2d, List<Animal>> tmp = map.tmp();
            for (Map.Entry<Vector2d, List<Animal>> entry : tmp.entrySet()) {
                System.out.println(entry);
            }

            System.out.println((map.jungleFreeSpaces()));
            System.out.println(map.steppeFreeSpaces());
            System.out.println(map.getGrassesAsList());
            System.out.println("--------");
    }
}
