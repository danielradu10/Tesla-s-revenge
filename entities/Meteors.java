package entities;

import utile.CollidedBox;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Meteors extends Entity{

    public BufferedImage meteorit;
    public float xinitial, yinital;

    public CollidedBox collided_box;
    public Meteors(float x, float y, int height, int width) {
        super(x, y, height, width);
        xinitial = x;
        yinital = y;
        this.init_meteor();
        this.collided_box = new CollidedBox();
        this.collided_box.init_CollidedBox((int) x, (int) y, height, width);
    }

    public void init_meteor(){
        meteorit = LoadSave.importImg("/Meteoriti/meteorit1.png");
    }
    public void render(Graphics g){
        g.drawImage(meteorit, (int) vector2D.getVectX(), (int) vector2D.getVectY(), (int) width, (int) height, null);
        //this.collided_box.drawCollidedBox(g);
    }
    public void update(Player hero){

        vector2D.modifyX(-0.05f);
        vector2D.modifyY(0.05f);
        if(vector2D.getVectY()>720 || vector2D.getVectX()<0)
        {
            vector2D.setVectY(yinital);
            vector2D.setVectX(xinitial);
        }

        this.collided_box.updateCollidedBox((int) vector2D.getVectX(), (int) vector2D.getVectY());
        colliding_with_hero(hero);
    }

    public void colliding_with_hero(Player hero)
    {
        if(collided_box.getRect().intersects(hero.getHitbox().getRect()))
        {
            System.out.println("L-am intersectat");
        }

    }
}
