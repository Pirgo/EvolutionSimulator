package ObjectsOnMap;

import java.util.Comparator;

public class AnimalSorter implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        if(o1.getEnergy() - o2.getEnergy() < 0){
            return 1;
        }
        if(o1.getEnergy() - o2.getEnergy() > 0){
            return -1;
        }
        return 0;
    }
}