package Visualization;

import Map.SimulationEngine;
import Map.SimulationMap;
import Map.SimulationStartData;
import ObjectsOnMap.Vector2d;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;


public class DataInputFrame extends JFrame implements ActionListener {

    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JSpinner energyStartSpinner;
    private JSpinner energyMoveSpinner;
    private JSpinner energyGrassSpinner;
    private JSpinner jungleRatioSpinner;
    private JSpinner numberOfMapsSpinner;
    private JSpinner numberOfAnimalSpinner;
    private SimulationStartData simulationData;

    private JSONObject paramaters;

    //TODO change dimensions
    public DataInputFrame() throws Exception{
        super("EvolutionSimulatorDataInput");
        setSize(280,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel inputPanel = new JPanel();
        add(inputPanel);
        inputPanel.setLayout(null);


        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("src\\parameters.json");
        if(jsonObject == null) throw new Exception();
        this.paramaters = jsonObject;

        SimulationStartData fromJson = new SimulationStartData(
                Integer.parseInt(jsonObject.get("width").toString()),
                Integer.parseInt(jsonObject.get("height").toString()),
                Integer.parseInt(jsonObject.get("energyStart").toString()),
                Integer.parseInt(jsonObject.get("energyMove").toString()),
                Integer.parseInt(jsonObject.get("energyGrass").toString()),
                Double.parseDouble(jsonObject.get("jungleRatio").toString()),
                Integer.parseInt(jsonObject.get("numOfMaps").toString()),
                Integer.parseInt(jsonObject.get("numOfAnimals").toString())
        );



        JLabel widthLabel = new JLabel("Width");
        widthLabel.setBounds(10,20,80,25);
        inputPanel.add(widthLabel);
        SpinnerModel width = new SpinnerNumberModel(fromJson.width,10,71,1);
        JSpinner widthSpinner = new JSpinner(width);
        widthSpinner.setBounds(100,20,165,25);
        inputPanel.add(widthSpinner);
        this.widthSpinner = widthSpinner;

        JLabel heightLabel = new JLabel("Height");
        heightLabel.setBounds(10,50,80,25);
        inputPanel.add(heightLabel);
        SpinnerModel height = new SpinnerNumberModel(fromJson.height,10,71,1);
        JSpinner heightSpinner = new JSpinner(height);
        heightSpinner.setBounds(100,50,165,25);
        inputPanel.add(heightSpinner);
        this.heightSpinner = heightSpinner;

        JLabel energyStartLabel = new JLabel("Start energy");
        energyStartLabel.setBounds(10,80,80,25);
        inputPanel.add(energyStartLabel);
        SpinnerModel energyStart = new SpinnerNumberModel(fromJson.energyStart,10,100,1);
        JSpinner energyStartSpinner = new JSpinner(energyStart);
        energyStartSpinner.setBounds(100,80,165,25);
        inputPanel.add(energyStartSpinner);
        this.energyStartSpinner = energyStartSpinner;

        JLabel energyMoveLabel = new JLabel("Move energy");
        energyMoveLabel.setBounds(10,110,80,25);
        inputPanel.add(energyMoveLabel);
        SpinnerModel energyMove = new SpinnerNumberModel(fromJson.energyMove,0,100,1);
        JSpinner energyMoveSpinner = new JSpinner(energyMove);
        energyMoveSpinner.setBounds(100,110,165,25);
        inputPanel.add(energyMoveSpinner);
        this.energyMoveSpinner = energyMoveSpinner;

        JLabel energyGrassLabel = new JLabel("Grass energy");
        energyGrassLabel.setBounds(10,140,80,25);
        inputPanel.add(energyGrassLabel);
        SpinnerModel energyGrass = new SpinnerNumberModel(fromJson.energyGrass,0,100,1);
        JSpinner energyGrassSpinner = new JSpinner(energyGrass);
        energyGrassSpinner.setBounds(100,140,165,25);
        inputPanel.add(energyGrassSpinner);
        this.energyGrassSpinner = energyGrassSpinner;


        JLabel jungleRatioLabel = new JLabel("Jungle ratio");
        jungleRatioLabel.setBounds(10,170,80,25);
        inputPanel.add(jungleRatioLabel);
        SpinnerModel jungleRatio = new SpinnerNumberModel(fromJson.jungleRatio,0,1,0.1);
        JSpinner jungleRatioSpinner = new JSpinner(jungleRatio);
        jungleRatioSpinner.setBounds(100,170,165,25);
        inputPanel.add(jungleRatioSpinner);
        this.jungleRatioSpinner = jungleRatioSpinner;

        JLabel numberOfMapsLabel = new JLabel("Num of maps");
        numberOfMapsLabel.setBounds(10,200,80,25);
        inputPanel.add(numberOfMapsLabel);
        SpinnerModel numberOfMaps = new SpinnerNumberModel(fromJson.numberOfMaps,1,2,1);
        JSpinner numberOfMapsSpinner = new JSpinner(numberOfMaps);
        numberOfMapsSpinner.setBounds(100,200,165,25);
        inputPanel.add(numberOfMapsSpinner);
        this.numberOfMapsSpinner = numberOfMapsSpinner;

        JLabel numberOfAnimalsLabel = new JLabel("Animals");
        numberOfAnimalsLabel.setBounds(10,230,80,25);
        inputPanel.add(numberOfAnimalsLabel);
        SpinnerModel numberOfAnimals = new SpinnerNumberModel(fromJson.numberOfAnimal,1,100,1);
        JSpinner numberOfAnimalsSpinner = new JSpinner(numberOfAnimals);
        numberOfAnimalsSpinner.setBounds(100,230,165,25);
        inputPanel.add(numberOfAnimalsSpinner);
        this.numberOfAnimalSpinner = numberOfAnimalsSpinner;

        JButton startSimulation = new JButton("Start");
        startSimulation.setBounds(10,260, 255, 25);
        inputPanel.add(startSimulation);
        startSimulation.addActionListener(this::actionPerformed);


        setVisible(true);


    }


    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        try{
            loadData();
        }catch (IOException event){
            event.printStackTrace();
        }

        visualizationTest();
    }

