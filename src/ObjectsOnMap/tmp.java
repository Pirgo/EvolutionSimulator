package ObjectsOnMap;

import java.util.Arrays;

public class tmp {
    public static void main(String[] args){
        Genes gene = new Genes(32, 8);
        System.out.println(Arrays.toString(gene.getGene()));
        int i = 0;
        while (i < 10){
            i++;
            gene =  new Genes(32, 8);
            System.out.println(Arrays.toString(gene.getGene()));
            int x = gene.returnRandomGene();
            System.out.println(x);
        }
    }
}
