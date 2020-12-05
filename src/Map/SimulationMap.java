package Map;

import ObjectsOnMap.Animal;
import ObjectsOnMap.Vector2d;

public class SimulationMap implements IWorldMap{
    private Vector2d mapUpperRight;
    private Vector2d mapLowerLeft;
    private Vector2d jungleUpperRight;
    private Vector2d jungleLowerLeft;

    private int width;
    private int height;

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
