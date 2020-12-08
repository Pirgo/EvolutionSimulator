package Map;

import ObjectsOnMap.Animal;
import ObjectsOnMap.Vector2d;

import java.util.Map;

public interface IWorldMap {
    int getWidth();

    int getHeight();

    Vector2d getMapLowerLeft();

    double getMoveEnergy();


}
