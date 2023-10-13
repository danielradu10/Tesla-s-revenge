package entities;

import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Document extends Entity{

    private BufferedImage imagine;

    boolean capturat;


    public Document(float x, float y, int height, int width) {
        super(x, y, height, width);
    }

    public void init_document()
    {
        imagine = LoadSave.importImg("/ResFolder/Document/closed.png");
    }


    public void update(Player hero)
    {
        colliding_with_hero(hero);
        getHitbox().updateCollidedBox((int)getVector2D().getVectX(), (int)getVector2D().getVectY());
    }
    public void render(Graphics g)
    {
        g.drawImage(imagine, (int) vector2D.getVectX(),(int) vector2D.getVectY(),(int) width, (int) height,null);
        //getHitbox().drawCollidedBox(g);
    }


    //metoda de colliding in care setez variabila capturat ca fiind true
    public void colliding_with_hero(Player hero)
    {
        if(getHitbox().getRect().intersects(hero.getThe_second_box().getRect()))
        {
            System.out.println("Capturat");
            capturat = true;
            //width =0;
            //height = 0;
            getVector2D().setVectX(-20);
            getVector2D().setVectY(-20);
        }
    }

    public boolean getCapturat()
    {
        return capturat;
    }
    public void setCapturat(boolean capturat)
    {
        this.capturat = capturat;

    }

    public void remove()
    {
        getVector2D().modifyY(-0.5f);
    }


}
