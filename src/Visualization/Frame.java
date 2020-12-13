package Visualization;

import Map.SimulationEngine;
import Map.SimulationMap;
import ObjectsOnMap.Animal;
import ObjectsOnMap.Genes;
import ObjectsOnMap.Vector2d;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.Map;

//todo savebutton
public class Frame extends JFrame implements ActionListener {

    public SimulationMap map;
    public SimulationEngine engine;
    public MapPanel mapPanel;
    public StatistickPanel statPanel;
    public JButton startButton;
    public JButton stopButton;
    public JButton saveButton;
    public Timer timer;

    //todo move to MapPanel, add coordinates
    //Todo maybe add back to setting button
    public Frame(SimulationEngine engine, Vector2d loacation){
        super("EvolutionSimulator");
        this.engine = engine;
        this.map = engine.map;
        this.timer = new Timer(100, this::actionPerformed);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(510, 700);
//        setBackground(Color.white);
        setVisible(true);
        try{
            this.mapPanel = new MapPanel(this.map, this.getSize(), this);
        }catch (IOException e){
            System.out.println("Nie udało wczytać się zdjęc");
        }



        setLocation(loacation.x,loacation.y);
        add(mapPanel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this::startTimer);
        startButton.setBounds(40,510,150,40);
        this.startButton = startButton;
        mapPanel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(this::stopTimer);
        stopButton.setBounds(40,550,150,40);
        stopButton.setEnabled(false);
        this.stopButton = stopButton;
        mapPanel.add(stopButton);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(40,590,150,40);
        saveButton.addActionListener(this::save);
        mapPanel.add(saveButton);

        StatistickPanel stats = new StatistickPanel(this.map);
        this.statPanel = stats;
        mapPanel.add(stats);








    }



    @Override
    public void actionPerformed(ActionEvent e) {
        this.engine.run();
        this.mapPanel.repaint();
        this.statPanel.updateStats();
        if(this.mapPanel.details != null){
            this.mapPanel.details.updateAnimalDetails();
        }



    }

    public void stopTimer(ActionEvent e){
        this.timer.stop();
        this.stopButton.setEnabled(false);
        this.startButton.setEnabled(true);
    }

    public void startTimer(ActionEvent e){
        this.timer.start();
        this.startButton.setEnabled(false);
        this.stopButton.setEnabled(true);
    }

    public void save(ActionEvent e){
        System.out.println(this.statPanel.toString());
        String text = this.statPanel.toString();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
        fileChooser.addChoosableFileFilter(filter);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if(!file.getName().contains(".")) file =new File(file.toString() + ".txt");
            try (PrintWriter out = new PrintWriter(file)) {
                out.println(text);
            }catch (FileNotFoundException event){
                event.printStackTrace();
            }
        }
    }


}
