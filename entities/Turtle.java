package entities;

import Incercari.Physics;
import Incercari.Vector2D;
import Levels.TileMap;
import utile.Constante;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utile.Constante.HeroConstants.DEAD;


//TODO(): BUILDER
// turtleaction, life, left, right, xmax, xmin, upside, downside
public class Turtle extends Entity{

    private BufferedImage [][]animation;
    private  int animation_tick;
    private  int animation_index;
    private  int animation_speed = 15;


    private  int turtle_action;
    private  int life;
    private  boolean left, right;
    private final float xmax;
    private final float xmin;

    private float vitezaX;

    float yinitial;
    private boolean dead = false;
    private boolean upside, downside;

    int startSide=0;
    int directionSide;

    boolean fall;

    boolean initial_state=true;

    Physics p;

    boolean grounded = false;


    public Turtle(TurtleBuilder turtleBuilder) {

        super(turtleBuilder.getX(), turtleBuilder.getY(), (int)turtleBuilder.getWidth(), (int)turtleBuilder.getHeight());
        this.turtle_action = turtleBuilder.turtle_action;
        this.life = turtleBuilder.life;
        this.left = turtleBuilder.left;
        this.right= turtleBuilder.right;
        this.xmax = turtleBuilder.xmax;
        this.xmin = turtleBuilder.xmin;
        this.upside = turtleBuilder.upside;
        this.downside = turtleBuilder.downside;
        this.yinitial = this.vector2D.getVectY();
        this.vitezaX = turtleBuilder.vitezaX;

        //xmax = x + 100;
        //xmin = x + 20;
        //right = true;

    }


    public void init_turtle()
    {
        if(animation== null) {

            System.out.println("E null");
            animation = new BufferedImage[3][10];
            animation[0][0] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_000.png");
            animation[0][1] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_001.png");
            animation[0][2] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_002.png");
            animation[0][3] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_003.png");
            animation[0][4] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_004.png");
            animation[0][5] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_005.png");
            animation[0][6] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_006.png");
            animation[0][7] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_007.png");
            animation[0][8] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_008.png");
            animation[0][9] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Walk_009.png");

            animation[1][0] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Dead_000.png");
            animation[1][1] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Dead_001.png");
            animation[1][2] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Dead_002.png");
            animation[1][3] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Dead_003.png");
            animation[1][4] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Dead_004.png");
            animation[1][5] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Dead_005.png");
            animation[1][6] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Dead_006.png");
            animation[1][7] = LoadSave.importImg("/ResFolder/turtle/1_Turtle_Dead_007.png");
        }
        else {
            System.out.println("E deja initializat.");
        }

    }
    //daca ramane fara viata, turtle moare
    public void set_animation()
    {
//        if(life>0)
//        {
//            turtle_action = Constante.TurtleConstants.WALK;
//        }
//        else
//        {
//            turtle_action = Constante.TurtleConstants.DEAD;
//        }
        if(life<=0)
        {
            turtle_action = Constante.TurtleConstants.DEAD;
        }

    }
    private void updateAnimationTick() {
        if(dead!=true) {
            animation_tick++;
            if(turtle_action == Constante.TurtleConstants.DEAD)
            {
                animation_speed = 35;
            }
            if (animation_tick >= animation_speed) {
                animation_tick = 0;
                animation_index++;
                if (animation_index >= Constante.TurtleConstants.get_turtle_dimension(turtle_action)) {
                    if (turtle_action == Constante.TurtleConstants.DEAD) {
                        animation_index =  Constante.TurtleConstants.get_turtle_dimension(turtle_action)-1;
                        dead = true;
                    }
                    else {
                        animation_index = 0;
                    }
                }
            }
        }
    }

    public void render(Graphics g){

        g.drawImage(animation[turtle_action][animation_index], (int) vector2D.getVectX(), (int) vector2D.getVectY(), (int)width, (int)height, null);
        //getHitbox().drawCollidedBox(g);
    }

    public void update(ArrayList<Bullet> bullets, Player hero)
    {

        if(life>0)
        {
            if(initial_state || grounded)
            {
                update_pos();
           }
            Fall();
            //ReverseVertical();
        }


        colliding_with_hero(hero);
        set_animation();
        updateAnimationTick();
        //getHitbox().updateCollidedBox((int) vector2D.getVectX(), (int) vector2D.getVectY());
        //getHitbox().updateCollidedBoxWH(width, height);
        collidingWithBullet(bullets);
    }

    //unele turle uri vor cadea de pe un tilemap pe altul
    //de acceea am folosit si un obiect Physics
    public void Fall()
    {
        if(fall && !grounded)
        {

            initial_state = false;
            p = new Physics(0);
            p.setAccelerationAsGravity();
            vector2D.setVectY(vector2D.add(p.getPosition(0.02f)).getVectY());

            if(vector2D.getVectY()-yinitial==60 )
            {
                height=height*(-1);
                getHitbox().updateCollidedBox((int)getVector2D().getVectX(), (int)getVector2D().getVectY());
            }
            else
            {
                getHitbox().updateCollidedBox((int)getVector2D().getVectX(), (int)getVector2D().getVectY()-32);
            }
        }


    }

