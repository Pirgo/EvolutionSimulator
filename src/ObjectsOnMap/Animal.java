package ObjectsOnMap;

import Map.MapDirection;

public class Animal implements IMapElement{
    private Vector2d position;
    private Genes gene;
    private Energy energy;
    private MapDirection orientation;


    public void rotate(){
        for(int i=0; i < this.gene.returnRandomGene(); i++){
            this.orientation = this.orientation.next();
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }
}
