package entities;

import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TeslaCard extends  Entity{

    BufferedImage tesla;

    boolean almost_clicked;
    int clicked = 0;

    int unclicked = 1;

    int renderLightning;

    boolean lightning;

    Fulger f1;
    public TeslaCard(float x, float y, int height, int width) {
        super(x, y, height, width);
        tesla = LoadSave.importImg("/tesla/tesla.png");
        f1 = new Fulger(-100,0,128, 128, 2);

    }


    //in aceasta metoda fac cardul mai mic cand este apasat
    public void update(){
        f1.update();
        if(clicked == 1)
        {
            this.width = 50;
            this.height = 50;
            System.out.println("Eu am incercat");
        }
        //apare fulgerul
        if (lightning)
        {
            f1.getVector2D().setVectX(getVector2D().getVectX());
            f1.getVector2D().setVectY(getVector2D().getVectY());



        }


    }

    public void render(Graphics g){

        if(!lightning)
        {
            g.drawImage(tesla, (int) vector2D.getVectX() , (int) vector2D.getVectY(), (int) width, (int) height, null);
        }

        if(lightning)
        {
            //cat timp apare fulgerul
            if (renderLightning < 100)
            {
                f1.render(g);
                renderLightning++;
            }
        }


    }

    public void setFulgerSeahorse(Seahorse seahorse)
    {
        this.f1.setSeahorse(seahorse);
    }

    public void set_almost_clicked(boolean val)
    {
        this.almost_clicked = val;
    }

    public boolean get_almost_clicked()
    {
        return this.almost_clicked;
    }

    public void set_clicked(int value)
    {
        this.clicked = value;
    }

    public int getClicked()
    {
        return this.clicked;
    }

    public void setUnclicked(int value)
    {
        this.clicked = value;
    }

    public int getUnclicked(){
        return this.unclicked;
    }

    public void setLightning(boolean value)
    {
        this.lightning = value;
    }
}