    //coliziunea cu tilemapurile
    public void collision_with_tilemap(TileMap tileMap)
    {
        if(getHitbox().getRect().intersects(tileMap.getThe_second_box().getRect())) {
                grounded = true;
                //daca va cadea pe un tilemap, acesta se va misca putin, pentru a da senzatia de spatiu
                tileMap.shakeit();

        }
    }

    //in update pos trebuie sa schimb directia de mers a turle ului dar si modul prin care se schimba imaginea
    public void update_pos()
    {
        if(right)
        {
            vector2D.modifyX(vitezaX);

            if(height<0)
            {
                //System.out.println("Height" + height);
                getHitbox().updateCollidedBox((int)vector2D.getVectX(), (int)getVector2D().getVectY()-32);
            }
            else
            {
                getHitbox().updateCollidedBox((int)vector2D.getVectX(), (int)getVector2D().getVectY());
            }
            //getHitbox().updateCollidedBoxWH(width, height);
            //getHitbox().reverseWidth();
            if(vector2D.getVectX()>=xmax)
            {
                right = false;
                left = true;
                width = width*(-1);


                //System.out.println("width: " + width);
                vector2D.modifyX(32);

            }

        }
        if(left)
        {
            vector2D.modifyX(-vitezaX);

            if(height<0)
            {
                getHitbox().updateCollidedBox((int)vector2D.getVectX()-32, (int)getVector2D().getVectY()-32);


            }
            else
            {
                getHitbox().updateCollidedBox((int)vector2D.getVectX()-32, (int)getVector2D().getVectY());

            }

            //getHitbox().updateCollidedBoxWH(width, height);
            if(vector2D.getVectX()<=xmin)
            {
                right = true;
                left = false;
                width = width*(-1);
                //System.out.println("width: " + width);

                vector2D.modifyX(-32);

            }

        }
    }

    //colizunea cu bullet
    public void collidingWithBullet(ArrayList<Bullet> bulletList)
    {
        for (Bullet bullet: bulletList)
        {
            if(collided_box.getRect().intersects(bullet.getThe_second_collidedBox().getRect()))
            {
                System.out.println("Bulletul a atins monster");
                bullet.setXY(0,0);
                life--;
                bullet.setHit(true);
                System.out.println(life);
            }
        }
    }

    //coliziunea cu eroul
    public void colliding_with_hero(Player hero)
    {
        if(!dead)
        {
            if(getHitbox().getRect().intersects(hero.getThe_second_box().getRect()))
            {

                vector2D.modifyX(1500);
                hero.setDead(true);
            }
        }

    }
    public void removeTurtle()
    {
        this.getVector2D().modifyX(-0.5f);
    }

    public void setFall(boolean fall)
    {
        this.fall = fall;
    }

    @Override
    public String toString() {
        String val = "Coordonatele mele maxime si minime sunt:" + xmin + " " + xmax;
        return val;
    }



    //BUILDER DESIGN PATTERN




    public static class TurtleBuilder extends Entity{
        private  int turtle_action;
        private  int life;
        private  boolean left, right;
        private float xmax;
        private float xmin;
        private float vitezaX;

        private boolean upside, downside;

        public TurtleBuilder(float x, float y, int height, int width)
        {
            super(x,y,height,width);
        }

        public TurtleBuilder setTurtleAction(int turtle_action)
        {
            if(turtle_action<0 || turtle_action>2)
            {
                this.turtle_action = 0;
            }
            else {
                this.turtle_action = turtle_action;
            }
            return this;
        }

        public TurtleBuilder setTurtleLife(int turtleLife)
        {
            if(turtleLife<1 || turtleLife>4)
            {
                this.life = 3;
            }
            else {
                this.life = turtleLife;
            }
            return this;
        }

        public TurtleBuilder setLeftRight(boolean left, boolean right)
        {
            if(left==right)
            {
                this.left = true;
                this.right = false;
            }
            else {
                this.right = right;
                this.left = left;
            }
            return this;
        }
        public TurtleBuilder setXmaxXmin(float xmax, float xmin)
        {
            this.xmax = xmax;
            this.xmin = xmin;
            return this;
        }

        public TurtleBuilder setUpDown(boolean upside, boolean downside)
        {
            if(upside == downside)
            {
                this.upside = true;
                this.downside = false;
            }
            else {
                this.downside = downside;
                this.upside = upside;
            }
            return this;
        }

        public float getX()
        {
            return super.vector2D.getVectX();
        }

        public float getY()
        {
            return super.vector2D.getVectY();
        }

        public float getWidth()
        {
            return super.width;
        }
        public float getHeight()
        {
            return super.height;
        }

        public TurtleBuilder setVitezaX(float vitezaX) {
            if(vitezaX>1)
            {
                this.vitezaX=0.1f;
            }
            else
                this.vitezaX = vitezaX;
            return this;
        }

        public Turtle build()
        {
            return new Turtle(this);
        }


    }
}


