package entities;

import utile.CollidedBox;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FireBall extends Entity {

    static BufferedImage[]animation = new BufferedImage[6];
    CollidedBox the_seconde_box = new CollidedBox();


    float xinitial;
    boolean go_get_him = false;
    int animation_speed = 20;
    int animation_tick,  animation_index;


    public FireBall(float x, float y, int height, int width) {
        super(x, y, height, width);
        xinitial = x;
        the_seconde_box.init_CollidedBox((int) x, (int)y, (int) width-15, (int) height);
    }

    public void init()
    {
        animation[0] = LoadSave.importImg("/ResFolder/FireBall/1.png");
        animation[1] = LoadSave.importImg("/ResFolder/FireBall/2.png");
        animation[2] = LoadSave.importImg("/ResFolder/FireBall/3.png");
        animation[3] = LoadSave.importImg("/ResFolder/FireBall/4.png");
        animation[4] = LoadSave.importImg("/ResFolder/FireBall/5.png");
        animation[5] = LoadSave.importImg("/ResFolder/FireBall/6.png");
    }

    public void render(Graphics g)
    {
        g.drawImage(animation[animation_index], (int) vector2D.getVectX(), (int) vector2D.getVectY(), (int) width, (int) height, null);
        //the_seconde_box.drawCollidedBox(g);
    }

    public void update(Player hero)
    {
        updateAnimationTick();
        the_seconde_box.updateCollidedBox((int) getVector2D().getVectX(), (int) getVector2D().getVectY());
        if(go_get_him)
        {
            updatePos();
        }
        colliding_with_hero(hero);
    }


    //fireball se va misca de la dreapta la stanga
    public void updatePos()
    {
        vector2D.modifyX(-0.3f);
        if(vector2D.getVectX()<=0)
        {
            vector2D.setVectX(xinitial);
            setGo_get_him(false);
        }
    }
    private void updateAnimationTick() {

        animation_tick++;
        if (animation_tick >= animation_speed) {
            animation_tick = 0;
            animation_index++;
            if (animation_index >= 6) {
                {
                    animation_index = 0;
                }
            }
        }
    }

    //atunci cand este apelata aceasta functie, fireball se duce in directia plyerului
    public void setGo_get_him(boolean go_get_him)
    {
        this.go_get_him = go_get_him;
    }


    //daca am coliziune cu hero, hero va muri
    public void colliding_with_hero(Player hero)
    {
        if(the_seconde_box.getRect().intersects(hero.getThe_second_box().getRect()))
        {
            go_get_him = false;
            vector2D.modifyX(1500);
            hero.setDead(true);
        }
    }


}
