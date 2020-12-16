package Map;

//repair done

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

            //Used for testing before GUI

//            Map<Vector2d, List<Animal>> tmp = map.getAnimalMap();
//            for (Map.Entry<Vector2d, List<Animal>> entry : tmp.entrySet()) {
//                System.out.println(entry);
//            }
//
//            System.out.println((map.jungleFreeSpaces()));
//            System.out.println(map.steppeFreeSpaces());
//            System.out.println(map.getGrassesAsList());
//            System.out.println("--------");
    }
}
