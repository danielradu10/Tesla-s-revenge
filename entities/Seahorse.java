package entities;

import utile.CollidedBox;
import utile.Constante;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Seahorse extends Entity{

    private BufferedImage[][]animation;
    boolean dead = false;
    boolean stare;

    int dead_index = 0;
    int animation_tick, animation_index;
    int animation_speed = 25;
    int seahorse_action = Constante.SeahorseConstants.MOVE;

    int seahorse_life = 3;

    CollidedBox the_second_box;

    CollidedBox danger_zone;

    boolean danger = false;

    private ArrayList<Bullet> green_bullets = new ArrayList<>(5);

    boolean shoot_now = false;
    int startDirection = 2;
    int bullet_number = 0;

    boolean is_shooting = false;

    int shooting_time = 0;

    boolean bullet_go = false;

    boolean move = true;

    boolean goLeft = false;

    boolean prepared_to_shoot;

    boolean leftOriented = false;


    public Seahorse(float x, float y, int height, int width) {
        super(x, y, height, width);
        the_second_box = new CollidedBox();
        the_second_box.init_CollidedBox( (int) x+10 , (int) y,width-30, height-10);
        danger_zone = new CollidedBox();
        danger_zone.init_CollidedBox(850, 450, 100, 100);

    }

    public void instances_of_bullets()
    {
        Bullet b1 = new Bullet(0,0,0,0);
        green_bullets.add(b1);
        Bullet b2 = new Bullet(0,0,0,0);
        green_bullets.add(b2);
        Bullet b3 = new Bullet(0,0,0,0);
        green_bullets.add(b3);
        Bullet b4 = new Bullet(0,0,0,0);
        green_bullets.add(b4);
        Bullet b5 = new Bullet(0,0,0,0);
        green_bullets.add(b5);

        for(Bullet bullet: green_bullets)
        {
            bullet.init_bullet(2);
        }

    }
    public void init_seahorse(){
        if(animation == null)
        {
            animation= new BufferedImage[3][10];
            System.out.println("NUUUUUUUUUUUU");
        }

        animation[0][0] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Move_000.png");
        animation[0][1] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Move_001.png");
        animation[0][2] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Move_002.png");
        animation[0][3] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Move_003.png");
        animation[0][4] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Move_004.png");
        animation[0][5] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Move_005.png");



        animation[1][0] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_000.png");
        animation[1][1] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_001.png");
        animation[1][2] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_002.png");
        animation[1][3] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_003.png");
        animation[1][4] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_004.png");
        animation[1][5] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_005.png");
        animation[1][6] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_006.png");
        animation[1][7] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_007.png");
        animation[1][8] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_008.png");
        animation[1][9] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Attack_009.png");

        animation[2][0] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_000.png");
        animation[2][1] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_001.png");
        animation[2][2] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_002.png");
        animation[2][3] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_003.png");
        animation[2][4] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_004.png");
        animation[2][5] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_005.png");
        animation[2][6] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_006.png");
        animation[2][7] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_007.png");
        animation[2][8] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_008.png");
        animation[2][9] = LoadSave.importImg("/ResFolder/seahorse/1_Seahorse_Dead_009.png");

        instances_of_bullets();

    }

    //actiune seahorse
    public void setSeahorse_action()
    {
        if(seahorse_life <= 0)
        {
            seahorse_action = Constante.SeahorseConstants.DEAD;
            dead = true;

            if(!stare)
            {
                dead_index = dead_index + 1;
            }
            stare = true;
        }
        if(is_shooting == true)
        {
            seahorse_action = Constante.SeahorseConstants.ATTACK;


        }
        if(is_shooting == false && dead!= true)
        {
            seahorse_action = Constante.SeahorseConstants.MOVE;
        }
    }

    public void update(Player hero){
        updateAnimationTick();
        collidingWithBullet(hero.getBullet());
        setSeahorse_action();
        if(dead)
        {
            fall();
        }
        the_second_box.updateCollidedBox((int)getVector2D().getVectX()+10, (int) getVector2D().getVectY());

        for (Bullet bullet: green_bullets)
        {
            bullet.update();
        }

        shoot_it();
        move();
        danger_zone_Activated(hero);
        colliding_with_Hero(hero);
        Seahorse_colliding_hero(hero);
        if(dead)
        {
            shoot_now = false;
            prepared_to_shoot = false;
            danger_zone.updateCollidedBox(650, 500);
            danger = false;
        }
    }

    public void render(Graphics g){

        g.drawImage(animation[seahorse_action][animation_index], (int) vector2D.getVectX(), (int) vector2D.getVectY(), (int)width, (int)height, null);
        //getHitbox().drawCollidedBox(g);
        //the_second_box.drawCollidedBox(g);
        for (Bullet bullet: green_bullets)
        {
            bullet.render(g);
        }
        //danger_zone.drawCollidedBox(g);
    }


    private void updateAnimationTick() {
        if(dead!=true) {
            animation_tick++;
            if(seahorse_action == Constante.SeahorseConstants.DEAD)
            {
                animation_speed = 35;
            }
            if(seahorse_action == Constante.SeahorseConstants.ATTACK)
            {
                animation_speed = 20;
            }
            if (animation_tick >= animation_speed) {
                animation_tick = 0;
                animation_index++;
                if (animation_index >= Constante.SeahorseConstants.get_seahorse_dimension(seahorse_action)) {
                    if (seahorse_action == Constante.SeahorseConstants.DEAD) {
                        animation_index =  Constante.SeahorseConstants.get_seahorse_dimension(seahorse_action)-1;
                        dead = true;
                    }
                    if(seahorse_action == Constante.SeahorseConstants.ATTACK)
                    {
                        is_shooting = false;
                        bullet_go = true;
                        animation_index = 0;
                    }
                    else {
                        animation_index = 0;
                    }
                }
            }
        }
    }

    public CollidedBox getThe_second_box()
    {
        return this.the_second_box;
    }

    //metoda prin care scad viata
    public void setLife()
    {
        seahorse_life--;
        System.out.println("Viata seahorse ului: " + seahorse_life);
    }

    //atunci cand moare seahorse, va cadea
    public void fall()
    {
        this.getVector2D().modifyY(0.4f);
    }

    //verific coliziunea cu bulleturile playerului
    public void collidingWithBullet(ArrayList<Bullet> bulletList)
    {
        for (Bullet bullet: bulletList)
        {
            if(the_second_box.getRect().intersects(bullet.getThe_second_collidedBox().getRect()))
            {
                System.out.println("Bulletul a atins seahorse");
                bullet.setXY(0,0);
                seahorse_life--;
                bullet.setHit(true);
                System.out.println(seahorse_life);
            }
        }
    }

    //metoda prin care impusca
    public void shoot_it()
    {
        if(!move)
        {
            if(shoot_now)
            {
                is_shooting = true;

            }
            shooting_time++;

            if(shooting_time <400)
            {
                shoot_now = false;
            }
            else
            {
                shooting_time = 0;
                shoot_now = true;
            }

            if(bullet_go)
            {
                if(startDirection==2)
                {

                    green_bullets.get(bullet_number).setDirection(true);
                    green_bullets.get(bullet_number).setXY((int) vector2D.getVectX() + 40, (int) vector2D.getVectY() + 10);

                }
                if(startDirection==1)
                {

                    green_bullets.get(bullet_number).setDirection(false);
                    green_bullets.get(bullet_number).setXY((int) vector2D.getVectX() - 47, (int) vector2D.getVectY() + 10);

                }

                green_bullets.get(bullet_number).setWH(20, 20);
                bullet_number++;
                if(bullet_number==5)
                {
                    bullet_number = 0;
                }
                bullet_go = false;

            }

        }

    }

    //seahorse ul se misca initial de la stanga la dreapta
    public void move()
    {
        if(move == true)
        {
            if(this.getVector2D().getVectX() >= 750)
            {
                goLeft = true;
                width = width*(-1);
                leftOriented = true;
            }
            if(this.getVector2D().getVectX()<=98)
            {
                goLeft = false;
                width = width*(-1);
                leftOriented = false;
            }
            if(goLeft)
            {
                getThe_second_box().updateCollidedBox((int)vector2D.getVectX()-42, (int)getVector2D().getVectY());
                this.getVector2D().modifyX(-0.2f);
            }
            else
            {
                getThe_second_box().updateCollidedBox((int)vector2D.getVectX()+10, (int)getVector2D().getVectY());
                this.getVector2D().modifyX(0.2f);
            }
        }

        //daca danger a fost activat, adica playerul se afla in patratul danger zone, atunci seahorse placa la punctul de tragere

        if(danger)
        {

            if(getVector2D().getVectX() < 399)
            {
                goLeft = false;
            }
            else if(getVector2D().getVectX() > 402)
            {
                goLeft = true;
            }
            //daca a ajuns la punctul de tragere, incepe sa impuste
            else
            {
                //verific sa nu fie cu spatele la hero
                if(leftOriented == true)
                {
                    width =width*-1;
                    leftOriented = false;
                }
                prepared_to_shoot = true;
                move = false;
            }

//                System.out.println("prepared: " + prepared_to_shoot);
//                System.out.println("golfet: " + goLeft);
                if(goLeft && !prepared_to_shoot)
                {
                    if(dead_index>=1)
                    {
                        System.out.println("Incerca sa ajung");
                    }
//                    System.out.println("Am intrat o data aici");
                    getThe_second_box().updateCollidedBox((int)vector2D.getVectX()-42, (int)getVector2D().getVectY());
                    this.getVector2D().modifyX(-0.5f);
                }
                if(!goLeft && !prepared_to_shoot)
                {
                    if(dead_index>=1)
                    {
                        System.out.println("Incerca sa ajung, merg inainte");
                    }
//                    System.out.println("Am intrat si aici");
                    getThe_second_box().updateCollidedBox((int)vector2D.getVectX()+10, (int)getVector2D().getVectY());
                    this.getVector2D().modifyX(0.4f);
                }


        }


    }



    public void danger_zone_Activated(Player hero)
    {
        //daca seahorse a murit prima data, fac respawn
        if(dead_index == 1)
        {

            if(dead && getVector2D().getVectY()>700)
            {
                //daca hero se afla in danger zone(care acum este la alte coordonate)
                if(danger_zone.getRect().intersects(hero.getThe_second_box().getRect()))
                {
                    //mai primeste 3 vieti
                    seahorse_life = seahorse_life + 3;
                    dead = false;
                    stare = false;
                    getVector2D().setVectX(-50);
                    getVector2D().setVectY(470);
                    danger = true;
                    System.out.println("respawn");
                }
            }
        }
        else
        {
            //setez miscarea normala pe false si activez danger
            if(danger_zone.getRect().intersects(hero.getThe_second_box().getRect()))
            {
                danger = true;
                move = false;
            }
        }
    }

    //coliziunea cu BULLETURILE  seahorseului
    public void colliding_with_Hero(Player hero)
    {
        for (Bullet bullet: green_bullets)
        {
            if(hero.getThe_second_box().getRect().intersects(bullet.getThe_second_collidedBox().getRect()))
            {
                System.out.println("Bulletul a atins monster");
                bullet.setXY(0,0);
                hero.life--;
                bullet.setHit(true);
                System.out.println(hero.life);
            }
        }
    }

    //la coliziunea cu eroul, eroul moare
    public void Seahorse_colliding_hero(Player hero)
    {
        if(getThe_second_box().getRect().intersects(hero.getThe_second_box().getRect()))
        {
            hero.life = hero.life -3;
        }
    }
}
