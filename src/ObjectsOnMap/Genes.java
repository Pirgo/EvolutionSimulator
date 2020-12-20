//repair probably done
package ObjectsOnMap;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Genes {
    private int[] gene;
    private static final int lengthOfGene = 32;
    private static final int numberOfUniqueGenes = 8;

    public Genes(){
        this.gene = new int[lengthOfGene];

        generateRandomGene();
    }

    public Genes(Genes gene1, Genes gene2){
        this();

        int whereToCut1 = (int)(Math.random()*(lengthOfGene - 1));
        int whereToCut2 = (int)(Math.random()*(lengthOfGene - 1));
        while (whereToCut1 == whereToCut2){
            whereToCut2 = (int)(Math.random()*(lengthOfGene - 1));
        }

        if(whereToCut1 > whereToCut2){
            int tmp = whereToCut1;
            whereToCut1 = whereToCut2;
            whereToCut2 = tmp;
        }

        int[] gene1arr = gene1.getGene();
        int[] gene2arr = gene2.getGene();
        for(int i = 0; i<whereToCut1; i++){
            this.gene[i] = gene1arr[i];
        }
        for (int i = whereToCut1; i < whereToCut2; i++){
            this.gene[i] = gene2arr[i];
        }
        for (int i = whereToCut2; i < lengthOfGene; i++){
            this.gene[i] = gene1arr[i];
        }

        repairGene();


    }

    public int getLengthOfGene(){
        return lengthOfGene;
    }

    public int getNumberOfUniqueGenes(){
        return numberOfUniqueGenes;
    }

    public int[] getGene(){
        return this.gene;
    }


    private void generateRandomGene(){
        for(int i = 0; i < lengthOfGene; i++){
            this.gene[i] = (int)(Math.random()*numberOfUniqueGenes);
        }

        Arrays.sort(this.gene);

        repairGene();
    }


    private void repairGene(){
        int[] numberOfGeneInDNA = new int[numberOfUniqueGenes];
        Arrays.fill(numberOfGeneInDNA ,0);
        for(int gene : this.gene) {
            numberOfGeneInDNA[gene] += 1;
        }

        for (int i = 0; i<numberOfUniqueGenes; i++){
            if(numberOfGeneInDNA[i] == 0){
                boolean flag = true;
                int random = 0;
                //take random gene which will be replaced
                while (flag){
                    random = (int) (Math.random()*numberOfUniqueGenes);
                    if(numberOfGeneInDNA[random] > 1){
                        numberOfGeneInDNA[random] -= 1;
                        flag = false;
                    }
                }

                for (int j = 0; j < lengthOfGene; j++){
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
        return this.gene[(int)(Math.random() * lengthOfGene)];
    }

    public int getDominantGene(){
        int [] geneCounter = new int[lengthOfGene];
        Arrays.fill(geneCounter,0);
        for (int gene : this.gene){
            geneCounter[gene] += 1;
        }
        int max = geneCounter[0];
        int index = 0;
        boolean onlyOneMax = true;
        for(int i = 1 ; i < geneCounter.length; i++){
            if(geneCounter[i] > max){
                max = geneCounter[i];
                index = i;
                onlyOneMax = true;
            }else if(geneCounter[i] == max){
                onlyOneMax = false;
            }
        }
        //-1 means that there is no dominant gene, exp. 6th appears 8times and 7th appears 8times as well, (8 was max)
        if(!onlyOneMax) return -1;

        return index;


    }
}