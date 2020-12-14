package Map;


import ObjectsOnMap.Vector2d;


public interface IWorldMap {
    int getWidth();

    int getHeight();

    Vector2d getMapLowerLeft();

    Vector2d getMapUpperRigth();

    int getMoveEnergy();

    int getStartEnergy();


}
