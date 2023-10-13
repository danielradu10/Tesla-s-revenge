package entities;

import utile.CollidedBox;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utile.Constante.HeroConstants.GetSpriteAmount;

public class Bullet extends Entity{

    BufferedImage []bullet_animation;

    CollidedBox the_second_collidedBox;

    int animation_tick = 0;
    int animation_speed = 10;

    int animation_index = 0;

    boolean direction = true;

    boolean hit = false;



    //constructor Bullet
    //initilizez si cel de al doilea CollidedBox
    Bullet(int x, int y, int width, int height)
    {
        super(x,y,width, height);
        the_second_collidedBox = new CollidedBox();
        the_second_collidedBox.init_CollidedBox(x+3, y+3, 12, 15);

    }

    public void init_bullet(int bulettype)
    {

        //verific ce fel de bullet este (verde -  pentru seahorse sau galben pentru hero)
        bullet_animation = new BufferedImage[5];
        if(bulettype == 1)
        {
            bullet_animation[0] = LoadSave.importImg("/Bullet/1_Bullet_Yellow_000.png");
            bullet_animation[1] = LoadSave.importImg("/Bullet/1_Bullet_Yellow_001.png");
            bullet_animation[2] = LoadSave.importImg("/Bullet/1_Bullet_Yellow_002.png");
            bullet_animation[3] = LoadSave.importImg("/Bullet/1_Bullet_Yellow_003.png");
            bullet_animation[4] = LoadSave.importImg("/Bullet/1_Bullet_Yellow_004.png");
        }
        if(bulettype == 2)
        {
            bullet_animation[0] = LoadSave.importImg("/GreenBullet/1_Bullet_Green_000.png");
            bullet_animation[1] = LoadSave.importImg("/GreenBullet/1_Bullet_Green_001.png");
            bullet_animation[2] = LoadSave.importImg("/GreenBullet/1_Bullet_Green_002.png");
            bullet_animation[3] = LoadSave.importImg("/GreenBullet/1_Bullet_Green_003.png");
            bullet_animation[4] = LoadSave.importImg("/GreenBullet/1_Bullet_Green_004.png");
        }

    }

    //render
    public void render(Graphics g)
    {
        g.drawImage(bullet_animation[animation_index], (int)vector2D.getVectX(), (int)vector2D.getVectY(), (int)width, (int)height, null);
        //the_second_collidedBox.drawCollidedBox(g);
    }


    public void update()
    {
        //vad in ce directie se trage
        if(direction)
        {
            vector2D.modifyX(1);
        }
        else
        {
            vector2D.modifyX(-1);
        }


        updateAnimationTick();
        the_second_collidedBox.updateCollidedBox((int) vector2D.getVectX(), (int)vector2D.getVectY());
        hitSomething();

    }

    public void setDirection(boolean direction)
    {
        this.direction = direction;
    }

    private void updateAnimationTick() {
        animation_tick++;
        if(animation_tick >= animation_speed)
        {
            animation_tick=0;
            animation_index++;
            if(animation_index>=4)
            {
                animation_index = 0;
            }
        }
    }

    //modific coordonatele
    public void setXY(int x, int y)
    {
        this.vector2D.setVectX(x);
        this.vector2D.setVectY(y);
    }

    //modific dimensiunile
    public void setWH(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void collidingWithMonster(Turtle turtle)
    {
        if(collided_box.getRect().intersects(turtle.getHitbox().getRect()))
        {
            System.out.println("Bulletul a atins monster");
        }

    }

    public CollidedBox getThe_second_collidedBox()
    {
        return this.the_second_collidedBox;
    }

    public void setHit(boolean hit)
    {
        this.hit = hit;
    }

    //daca hit este setat pe 1, adica daca a lovit ceva, fac bulletul foarte mic in afara ecranului si resetez hitul pe 0
    public void hitSomething()
    {
        if(hit)
        {
            this.setWH(0,0);
            hit = false;
        }
    }
}
