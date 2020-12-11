package Visualization;


import Map.SimulationMap;
import ObjectsOnMap.Animal;
import ObjectsOnMap.Grass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapPanel extends JPanel {
    public int panelWidth;
    public int panelHeigth;
    public SimulationMap map;
    public int widthRatio;
    public int heigthRatio;
    public Dimension frameDim;
    private BufferedImage grassImg;

    // need to add ratio
    public MapPanel(SimulationMap map, Dimension size){
        this.map = map;
        this.frameDim = size;
        System.out.println(frameDim.width);
        this.widthRatio = Math.round(500 / map.getWidth());
        this.heigthRatio = Math.round(500/  map.getHeight());
        this.panelWidth = this.widthRatio * map.getWidth();
        this.panelHeigth = this.heigthRatio * map.getHeight();
        setPreferredSize(new Dimension(this.panelWidth, this.panelHeigth));
        setLocation(0,0);
        setLayout(null);


    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //draw steppe
        g2d.setColor(new Color(236, 223, 25));
        g2d.fillRect(0,0,this.panelWidth, this.panelHeigth);

        //draw jungle
        g2d.setColor(new Color(0, 170, 0));
        g2d.fillRect(this.map.getJungleLowerLeft().x * widthRatio, this.map.getJungleLowerLeft().y * heigthRatio, this.map.getJunglelwidth() * widthRatio, this.map.getJunglelheight() * heigthRatio);

        //draw grass
        for(Grass grass : this.map.getGrassesAsList()){
            g2d.setColor(new Color(30, 61, 23));
            int xPos = map.toNoBoundedPosition(grass.getPosition()).x;
            int yPos = map.toNoBoundedPosition(grass.getPosition()).y;
            g2d.fillRect(xPos * widthRatio,yPos * heigthRatio,widthRatio,heigthRatio);
        }

        //draw animals
        for(Animal animal : this.map.getAnimalsAsList()){
            // temporary solution
            g2d.setColor(animal.animalColor());
            int xPos = map.toNoBoundedPosition(animal.getPosition()).x;
            int yPos = map.toNoBoundedPosition(animal.getPosition()).y;
            g2d.fillOval(xPos * widthRatio,yPos * heigthRatio,widthRatio,heigthRatio);

        }
    }
}
