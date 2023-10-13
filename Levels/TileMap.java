package Levels;

import Incercari.Vector2D;
import entities.Entity;
import entities.Player;
import utile.CollidedBox;
import utile.Constante;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileMap extends Entity {

    BufferedImage tilemap_comp;
    CollidedBox the_second_box;
    int component;

    boolean moveIt;

    boolean finished = false;

    boolean plutitoare = false;

    float yinitial;

    boolean occupied = false;
    boolean up = false, down = true;
    float borderup, borderdown;


    public float contor = 8.0f;

   public TileMap(float x, float y, int width, int height, int component){
        super((int) x, (int) y, width,  height);
        yinitial = y;
        this.component = component;
        the_second_box = new CollidedBox();
        if(this.component == 0)
        {
            the_second_box.init_CollidedBox((int) x + 25,(int)y+10, 75, 43);
        }
        else if(this.component == 1)
        {
            the_second_box.init_CollidedBox((int) x + 20,(int)y+20, 90, 18);
        }
        else if (this.component == 2) {
            the_second_box.init_CollidedBox((int) x+5,(int)y+20, 120, 18);
        }
        else if(this.component == 3){
            the_second_box.init_CollidedBox((int) x+5,(int)y+20, 120, 18);
        }

    }

    public void init_tile_map()
    {
        tilemap_comp = LoadSave.importImg(Constante.TileMap_Composition.Get_List_Of_components(component));
    }

    public void render(Graphics g)
    {
        g.drawImage(tilemap_comp, (int) vector2D.getVectX() , (int) vector2D.getVectY(), (int) width, (int) height, null);
        //the_second_box.drawCollidedBox(g);
        //getHitbox().drawCollidedBox(g);
    }

    //in functie de tilemap ul ales intializez al doilea collided box
    public void update(Player hero)
    {
        colliding_with_hero(hero);
        if(this.component == 0)
        {
            the_second_box.updateCollidedBox((int)vector2D.getVectX() + 25, (int)vector2D.getVectY() + 10);
            the_second_box.updateCollidedBoxWH(75, 43);
        }
        if(this.component==1)
        {
            the_second_box.updateCollidedBox((int)vector2D.getVectX() + 20, (int)vector2D.getVectY() + 20);
            the_second_box.updateCollidedBoxWH(90, 18);
        }

        if(this.component==2 || this.component == 3)
        {
            the_second_box.updateCollidedBox((int)vector2D.getVectX() +5, (int)vector2D.getVectY() + 20);
            the_second_box.updateCollidedBoxWH(120, 18);
        }



        move();
    }

    //functie de collideing with hero
    public void colliding_with_hero(Player hero)
    {
        setOccupied(false);
        if(the_second_box.getRect().intersects(hero.getThe_second_box().getRect()))
        {
            //dau senzatia de spatiu (dar doar o singura data) si setez ca fiind ocupat
            setOccupied(true);
            if(contor>0)
            {
                shakeit();
            }


        }


    }

    public void removeTileMap()
    {
        this.vector2D.modifyX(-0.5f);
    }

    public boolean getOccupied()
    {
        return occupied;
    }

    public void setOccupied(boolean occupied)
    {
        this.occupied = occupied;
    }

    public float getCoordonateX()
    {
        return vector2D.getVectX();
    }

    public float getCoordonateY()
    {
        return vector2D.getVectY();
    }

    //miscarea tilemapului la dreapta
    public void move()
    {
        if(moveIt)
        {
            vector2D.modifyX(0.5f);
        }

    }

    public void setMoveIt(boolean moveIt)
    {
        this.moveIt = moveIt;
    }

    public void shakeit()
    {
            this.getVector2D().modifyY(contor);
            contor = contor - 0.1f;
            this.getVector2D().modifyY(-contor);
    }

    //tilemapul care merge de sus in jos
    public void make_it_hard()
    {
        setPlutitoare(true);

        if(up)
        {
            this.vector2D.modifyY(-0.5f);
            if(this.vector2D.getVectY()<yinitial-borderup)
            {
                up = false;
                down = true;
            }
        }
        if(down)
        {
            this.vector2D.modifyY(0.5f);
            if(this.vector2D.getVectY()>yinitial+borderdown)
            {
                up = true;
                down = false;
            }
        }
    }


    public void setItAgain(float borderX)
    {

        if(getVector2D().getVectX()<=borderX)
        {
            getVector2D().modifyX(1);
        }
        else
        {
            finished = true;
        }

    }

    public void init_borders(float borderup, float borderdown){
       this.borderdown = borderdown;
       this.borderup = borderup;
    }

    public boolean getup()
    {
        return up;
    }
    public boolean getdown()
    {
        return down;
    }

    public boolean getPlutitoare()
    {
        return plutitoare;
    }

    public void setPlutitoare(boolean plutitoare)
    {
        this.plutitoare = plutitoare;
    }

    public float get_yinitial()
    {
        return yinitial;
    }

    public boolean getfinished()
    {
        return finished;
    }

    public CollidedBox getThe_second_box(){
       return the_second_box;
    }

    @Override
    public String toString() {
        String val = "Ma aflu pe platforma: " + this.vector2D.toString();
        return val;
    }
}
