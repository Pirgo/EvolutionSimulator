package Map;


import ObjectsOnMap.Animal;
import ObjectsOnMap.Genes;
import ObjectsOnMap.Vector2d;

import java.util.Arrays;


public class tmp {
    public static void main(String[] args){
        Genes gene = new Genes(32, 8);
        Genes gene2 = new Genes(32, 8);
        System.out.println(Arrays.toString(gene.getGene()));
        System.out.println(Arrays.toString(gene2.getGene()));
        Genes child = new Genes(gene, gene2);
        System.out.println(Arrays.toString(child.getGene()));
        SimulationMap map = new SimulationMap(10,10,0.5,40, 3, 1);


        Animal komodo = new Animal(new Vector2d(2,2), 40, map, map, gene);
        Animal komodo2 = new Animal(new Vector2d(0,2), 40, map, map, new Genes(32,8));
        Animal komodo3 = new Animal(new Vector2d(2,1), 40, map, map, new Genes(32,8));
        Animal komodo4 = new Animal(new Vector2d(1,2), 40, map, map, new Genes(32,8));

        map.placeAnimalAtFreePosition(komodo);
        map.placeAnimalAtFreePosition(komodo2);
        map.placeAnimalAtFreePosition(komodo3);
        map.placeAnimalAtFreePosition(komodo4);

//        Map<Vector2d, List<Animal>> tmp = map.tmp();
//        for (Map.Entry<Vector2d, List<Animal>> entry : tmp.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue().toString());

//        }
        SimulationEngine engine = new SimulationEngine(map);
        engine.run();
    }
}
