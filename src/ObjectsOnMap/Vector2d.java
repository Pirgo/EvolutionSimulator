package ObjectsOnMap;

import Map.IWorldMap;
import Map.SimulationStartData;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }


    //creates random vector in map boundries
    public Vector2d(IWorldMap map){
        Vector2d mapLowerLeft = map.getMapLowerLeft();
        Vector2d mapUpperRigth = map.getMapUpperRigth();

        this.x = (int)(Math.random()*(mapUpperRigth.x - mapLowerLeft.x));
        this.y = (int)(Math.random()*(mapUpperRigth.y - mapLowerLeft.y));
    }

    //used when placing animals at start
    public Vector2d(SimulationStartData data){

        this.x = (int)(Math.random()*(data.width - 1));
        this.y = (int)(Math.random()*(data.height - 1));
    }

    public String toString(){
        return "(" + this.x +","+this.y+")";
    }
    public boolean precedes(Vector2d other){
        if (other.x >= this.x && other.y >= this.y){
            return true;
        }
        return false;
    }

    public boolean follows(Vector2d other){
        if (other.x <= this.x && other.y <= this.y){
            return true;
        }
        return false;
    }

    public Vector2d upperRight(Vector2d other){
        int tmp1 = this.x;
        int tmp2 = this.y;
        if (other.x > this.x){
            tmp1 = other.x;
        }
        if (other.y > this.y){
            tmp2 = other.y;
        }
        return new Vector2d(tmp1,tmp2);
    }
    public Vector2d lowerLeft(Vector2d other){
        int tmp1 = this.x;
        int tmp2 = this.y;
        if (other.x < this.x){
            tmp1 = other.x;
        }
        if (other.y < this.y){
            tmp2 = other.y;
        }
        return new Vector2d(tmp1, tmp2);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if (this.x == that.x && this.y == that.y)
            return true;
        return false;

    }
    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
