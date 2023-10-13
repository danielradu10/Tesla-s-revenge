package entities;

import utile.CollidedBox;
import utile.Constante;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Light extends Entity{

    static BufferedImage[]animation = new BufferedImage[5];
    CollidedBox the_second_box = new CollidedBox();
    boolean aprins;

    boolean functional = true;
    int stare = 0;
    int animation_tick,  animation_index;

    int number_of_times_for_speed = 5;
    int animation_speed = 160;
    public Light(float x, float y, int height, int width) {
        super(x, y, height, width);
        the_second_box.init_CollidedBox((int)x,(int)y,(int)height,(int)width);
    }

    public void init()
    {
        animation[0] = LoadSave.importImg("/ResFolder/Light/light_bulb_01.png");
        animation[1] = LoadSave.importImg("/ResFolder/Light/light_bulb_02.png");
        animation[2] = LoadSave.importImg("/ResFolder/Light/light_bulb_03.png");
        animation[3] = LoadSave.importImg("/ResFolder/Light/light_bulb_04.png");
        animation[4] = LoadSave.importImg("/ResFolder/Light/light_bulb_05.png");
    }

    public void render(Graphics g)
    {
        g.drawImage(animation[animation_index], (int) vector2D.getVectX(), (int) vector2D.getVectY(), (int) width, (int) height, null);
        //the_second_box.drawCollidedBox(g);
    }


    public void update(Player hero)
    {
       updateAnimationTick();
       the_second_box.updateCollidedBox((int)getVector2D().getVectX(), (int)getVector2D().getVectY());
       colliding_with_hero(hero);
    }

    public boolean getAprins()
    {
        return aprins;
    }

    public void setAprins(boolean aprins)
    {
        this.aprins = aprins;
    }


    private void updateAnimationTick() {


        if (functional) {
            //daca e timpul ca un nou Fireball sa vina, maresc viteza cu care se schimba nimatiile astfel incat becul sa clipeasac mai des
            if (number_of_times_for_speed >= 10) {
                number_of_times_for_speed = 0;

            }
            if (number_of_times_for_speed >= 1) {
                animation_speed = 30;
                setAprins(true);
            } else {
                animation_speed = 2000;
                setAprins(false);
            }
            animation_tick++;

            if (animation_tick >= animation_speed) {
                animation_tick = 0;
                animation_index++;
                if (animation_index >= 5) {
                    {
                        animation_index = 0;
                        number_of_times_for_speed++;
                    }
                }
            }
        }
        else
        {
            animation_index = 0;
        }
    }


    //daca becul este atins de player, se stinge definitiv, functional = false (nu mai este functional)
     public void colliding_with_hero(Player hero)
     {
         if(the_second_box.getRect().intersects(hero.getThe_second_box().getRect()))
         {
             functional = false;
         }
     }

     public boolean getFunctional()
     {
         return functional;
     }

     public void removeLight()
     {
         getVector2D().modifyX(-0.5f);
     }


}
