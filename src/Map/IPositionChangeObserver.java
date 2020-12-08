package Map;

import ObjectsOnMap.Animal;
import ObjectsOnMap.Vector2d;

public interface IPositionChangeObserver {
    void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition);

}
