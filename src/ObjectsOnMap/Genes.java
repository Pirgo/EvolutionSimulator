package ObjectsOnMap;

import java.lang.reflect.Array;
import java.util.Arrays;


public class Genes {
    private int[] gene;
    private int lengthOfGene;
    private int numberOfUniqueGenes;

    public Genes(int lengthOfGene, int numberOfUniqueGenes){
        this.gene = new int[lengthOfGene];
        this.lengthOfGene = lengthOfGene;
        this.numberOfUniqueGenes = numberOfUniqueGenes;
        generateRandomGene();
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

    //private??
    public void generateRandomGene(){
        for(int i = 0; i < this.lengthOfGene; i++){
            this.gene[i] = (int)(Math.random()*this.numberOfUniqueGenes);
        }
        Arrays.sort(this.gene);

        repairGene();
    }

    //private??
    public void repairGene(){
        int[] numberOfGeneInDNA = new int[this.numberOfUniqueGenes];
        Arrays.fill(numberOfGeneInDNA ,0);
        for(int gene : this.gene) {
            numberOfGeneInDNA[gene] += 1;
        }

        for (int i = 0; i<this.numberOfUniqueGenes; i++){
            if(numberOfGeneInDNA[i] == 0){
                System.out.println(i);
                boolean flag = true;
                int random = 0;
                //take random gene which will be replaced
                while (flag){
                    random = (int) (Math.random()*this.numberOfUniqueGenes);
                    if(numberOfGeneInDNA[random] > 1){
                        numberOfGeneInDNA[random] -= 1;
                        flag = false;
                    }
                }

                for (int j = 0; j < this.lengthOfGene; j++){
                    if(this.gene[j] == random){
                        this.gene[j] = i;
                        break;
                    }
                }
            }
        }
        Arrays.sort(this.gene);
    }

    public int returnRandomGene(){
        return this.gene[(int)(Math.random() * this.lengthOfGene)];
    }
}
