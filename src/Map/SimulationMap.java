package Map;

import ObjectsOnMap.*;

import java.util.*;


public class SimulationMap implements IWorldMap, IPositionChangeObserver{
    private final Vector2d mapUpperRight;
    private final Vector2d mapLowerLeft;
    private final Vector2d jungleUpperRight;
    private final Vector2d jungleLowerLeft;

    private final int width;
    private final int height;
    private final int jungleWidth;
    private final int jungleHeight;


    private final int startEnergy;
    private final int grassEnergy;
    private final int moveEnergy;

    private int numberOfDeadAnimals;
    private int sumOfDaysLivedByDeadAnimals;
    private int day;

    private Map<Vector2d, List<Animal>> animalMap = new HashMap<>();
    private Map<Vector2d, Grass> grassMap = new HashMap<>();

    public SimulationMap(int width, int height, double jungleRatio, int startEnergy, int grassEnergy, int moveEnergy, List<Vector2d> positions){
        this.mapLowerLeft = new Vector2d(0,0);
        this.mapUpperRight = new Vector2d(width-1, height-1);
        this.width = width;
        this.height = height;
        this.jungleWidth = (int)(jungleRatio * width);
        this.jungleHeight = (int)(jungleRatio * height);

        this.jungleLowerLeft = new Vector2d((width - jungleWidth)/2, (height - jungleHeight)/2);
        this.jungleUpperRight = new Vector2d((width - jungleWidth)/2 + jungleWidth, (height - jungleHeight)/2 + jungleHeight);
        this.startEnergy = startEnergy;
        this.grassEnergy = grassEnergy;
        this.moveEnergy = moveEnergy;

        this.numberOfDeadAnimals = 0;
        this.sumOfDaysLivedByDeadAnimals = 0;
        this.day = 0;



        int i = 0;
        while (i < positions.size()){
            this.placeAnimalAtFreePosition(new Animal(positions.get(i), this.startEnergy, this, this, new Genes()));
            i++;
        }
    }

    //getters
//    @Override
    public Vector2d getMapLowerLeft() {
        return this.mapLowerLeft;
    }

    public Vector2d getMapUpperRigth(){
        return this.mapUpperRight;
    }

    //    @Override
    public int getWidth(){
        return this.width;
    }

    //    @Override
    public int getHeight(){
        return this.height;
    }

    public int getMoveEnergy(){
        return this.moveEnergy;
    }

    public int getStartEnergy(){
        return this.startEnergy;
    }

    public int getGrassEnergy(){ return this.grassEnergy;}

    public Vector2d getJungleLowerLeft(){
        return this.jungleLowerLeft;
    }

    public Vector2d getJungleUpperRight(){
        return this.jungleUpperRight;
    }

    public int getJungleWidth(){
        return this.jungleWidth;
    }

    public int getJungleHeight(){
        return this.jungleHeight;
    }

    public Map<Vector2d, Grass> getGrassMap(){
        return this.grassMap;
    }

    public Map<Vector2d, List<Animal>> getAnimalMap(){
        return this.animalMap;
    }

    //place animal at the not occupied position, it will be used at start
    public void placeAnimalAtFreePosition(Animal animal){
        if(this.animalMap.get(animal.getPosition()) == null){
            List<Animal> animalListAtPosition = new ArrayList<>();
            animalListAtPosition.add(animal);
            this.animalMap.put(animal.getPosition(), animalListAtPosition);
        }

    }

    public boolean isOccupiedByAnimal(Vector2d position){
        return this.animalMap.get(position) != null;
    }

    public boolean isOccupiedByGrass(Vector2d position){
        return this.grassMap.get(position) != null;
    }

