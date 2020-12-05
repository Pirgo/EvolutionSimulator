package ObjectsOnMap;

import java.lang.reflect.Array;

public class Genes {
    private int[] gene;
    private int lengthOfGene;
    private int numberOfUniqueGenes;

    public Genes(int lengthOfGene, int numberOfUniqueGenes){
        this.gene = new int[lengthOfGene];
        this.lengthOfGene = lengthOfGene;
        this.numberOfUniqueGenes = numberOfUniqueGenes;
    }

    public int getLengthOfGene(){
        return this.lengthOfGene;
    }
    
    public int getNumberOfUniqueGenes(){
        return this.numberOfUniqueGenes;
    }

    public int[] getGene(){
        return this.gene;
    }


}
