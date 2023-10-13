package entities;

import utile.Constante;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static utile.Constante.FulgerStates.BOLT1;

public class Fulger extends Entity{

    int animationTick = 0;
    int animationSpeed = 100;
    int animationAction = BOLT1;

    int animationIndex = 0;
    static BufferedImage [][] animatieFulger;

    boolean CollidingEnable = true;


    private Seahorse seahorse;



    public Fulger(float x, float y, int height, int width, int fulgerType) {
        super(x, y, height, width);
        if(fulgerType<4)
        {
            animationAction  = fulgerType;
        }
        else
        {
            fulgerType = 0;
        }


    }
    public void render(Graphics g)
    {
        g.drawImage(animatieFulger[animationAction][animationIndex], (int) vector2D.getVectX(), (int) vector2D.getVectY(), (int)width, (int)height, null);
        //getHitbox().drawCollidedBox(g);
    }

    public void update()
    {
        updateAnimation();
        getHitbox().updateCollidedBox((int)getVector2D().getVectX(), (int)getVector2D().getVectY());

            if(seahorse != null)
            {
                colliding_with_seahorse();

        }

    }

    public void initFulger()
    {
        animatieFulger = new BufferedImage[4][];
        animatieFulger[0] = new BufferedImage[6];
        animatieFulger[1] = new BufferedImage[8];
        animatieFulger[2] = new BufferedImage[6];
        animatieFulger[3] = new BufferedImage[6];

        animatieFulger[0][0] = LoadSave.importImg("/bolt_animation_1/bolt_animation_1_01.png");
        animatieFulger[0][1] = LoadSave.importImg("/bolt_animation_1/bolt_animation_1_02.png");
        animatieFulger[0][2] = LoadSave.importImg("/bolt_animation_1/bolt_animation_1_03.png");
        animatieFulger[0][3] = LoadSave.importImg("/bolt_animation_1/bolt_animation_1_04.png");
        animatieFulger[0][4] = LoadSave.importImg("/bolt_animation_1/bolt_animation_1_05.png");
        animatieFulger[0][5] = LoadSave.importImg("/bolt_animation_1/bolt_animation_1_06.png");

        animatieFulger[1][0] = LoadSave.importImg("/bolt_animation_2/bolt_animation_2_01.png");
        animatieFulger[1][1] = LoadSave.importImg("/bolt_animation_2/bolt_animation_2_02.png");
        animatieFulger[1][2] = LoadSave.importImg("/bolt_animation_2/bolt_animation_2_03.png");
        animatieFulger[1][3] = LoadSave.importImg("/bolt_animation_2/bolt_animation_2_04.png");
        animatieFulger[1][4] = LoadSave.importImg("/bolt_animation_2/bolt_animation_2_05.png");
        animatieFulger[1][5] = LoadSave.importImg("/bolt_animation_2/bolt_animation_2_06.png");
        animatieFulger[1][6] = LoadSave.importImg("/bolt_animation_2/bolt_animation_2_07.png");
        animatieFulger[1][7] = LoadSave.importImg("/bolt_animation_2/bolt_animation_2_08.png");

        animatieFulger[2][0] = LoadSave.importImg("/bolt_animation_3/bolt_animation_3_01.png");
        animatieFulger[2][1] = LoadSave.importImg("/bolt_animation_3/bolt_animation_3_02.png");
        animatieFulger[2][2] = LoadSave.importImg("/bolt_animation_3/bolt_animation_3_03.png");
        animatieFulger[2][3] = LoadSave.importImg("/bolt_animation_3/bolt_animation_3_04.png");
        animatieFulger[2][4] = LoadSave.importImg("/bolt_animation_3/bolt_animation_3_05.png");
        animatieFulger[2][5] = LoadSave.importImg("/bolt_animation_3/bolt_animation_3_06.png");

        animatieFulger[3][0] = LoadSave.importImg("/bolt_animation_4/bolt_animation_4_01.png");
        animatieFulger[3][1] = LoadSave.importImg("/bolt_animation_4/bolt_animation_4_02.png");
        animatieFulger[3][2] = LoadSave.importImg("/bolt_animation_4/bolt_animation_4_03.png");
        animatieFulger[3][3] = LoadSave.importImg("/bolt_animation_4/bolt_animation_4_04.png");
        animatieFulger[3][4] = LoadSave.importImg("/bolt_animation_4/bolt_animation_4_05.png");
        animatieFulger[3][5] = LoadSave.importImg("/bolt_animation_4/bolt_animation_4_06.png");

    }


    void updateAnimation()
    {
        animationTick++;
        if(animationTick >= 30)
        {
            animationIndex++;
            if(animationIndex>=Constante.FulgerStates.getSpriteAmount(animationAction))
            {
                animationIndex = 0;
                Random rand = new Random();
                animationAction = rand.nextInt(4);
            }
            animationTick = 0;
        }
    }


    //daca am coliziune cu seahorse, scad viata acestuia
    //coliziunea o testez doar pentru prima data
    //daca deja am coliziune nu mai schimb viata seahorse ului, altfel ar scadea la infinit viata acestuia, nu o singura data
    public void colliding_with_seahorse()
    {
            if(getHitbox().getRect().intersects(seahorse.getThe_second_box().getRect())) {
                if(this.CollidingEnable == true)
                {
                    seahorse.setLife();
                }
                this.CollidingEnable = false;
            }
            else
            {
                this.CollidingEnable = true;
            }


    }

    public void setSeahorse(Seahorse seahorse)
    {
        this.seahorse = seahorse;
    }

}