    public List<Object> objectsAtPosition(Vector2d position){
        List<Object> result = new ArrayList<>();
        if(isOccupiedByAnimal(position)){
            result.addAll(this.animalMap.get(position));
        }
        if(isOccupiedByGrass(position)){
            result.add(grassMap.get(position));
        }

        return result;
    }
    //returns list of free positions in jungle
    public List<Vector2d> jungleFreeSpaces(){
        List<Vector2d> jungleFreeSpaces = new ArrayList<>();
        for (int x = this.jungleLowerLeft.x; x <= this.jungleUpperRight.x; x++) {
            for (int y = this.jungleLowerLeft.y; y <= this.jungleUpperRight.y; y++) {
                if(objectsAtPosition(new Vector2d(x,y)).size() == 0){
                    jungleFreeSpaces.add(new Vector2d(x,y));
                }
            }
        }

        return jungleFreeSpaces;

    }
    //returns list of free positions in steppe
    public List<Vector2d> steppeFreeSpaces(){
        List<Vector2d> steppeFreeSpaces = new ArrayList<>();

        //left
        for(int x = this.mapLowerLeft.x; x < this.jungleLowerLeft.x; x++){
            for(int y = this.mapLowerLeft.y; y <= this.mapUpperRight.y; y++){
                if(objectsAtPosition(new Vector2d(x,y)).size() == 0) steppeFreeSpaces.add(new Vector2d(x,y));

            }
        }
        //right
        for(int x = this.jungleUpperRight.x + 1; x <= this.mapUpperRight.x; x++){
            for(int y = this.mapLowerLeft.y; y <= this.mapUpperRight.y; y++){
                if(objectsAtPosition(new Vector2d(x,y)).size() == 0) steppeFreeSpaces.add(new Vector2d(x,y));
            }
        }
        //only top without left and right
        for(int x = this.jungleLowerLeft.x; x <= this.jungleUpperRight.x; x++){
            for(int y = this.jungleUpperRight.y + 1; y<= this.mapUpperRight.y; y++){
                if(objectsAtPosition(new Vector2d(x,y)).size() == 0) steppeFreeSpaces.add(new Vector2d(x,y));
            }
        }
        //only bottom without left and right
        for(int x = this.jungleLowerLeft.x; x <= this.jungleUpperRight.x; x++){
            for(int y = this.mapLowerLeft.y; y<= this.jungleLowerLeft.y; y++){
                if(objectsAtPosition(new Vector2d(x,y)).size() == 0) steppeFreeSpaces.add(new Vector2d(x,y));
            }
        }

        return steppeFreeSpaces;
    }

    public List<Animal> getAnimalsAsList(){
        List<Animal> animalsList = new ArrayList<>();
        for (Map.Entry<Vector2d, List<Animal>> entry : animalMap.entrySet()) {
            animalsList.addAll(entry.getValue());
        }

        return animalsList;
    }

    public List<Grass> getGrassesAsList(){
        List<Grass> grassesList = new ArrayList<>();

        for (Map.Entry<Vector2d, Grass> entry : this.grassMap.entrySet()) {
            grassesList.add(entry.getValue());
        }

        return grassesList;
    }

    //remember to use only with placeTwoGrasses, cuz there is no validation in this function, given grass CAN be placed at its position
    //adds to hashmap
    public void addGrass(Grass grass){
        this.grassMap.put(grass.getPosition(), grass);
    }

    //it is used when grass is getting eaten, no need to validate
    public void removeGrass(Grass grass){
        this.grassMap.remove(grass.getPosition());
    }

    public void addAnimal(Animal animal, Vector2d position){
        if(this.animalMap.get(position) == null){
            this.placeAnimalAtFreePosition(animal);
        }
        else {
            this.animalMap.get(position).add(animal);
        }
    }

    public void removeAnimal(Animal animal, Vector2d position){
        this.animalMap.get(position).remove(animal);
        if(this.animalMap.get(position).isEmpty()){
            this.animalMap.remove(position);
        }
    }
    //places two grasses one in jungle one in steppe
    public void placeTwoGrasses(){
        List<Vector2d> placesInJungle = this.jungleFreeSpaces();
        List<Vector2d> placesInSteppe = this.steppeFreeSpaces();

        if(placesInSteppe.size() != 0){
            Vector2d pos = placesInSteppe.get((int)(Math.random()*placesInSteppe.size()));
            this.addGrass(new Grass(pos));
        }

        if(placesInJungle.size() != 0){
            Vector2d pos = placesInJungle.get((int)(Math.random()*placesInJungle.size()));
            this.addGrass(new Grass(pos));
        }
    }