    public void loadData() throws IOException {
        this.simulationData = new SimulationStartData(
                (int)this.widthSpinner.getValue(),
                (int)this.heightSpinner.getValue(),
                (int)this.energyStartSpinner.getValue(),
                (int)this.energyMoveSpinner.getValue(),
                (int)this.energyGrassSpinner.getValue(),
                (double)this.jungleRatioSpinner.getValue(),
                (int)this.numberOfMapsSpinner.getValue(),
                (int)this.numberOfAnimalSpinner.getValue()
        );

        this.paramaters.put("jungleRatio", simulationData.jungleRatio);
        this.paramaters.put("energyMove", simulationData.energyMove);
        this.paramaters.put("energyGrass", simulationData.energyGrass);
        this.paramaters.put("width", simulationData.width);
        this.paramaters.put("energyStart", simulationData.energyStart);
        this.paramaters.put("numOfMaps", simulationData.numberOfMaps);
        this.paramaters.put("height", simulationData.height);
        this.paramaters.put("numOfAnimals", simulationData.numberOfAnimal);

        Files.write(Paths.get("src\\parameters.json"), this.paramaters.toJSONString().getBytes());
    }

    //TODO repair that and map init with animals
    public void visualizationTest(){

        SimulationMap map = new SimulationMap(
                this.simulationData.width,
                this.simulationData.height,
                this.simulationData.jungleRatio,
                this.simulationData.energyStart,
                this.simulationData.energyGrass,
                this.simulationData.energyMove,
                this.simulationData.startingPositions,
                this.simulationData.genesForStartAnimals

        );
        SimulationEngine engine = new SimulationEngine(map);






        new Frame(engine, new Vector2d(0,0));

        if(this.simulationData.numberOfMaps == 2){
            SimulationMap map2 = new SimulationMap(
                    this.simulationData.width,
                    this.simulationData.height,
                    this.simulationData.jungleRatio,
                    this.simulationData.energyStart,
                    this.simulationData.energyGrass,
                    this.simulationData.energyMove,
                    this.simulationData.startingPositions,
                    this.simulationData.genesForStartAnimals
            );
            SimulationEngine engine2 = new SimulationEngine(map2);

            new Frame(engine2, new Vector2d(500,0));
        }

    }


}
