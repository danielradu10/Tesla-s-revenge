package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs = new MouseInputs(this);
    public int xDelta = 100, yDelta = 100;

    private int frames = 0;
    private long lastCheck = 0;
    private Game game;




    public GamePanel(Game game)
    {
        this.game = game;
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);

        System.out.println("Am dat");
        setPanelSize();
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    //takes care of our drawing
    //JPanel is saying where we can draw
    //Graphics object is starting to draw
    public void paintComponent(Graphics g)              //functie virtuala a clasei JComponent, trebuie sa o definim!
    {
        super.paintComponent(g);    //calling the super class: JComponent, calling the paintComponent
        game.render(g);
        //if we don't call it we may have some awkward things like not cleaning the window
        //We need to put the JPanel inside the JFrame
    }

    public void updateGame(){

    }

    public Game getGame(){
        return game;
    }
}


