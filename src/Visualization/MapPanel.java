package Visualization;


import Map.SimulationMap;
import ObjectsOnMap.Animal;
import ObjectsOnMap.Grass;
import ObjectsOnMap.Vector2d;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapPanel extends JPanel implements MouseListener {
    private int panelWidth;
    private int panelHeigth;
    private SimulationMap map;
    private int widthRatio;
    private int heigthRatio;
    private Dimension frameDim;
    private final BufferedImage grassImg;
    private final BufferedImage animalImg;
    private final BufferedImage animalDominantImg;
    protected AnimalDetailsFrame details;
    private Frame frame;

    public MapPanel(SimulationMap map, Dimension size, Frame frame) throws IOException{
        this.map = map;
        this.frame = frame;
        this.frameDim = size;
        this.widthRatio = (int)Math.round(500 / (double)map.getWidth());
        this.heigthRatio = (int)Math.round(500/  (double)map.getHeight());
        this.panelWidth = this.widthRatio * map.getWidth();
        this.panelHeigth = this.heigthRatio * map.getHeight();
        setSize(new Dimension(this.panelWidth, this.panelHeigth));
        setPreferredSize(new Dimension(this.panelWidth, this.panelHeigth));
        setLayout(null);
        setBackground(Color.white);
        this.addMouseListener(this);


        this.grassImg = ImageIO.read(new File("src/Visualization/images/grass2.png"));
        if(this.grassImg == null) throw new IOException();

        this.animalImg = ImageIO.read(new File("src/Visualization/images/animal1.png"));
        if(this.animalImg == null) throw new IOException();

        this.animalDominantImg = ImageIO.read(new File("src/Visualization/images/animal2.png"));
        if(this.animalDominantImg == null) throw new IOException();




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
        g2d.fillRect(this.map.getJungleLowerLeft().x * widthRatio, this.map.getJungleLowerLeft().y * heigthRatio, this.map.getJungleWidth() * widthRatio, this.map.getJungleHeight() * heigthRatio);

        //draw grass
        for(Grass grass : this.map.getGrassesAsList()){
            int xPos = map.wrapPosition(grass.getPosition()).x;
            int yPos = map.wrapPosition(grass.getPosition()).y;
            g2d.drawImage(this.grassImg, xPos*widthRatio, yPos*heigthRatio, widthRatio,heigthRatio, null);
        }

        //draw animals
        for(Animal animal : this.map.getAnimalsAsList()){
            g2d.setColor(animal.animalColor());
            int xPos = map.wrapPosition(animal.getPosition()).x;
            int yPos = map.wrapPosition(animal.getPosition()).y;
            g2d.fillOval(xPos * widthRatio,yPos * heigthRatio,widthRatio,heigthRatio);
            g2d.drawImage(this.animalImg, xPos*widthRatio, yPos*heigthRatio, widthRatio,heigthRatio, null);
            if(this.map.getDominantGenotype() == animal.getDominantGene() && !this.frame.stopButton.isEnabled()){
                g2d.drawImage(this.animalDominantImg, xPos*widthRatio, yPos*heigthRatio, widthRatio,heigthRatio, null);
            }


        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Vector2d animalPosition = new Vector2d((int)Math.floor(e.getX()/(double)this.widthRatio), (int)Math.floor(e.getY()/(double)this.heigthRatio));
        if(!this.map.isOccupiedByAnimal(animalPosition)) return;
        if(this.details !=null) this.details.dispatchEvent(new WindowEvent(this.details, WindowEvent.WINDOW_CLOSING));
        this.details = new AnimalDetailsFrame(animalPosition, this.map, this.frame);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
