package entities;

import utile.CollidedBox;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonPlatform extends Entity{

    static BufferedImage []animation = new BufferedImage[2];
    CollidedBox the_second_box;

    float borderup, borderdown;
    float yinitial;
    boolean up=false, down = true;

    int stare = 0;

    public ButtonPlatform(float x, float y, int height, int width) {
        super(x, y, height, width);
        yinitial = y;
        the_second_box = new CollidedBox();
        the_second_box.init_CollidedBox((int)vector2D.getVectX(), (int)vector2D.getVectY()-20, 20, 40);

    }

    public void init()
    {
        animation[0] = LoadSave.importImg("/ResFolder/Button/red_switch_unpressed.png");
        animation[1] = LoadSave.importImg("/ResFolder/Button/green_switch_unpressed.png");
    }

    public void render(Graphics g)
    {
        g.drawImage(animation[stare], (int) vector2D.getVectX(), (int) vector2D.getVectY(), (int) width, (int) height, null);
        //the_second_box.drawCollidedBox(g);
    }

    public void update(Player hero)
    {
        collision_with_player(hero);
        the_second_box.updateCollidedBox((int)vector2D.getVectX(),(int)vector2D.getVectY()-20);
        the_second_box.updateCollidedBoxWH(20,40);
    }


    //daca playerul "a aprins" butonul, setez starea acestui pe aprins. Altfel pe neaprins.
    public void collision_with_player(Player hero)
    {
        if(the_second_box.getRect().intersects(hero.getThe_second_box().getRect())) {
            stare = 1;
        }
        else {
            stare = 0;
        }
    }

    public int getStare()
    {
        return stare;
    }

    public void setStare(int stare)
    {
        this.stare = stare;
    }


    //metoda astfel incat butonul sa nu ramana pe loc atunci cand tilemapul se misca
    public void make_it_follow()
    {
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


    public void init_borders(float borderup, float borderdown){
        this.borderdown = borderdown;
        this.borderup = borderup;
    }

    public void removeButton()
    {
        vector2D.modifyX(-0.5f);
    }
}
