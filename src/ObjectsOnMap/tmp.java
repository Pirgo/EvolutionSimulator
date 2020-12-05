package ObjectsOnMap;

import java.util.Arrays;

public class tmp {
    public static void main(String[] args){
        Genes gene = new Genes(32, 8);
        Genes gene2 = new Genes(32, 8);
        System.out.println(Arrays.toString(gene.getGene()));
        System.out.println(Arrays.toString(gene2.getGene()));
        Genes child = new Genes(gene, gene2);
        System.out.println(Arrays.toString(child.getGene()));
    }
}
