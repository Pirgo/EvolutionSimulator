package ObjectsOnMap;

public class Grass implements IMapElement{
    private Vector2d position;
    private int energyValue;

    public Grass(Vector2d position, int energyValue){
        this.position = position;
        this.energyValue = energyValue;
    }

    @Override
    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString(){
        return "*";
    }
}
