package Visualization;

import Map.SimulationEngine;
import Map.SimulationMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DataInputFrame extends JFrame implements ActionListener {

    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JSpinner energyStartSpinner;
    private JSpinner energyMoveSpinner;
    private JSpinner energyGrassSpinner;
    private JSpinner jungleRatioSpinner;
    private JSpinner numberOfMapsSpinner;
    private JSpinner numberOfAnimalSpinner;

    //TODO change dimensions
    public DataInputFrame(){
        super("EvolutionSimulatorDataInput");
        setSize(280,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel inputPanel = new JPanel();
        add(inputPanel);
        inputPanel.setLayout(null);

        JLabel widthLabel = new JLabel("Width");
        widthLabel.setBounds(10,20,80,25);
        inputPanel.add(widthLabel);
        SpinnerModel width = new SpinnerNumberModel(26,10,71,1);
        JSpinner widthSpinner = new JSpinner(width);
        widthSpinner.setBounds(100,20,165,25);
        inputPanel.add(widthSpinner);
        this.widthSpinner = widthSpinner;

        JLabel heightLabel = new JLabel("Height");
        heightLabel.setBounds(10,50,80,25);
        inputPanel.add(heightLabel);
        SpinnerModel height = new SpinnerNumberModel(26,10,71,1);
        JSpinner heightSpinner = new JSpinner(height);
        heightSpinner.setBounds(100,50,165,25);
        inputPanel.add(heightSpinner);
        this.heightSpinner = heightSpinner;

        JLabel energyStartLabel = new JLabel("Start energy");
        energyStartLabel.setBounds(10,80,80,25);
        inputPanel.add(energyStartLabel);
        SpinnerModel energyStart = new SpinnerNumberModel(11,10,100,1);
        JSpinner energyStartSpinner = new JSpinner(energyStart);
        energyStartSpinner.setBounds(100,80,165,25);
        inputPanel.add(energyStartSpinner);
        this.energyStartSpinner = energyStartSpinner;

        JLabel energyMoveLabel = new JLabel("Move energy");
        energyMoveLabel.setBounds(10,110,80,25);
        inputPanel.add(energyMoveLabel);
        SpinnerModel energyMove = new SpinnerNumberModel(0,0,100,1);
        JSpinner energyMoveSpinner = new JSpinner(energyMove);
        energyMoveSpinner.setBounds(100,110,165,25);
        inputPanel.add(energyMoveSpinner);
        this.energyMoveSpinner = energyMoveSpinner;

        JLabel energyGrassLabel = new JLabel("Grass energy");
        energyGrassLabel.setBounds(10,140,80,25);
        inputPanel.add(energyGrassLabel);
        SpinnerModel energyGrass = new SpinnerNumberModel(0,0,100,1);
        JSpinner energyGrassSpinner = new JSpinner(energyGrass);
        energyGrassSpinner.setBounds(100,140,165,25);
        inputPanel.add(energyGrassSpinner);
        this.energyGrassSpinner = energyGrassSpinner;


        JLabel jungleRatioLabel = new JLabel("Jungle ratio");
        jungleRatioLabel.setBounds(10,170,80,25);
        inputPanel.add(jungleRatioLabel);
        SpinnerModel jungleRatio = new SpinnerNumberModel(0.5,0,1,0.1);
        JSpinner jungleRatioSpinner = new JSpinner(jungleRatio);
        jungleRatioSpinner.setBounds(100,170,165,25);
        inputPanel.add(jungleRatioSpinner);
        this.jungleRatioSpinner = jungleRatioSpinner;

        JLabel numberOfMapsLabel = new JLabel("Num of maps");
        numberOfMapsLabel.setBounds(10,200,80,25);
        inputPanel.add(numberOfMapsLabel);
        SpinnerModel numberOfMaps = new SpinnerNumberModel(1,1,2,1);
        JSpinner numberOfMapsSpinner = new JSpinner(numberOfMaps);
        numberOfMapsSpinner.setBounds(100,200,165,25);
        inputPanel.add(numberOfMapsSpinner);
        this.numberOfMapsSpinner = numberOfMapsSpinner;

        JLabel numberOfAnimalsLabel = new JLabel("Animals");
        numberOfAnimalsLabel.setBounds(10,230,80,25);
        inputPanel.add(numberOfAnimalsLabel);
        SpinnerModel numberOfAnimals = new SpinnerNumberModel(1,1,100,1);
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
    //TODO datainterface
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        visualizationTest();
    }
    //TODO repair that and map init with animals
    public void visualizationTest(){
        System.out.println((int)this.widthSpinner.getValue());
        System.out.println((int)this.heightSpinner.getValue());
        System.out.println((double)this.jungleRatioSpinner.getValue());
        System.out.println((int)this.energyStartSpinner.getValue());
        System.out.println((int)this.energyGrassSpinner.getValue());
        System.out.println((int)this.energyMoveSpinner.getValue());

        SimulationMap map = new SimulationMap(
                (int)this.widthSpinner.getValue(),
                (int)this.heightSpinner.getValue(),
                (double)this.jungleRatioSpinner.getValue(),
                (int)this.energyStartSpinner.getValue(),
                (int)this.energyGrassSpinner.getValue(),
                (int)this.energyMoveSpinner.getValue());
        SimulationMap map2 = new SimulationMap(26,26,0.5,40, 10, 1);

        SimulationEngine engine2 = new SimulationEngine(map2);
        int nmbAnimals = (int)this.numberOfAnimalSpinner.getValue();
        SimulationEngine engine = new SimulationEngine(map);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame(engine, nmbAnimals);
            }
        });
    }
}