    @Override
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition) {
        this.addAnimal(animal, newPosition);
        this.removeAnimal(animal, oldPosition);

    }

    //used to eat grass, no validation needed
    public List<Animal> getEquallyStrongestAnimals(Vector2d position){
        List<Animal> result = new ArrayList<>();
        List<Animal> sortedAnimalsAtPosition = this.animalMap.get(position);
        sortedAnimalsAtPosition.sort(new AnimalSorter());
        double maxEnergy = sortedAnimalsAtPosition.get(0).getEnergy();
        for(Animal a : sortedAnimalsAtPosition){
            if(a.getEnergy() == maxEnergy){
                result.add(a);
            }
            else {
                break;
            }
        }

        return result;
    }

    public void eatGrass(Grass grass){
        if(this.isOccupiedByAnimal(grass.getPosition())){
            List<Animal> strongestAnimals = this.getEquallyStrongestAnimals(grass.getPosition());
            for (Animal a : strongestAnimals){
                a.changeEnergy(grassEnergy/(double)strongestAnimals.size());
            }
            this.removeGrass(grass);
        }
    }

    public void tryEatGrasses(){
        List<Grass> grassesToConsume = this.getGrassesAsList();
        for(Grass g : grassesToConsume){
            this.eatGrass(g);
        }
    }

    public void moveAllAnimals(){
        List<Animal> animalsToMove = this.getAnimalsAsList();

        for(Animal a : animalsToMove){
            a.changeEnergy(-moveEnergy);
            a.move();
        }

    }

    public void removeDeadAnimals(){
        List<Animal> animalsToRemove = this.getAnimalsAsList();
        for(Animal a : animalsToRemove){
            if(a.isDead()){
                a.dayOfDeath = this.day;
                a.removeObserver(this);
                this.numberOfDeadAnimals += 1;
                this.sumOfDaysLivedByDeadAnimals += a.livedDays;
                removeAnimal(a, a.getPosition());
            }
        }
    }



    //need to change and same in Animal
    public Vector2d toNoBoundedPosition(Vector2d position) {
        int newX;
        int newY;

        if (position.x < this.mapLowerLeft.x) {
            newX = (this.width - Math.abs(position.x % this.width)) % this.width;
        } else {
            newX = Math.abs(position.x % width);
        }
        if (position.y < this.mapLowerLeft.y) {
            newY = (this.height - Math.abs(position.y % this.height)) % this.height;
        } else {
            newY = Math.abs(position.y % this.height);
        }

        return new Vector2d(newX, newY);
    }

    //returns list of free positions around position or if there is no free positions around it, returns all
    public List<Vector2d> positionsForChild(Vector2d position){
        List<Vector2d> result = new ArrayList<>();
        List<Vector2d> result2 = new ArrayList<>();
        for(int x = position.x - 1; x<= position.x + 1; x++){
            for (int y = position.y - 1; y <= position.y + 1; y++){
                if(x != position.x || y != position.y){
                    result.add(toNoBoundedPosition(new Vector2d(x,y)));
                    if(objectsAtPosition(toNoBoundedPosition(new Vector2d(x,y))).size() == 0){
                        result2.add(toNoBoundedPosition(new Vector2d(x,y)));
                    }
                }
            }
        }
        if(result2.size() != 0) return result2;
        return result;
    }
    //would be nice to rewrite it
    public void copulateAll(){
        List<Animal> result = new ArrayList<>();
        for (Map.Entry<Vector2d, List<Animal>> entry : animalMap.entrySet()) {
            if(entry.getValue().size() > 1){
                List<Animal> animalsList = new ArrayList<>(entry.getValue());
                animalsList.sort(new AnimalSorter());
                Animal male = animalsList.get(0);
                Animal female = animalsList.get(1);
                if(2*male.getEnergy() > this.startEnergy && 2*female.getEnergy() > this.startEnergy){
                    List<Vector2d> positions = this.positionsForChild(male.getPosition());
                    double energy = male.getEnergy() * 0.25 + female.getEnergy() * 0.25;
                    Genes gene = new Genes(male.getGene(), female.getGene());
                    Vector2d position = positions.get((int)(Math.random() * positions.size()));
                    male.copulate();
                    female.copulate();
                    Animal child = new Animal(position, energy, this, this, gene);
                    //adding childs to the list
                    result.add(child);
                }
            }

        }
        //adding childs to hashmap
        for(Animal a : result){
            addAnimal(a, a.getPosition());
        }
    }


    //getters for stats
    public int getNumberOfAnimals(){
        return this.getAnimalsAsList().size();
    }

    public int getNumberOfGrasses(){
        return this.getGrassesAsList().size();
    }

    public double getAverageEnergy(){
        List<Animal> animals = this.getAnimalsAsList();
        double result = 0;
        for(Animal animal : animals){
            result += animal.getEnergy();
        }

        result /= animals.size();
        return  result;
    }

    //TODO dunno if it is correct, hardcoded number of genes, rename methods name
    public int getDominantGenotype(){
        int[] geneCounter = new int[8];
        List<Animal> animals = this.getAnimalsAsList();
        for(Animal animal : animals){
            int[] geneType = animal.getGene().getGene();
            for (int value : geneType) {
                geneCounter[value]++;
            }
        }
        int max = 0;
        int index = 0;
        for(int i = 0; i<8; i++){
            if(geneCounter[i] > max){
                max = geneCounter[i];
                index = i;
            }
        }

        return index;

    }

    public double getAverageChildCount(){
        List<Animal> animals = this.getAnimalsAsList();
        double result = 0;
        for(Animal animal : animals){
            result += animal.getNumberOfchilds();
        }
        if(animals.size() > 0){
            result = result/animals.size();
        }
        return result;
    }

    public double getAverageDaysLivedByAnimal(){
        return (double)this.sumOfDaysLivedByDeadAnimals/(double)this.numberOfDeadAnimals;
    }

    public void increaseDay(){
        this.day += 1;
    }

    public int getDays(){
        return this.day;
    }


}
