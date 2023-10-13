package entities;

import Incercari.Physics;
import Incercari.Vector2D;
import Levels.TileMap;
import utile.CollidedBox;
import utile.Constante;
import utile.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static utile.Constante.Directions.*;
import static utile.Constante.Directions.DOWN;
import static utile.Constante.HeroConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animation;

    CollidedBox the_second_box;


    private int aniTick, aniIndex, aniSpeed = 17;

    int life = 3;


    private int player_action = IDLE;

    private int startDirection = 2;

    private int DIRECTION = 2;

    private int player_dir = -1;

    private boolean moving = false;
    private boolean sliding = false;

    private boolean shoot = false;

    boolean isShooting  = false;
    private boolean shoot_now = false;
    private boolean shoot_ani = false;

    private boolean left, right, up, down, preparing;
    private float playerspeed = 1.0f;

    private boolean grounded = false;

    private boolean dead= false;

    private boolean  jumping = false;
    private boolean wasJumping = false;

    private boolean blockJumping = false;

    private boolean blockShooting = true;

    boolean auto_up = false, auto_down = false;

    private ArrayList<Bullet> bullets = new ArrayList<>(5);

    int bullet_number;
    Physics p;

    float deltaTimp = 0.02f;
    float deltaJumping = 0.039f;

    int hintsWon = 0;
    boolean gameover = false;

    boolean winner = false;

    int level = 1;

    int contorBullets = 0;

    int maxBullets = 100;

    Vector2D forceX = new Vector2D();
    Vector2D forceY = new Vector2D();
    public Player(float x, float y, int width, int height)
    {

        super(x,y, width, height);
        the_second_box = new CollidedBox();
        the_second_box.init_CollidedBox((int) x + 20, (int) y, width-28, height);
        p = new Physics(1);

        p.addForce(forceX);
        p.addForce(forceY);

        instances_of_bullets();
        loadAnimation();

        level = 1;


        if(level == 1)
        {
            maxBullets = 100;
            contorBullets = 0;
        }
        if(level ==2)
        {
            maxBullets = 5;
        }


    }

    //instanteiz bulletsurile
    public void instances_of_bullets()
    {
        Bullet b1 = new Bullet(0,0,0,0);
        bullets.add(b1);
        Bullet b2 = new Bullet(0,0,0,0);
        bullets.add(b2);
        Bullet b3 = new Bullet(0,0,0,0);
        bullets.add(b3);
        Bullet b4 = new Bullet(0,0,0,0);
        bullets.add(b4);
        Bullet b5 = new Bullet(0,0,0,0);
        bullets.add(b5);

    }
    public void update(){
        updatePos();
        getHitbox().updateCollidedBox((int)this.vector2D.getVectX(), (int)this.vector2D.getVectY());
        updateAnimationTick();
        setAnimation();
        walk_of_fame();



        for (Bullet bullet: bullets)
        {
            bullet.update();
        }
        if (!isShooting)
        {
            shoot_it();
        }

        //update urile pentru cel de al doilea Collided box astfel incat sa nu se modifice
        if(jumping|| !grounded)
        {
            the_second_box.updateCollidedBox((int) vector2D.getVectX() + 5, (int) vector2D.getVectY());
        }
        if(startDirection==1)
        {

            //daca fac slide, collided boxul se va duce si el in jos
            if(sliding)
            {
                the_second_box.updateCollidedBox((int)vector2D.getVectX()-40, (int)getVector2D().getVectY()+15);
            }
            else
            {
                the_second_box.updateCollidedBox((int)vector2D.getVectX()-40, (int)getVector2D().getVectY());
            }
        }
        if(startDirection==2)
        {
            if(sliding)
            {
                the_second_box.updateCollidedBox((int)vector2D.getVectX()+5, (int)getVector2D().getVectY()+15);
            }
            else
            {
                the_second_box.updateCollidedBox((int)vector2D.getVectX()+5, (int)getVector2D().getVectY());
            }

        }


        //daca nu mai am viata, dead = true
        if(life<=0)
        {
            dead = true;
        }


    }

    public void render(Graphics g){

        //doar daca nu am pierdut mai desenez playerul
        if(!gameover)
            g.drawImage(animation[player_action][aniIndex], (int) vector2D.getVectX(), (int) vector2D.getVectY(), (int)width, (int)height, null);
        //the_second_box.drawCollidedBox(g);
        for (Bullet bullet: bullets)
        {
            bullet.render(g);
        }
    }

    public void shoot_it()
    {
        //daca mai am bulleturi, impusc
        if(contorBullets< maxBullets) {

            if (shoot_now) {


                isShooting = true;

                if (startDirection == 2) {

                    bullets.get(bullet_number).setDirection(true);
                    bullets.get(bullet_number).setXY((int) vector2D.getVectX() + 45, (int) vector2D.getVectY() + 20);

                }
                if (startDirection == 1) {

                    bullets.get(bullet_number).setDirection(false);
                    bullets.get(bullet_number).setXY((int) vector2D.getVectX() - 52, (int) vector2D.getVectY() + 20);

                }

                bullets.get(bullet_number).setWH(20, 20);
                bullet_number++;
                if(level == 2)
                {
                    contorBullets++;
                }

                if (bullet_number == 5) {
                    bullet_number = 0;
                }
                //shoot_now = false;
            }
        }
    }


    private void loadAnimation() {
        animation = new BufferedImage[13][20];
        animation[0] =  new BufferedImage[10]; //idle
        animation[1] =  new BufferedImage[10];  //idle aim
        animation[2] =  new BufferedImage[20];  //jump
        animation[3] =  new BufferedImage[20];  //jump aim
        animation[4] =  new BufferedImage[8];  //run
        animation[5] =  new BufferedImage[8];   //run aim
        animation[6] =  new BufferedImage[8];   //shoot
        animation[7] =  new BufferedImage[5];   //slide
        animation[8] =  new BufferedImage[10];  //walk
        animation[9] =  new BufferedImage[10];  //walk aim
        animation[10] =  new BufferedImage[10];  //hurt
        animation[11] =  new BufferedImage[10];  //dead
        animation[12] =  new BufferedImage[10];  //victory

        animation[0][0] = LoadSave.importImg("/1_Hero_Idle_Aim_000.png");
        animation[0][1] = LoadSave.importImg("/1_Hero_Idle_Aim_001.png");
        animation[0][2] = LoadSave.importImg("/1_Hero_Idle_Aim_002.png");
        animation[0][3] = LoadSave.importImg("/1_Hero_Idle_Aim_003.png");
        animation[0][4] = LoadSave.importImg("/1_Hero_Idle_Aim_004.png");
        animation[0][5] = LoadSave.importImg("/1_Hero_Idle_Aim_005.png");
        animation[0][6] = LoadSave.importImg("/1_Hero_Idle_Aim_006.png");
        animation[0][7] = LoadSave.importImg("/1_Hero_Idle_Aim_007.png");
        animation[0][8] = LoadSave.importImg("/1_Hero_Idle_Aim_008.png");
        animation[0][9] = LoadSave.importImg("/1_Hero_Idle_Aim_009.png");

        animation[2][0] = LoadSave.importImg("/1_Hero_Jump_000.png");
        animation[2][1] = LoadSave.importImg("/1_Hero_Jump_001.png");
        animation[2][2] = LoadSave.importImg("/1_Hero_Jump_002.png");
        animation[2][3] = LoadSave.importImg("/1_Hero_Jump_003.png");
        animation[2][4] = LoadSave.importImg("/1_Hero_Jump_004.png");
        animation[2][5] = LoadSave.importImg("/1_Hero_Jump_005.png");
        animation[2][6] = LoadSave.importImg("/1_Hero_Jump_006.png");
        animation[2][7] = LoadSave.importImg("/1_Hero_Jump_007.png");
        animation[2][8] = LoadSave.importImg("/1_Hero_Jump_008.png");
        animation[2][9] = LoadSave.importImg("/1_Hero_Jump_009.png");
        animation[2][10] = LoadSave.importImg("/1_Hero_Jump_010.png");
        animation[2][11] = LoadSave.importImg("/1_Hero_Jump_011.png");
        animation[2][12] = LoadSave.importImg("/1_Hero_Jump_012.png");
        animation[2][13] = LoadSave.importImg("/1_Hero_Jump_013.png");
        animation[2][14] = LoadSave.importImg("/1_Hero_Jump_014.png");
        animation[2][15] = LoadSave.importImg("/1_Hero_Jump_015.png");
        animation[2][16] = LoadSave.importImg("/1_Hero_Jump_016.png");
        animation[2][17] = LoadSave.importImg("/1_Hero_Jump_017.png");
        animation[2][18] = LoadSave.importImg("/1_Hero_Jump_018.png");
        animation[2][19] = LoadSave.importImg("/1_Hero_Jump_019.png");


        animation[4][0] = LoadSave.importImg("/1_Hero_Run_000.png");
        animation[4][1] = LoadSave.importImg("/1_Hero_Run_001.png");
        animation[4][2] = LoadSave.importImg("/1_Hero_Run_002.png");
        animation[4][3] = LoadSave.importImg("/1_Hero_Run_003.png");
        animation[4][4] = LoadSave.importImg("/1_Hero_Run_004.png");
        animation[4][5] = LoadSave.importImg("/1_Hero_Run_005.png");
        animation[4][6] = LoadSave.importImg("/1_Hero_Run_006.png");
        animation[4][7] = LoadSave.importImg("/1_Hero_Run_007.png");

        animation[5][0] = LoadSave.importImg("/1_Hero_Run_Aim_000.png");
        animation[5][1] = LoadSave.importImg("/1_Hero_Run_Aim_001.png");
        animation[5][2] = LoadSave.importImg("/1_Hero_Run_Aim_002.png");
        animation[5][3] = LoadSave.importImg("/1_Hero_Run_Aim_003.png");
        animation[5][4] = LoadSave.importImg("/1_Hero_Run_Aim_004.png");
        animation[5][5] = LoadSave.importImg("/1_Hero_Run_Aim_005.png");
        animation[5][6] = LoadSave.importImg("/1_Hero_Run_Aim_006.png");
        animation[5][7] = LoadSave.importImg("/1_Hero_Run_Aim_007.png");

        animation[6][0] = LoadSave.importImg("/1_Hero_Shoot_000.png");
        animation[6][1] = LoadSave.importImg("/1_Hero_Shoot_001.png");
        animation[6][2] = LoadSave.importImg("/1_Hero_Shoot_002.png");
        animation[6][3] = LoadSave.importImg("/1_Hero_Shoot_003.png");
        animation[6][4] = LoadSave.importImg("/1_Hero_Shoot_004.png");
        animation[6][5] = LoadSave.importImg("/1_Hero_Shoot_005.png");
        animation[6][6] = LoadSave.importImg("/1_Hero_Shoot_006.png");
        animation[6][7] = LoadSave.importImg("/1_Hero_Shoot_007.png");

        animation[7][0] = LoadSave.importImg("/1_Hero_Slide_000.png");
        animation[7][1] = LoadSave.importImg("/1_Hero_Slide_001.png");
        animation[7][2] = LoadSave.importImg("/1_Hero_Slide_002.png");
        animation[7][3] = LoadSave.importImg("/1_Hero_Slide_003.png");
        animation[7][4] = LoadSave.importImg("/1_Hero_Slide_004.png");


        animation[8][0] = LoadSave.importImg("/1_Hero_Walk_000.png");
        animation[8][1] = LoadSave.importImg("/1_Hero_Walk_001.png");
        animation[8][2] = LoadSave.importImg("/1_Hero_Walk_002.png");
        animation[8][3] = LoadSave.importImg("/1_Hero_Walk_003.png");
        animation[8][4] = LoadSave.importImg("/1_Hero_Walk_004.png");
        animation[8][5] = LoadSave.importImg("/1_Hero_Walk_005.png");
        animation[8][6] = LoadSave.importImg("/1_Hero_Walk_006.png");
        animation[8][7] = LoadSave.importImg("/1_Hero_Walk_007.png");
        animation[8][8] = LoadSave.importImg("/1_Hero_Walk_008.png");
        animation[8][9] = LoadSave.importImg("/1_Hero_Walk_009.png");


        animation[11][0] = LoadSave.importImg("/1_Hero_Dead_000.png");
        animation[11][1] = LoadSave.importImg("/1_Hero_Dead_001.png");
        animation[11][2] = LoadSave.importImg("/1_Hero_Dead_002.png");
        animation[11][3] = LoadSave.importImg("/1_Hero_Dead_003.png");
        animation[11][4] = LoadSave.importImg("/1_Hero_Dead_004.png");
        animation[11][5] = LoadSave.importImg("/1_Hero_Dead_005.png");
        animation[11][6] = LoadSave.importImg("/1_Hero_Dead_006.png");
        animation[11][7] = LoadSave.importImg("/1_Hero_Dead_007.png");
        animation[11][8] = LoadSave.importImg("/1_Hero_Dead_008.png");
        animation[11][9] = LoadSave.importImg("/1_Hero_Dead_009.png");

        animation[12][0] = LoadSave.importImg("/1_000.png");
        animation[12][1] = LoadSave.importImg("/1_001.png");
        animation[12][2] = LoadSave.importImg("/1_002.png");
        animation[12][3] = LoadSave.importImg("/1_003.png");
        animation[12][4] = LoadSave.importImg("/1_004.png");
        animation[12][5] = LoadSave.importImg("/1_005.png");
        animation[12][6] = LoadSave.importImg("/1_006.png");
        animation[12][7] = LoadSave.importImg("/1_007.png");
        animation[12][8] = LoadSave.importImg("/1_008.png");
        animation[12][9] = LoadSave.importImg("/1_009.png");


        for(Bullet bullet: bullets)
        {
            bullet.init_bullet(1);
        }
    }



    //setarea starilor pentru animatie
    public void setAnimation(){
        int startAni = player_action;
        if(moving && deltaTimp <= 0.03400)
            player_action = WALK;
        else if(moving && deltaTimp > 0.03400)
        {
            player_action = RUN;
        }
        if(!moving && !dead &&!winner)
        {
            player_action = IDLE;
            deltaTimp = 0.02f;
        }

        if(preparing || jumping || wasJumping)
        {
            player_action = JUMP;
        }

        if(grounded && wasJumping)
        {
            player_action = IDLE;
            wasJumping = false;
        }

        if(sliding)
        {
            player_action = SLIDE;
        }
        if(dead)
        {
            player_action = DEAD;
        }
        if(winner)
        {
            player_action = VICTORY;
        }




        if(shoot && moving)
        {
            player_action = RUN_AIM;
        }
        if(shoot_ani)
        {
            player_action = SHOOT;
        }
        if(startAni != player_action)
        {
            resetAniTick();
        }

    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    //setarea movementului playerului
    private void updatePos() {


        moving = false;

        startDirection = DIRECTION;


        //daca nu sunt in aer pot sa ma misc in toate directiile inclusiv sa sar
        if(grounded && !jumping) {



            blockJumping = false;
            jumping = false;

            if (left && !right) {
                forceX.setVectX(-400);
                forceX.setVectY(0);
                p.calculateAcceleration();
                vector2D.setVectX(vector2D.add(p.getPosition(deltaTimp)).getVectX());
                moving = true;
                deltaTimp = deltaTimp + 0.00015f;
                if(deltaTimp>=0.039)
                {
                    deltaTimp = 0.039f;
                }
                setDIRECTION(1);


            } else if (right && !left) {

                forceX.setVectX(400);
                forceX.setVectY(0);
                p.calculateAcceleration();
                vector2D.setVectX(vector2D.add(p.getPosition(deltaTimp)).getVectX());
                moving = true;
                deltaTimp = deltaTimp + 0.00015f;
                if(deltaTimp>=0.039)
                {
                    deltaTimp = 0.039f;
                }
                System.out.println(deltaTimp);
                setDIRECTION(2);

            }
            if(!down)
            {
                sliding = false;
            }
            if (up && !down) {
                jumping = true;
                moving = true;
            }
            else if(!up && down)
            {
                sliding = true;
                moving = false;
                jumping = false;
            }



        }
        //daca sunt in aer si nu sar setez gravitatia astfel incat sa cobor din nou
        if (!grounded && !jumping && !wasJumping)
        {
            forceX.setVectX(0);
            forceX.setVectY(0);
            p.setAccelerationAsGravity();

            vector2D.setVectY(vector2D.add(p.getPosition(deltaTimp)).getVectY());

        }
        //daca sun in mijlocul sariturii cad dar ma mut si intr o parte
        //start direction= 2 in partea dreapta
        if (!grounded && !jumping && wasJumping && startDirection==2)
        {
            forceY.setVectX(0);
            forceY.setVectY(400);
            forceX.setVectX(400);
            forceX.setVectY(0);

            p.calculateAcceleration();
            vector2D= vector2D.add(p.getPosition(deltaTimp));
            deltaTimp = deltaTimp + 0.00025f;
            moving = true;
            if(deltaTimp>=0.035)
            {
                deltaTimp = 0.035f;
                wasJumping = false;
            }
        }

        //acelasi lucru dar pentru partea stanga
        if (!grounded && !jumping && wasJumping && startDirection==1)
        {
            forceY.setVectX(0);
            forceY.setVectY(400);
            forceX.setVectX(-400);
            forceX.setVectY(0);

            p.calculateAcceleration();
            vector2D= vector2D.add(p.getPosition(deltaTimp));
            deltaTimp = deltaTimp + 0.00025f;
            moving = true;
            if(deltaTimp>=0.035)
            {
                deltaTimp = 0.035f;
                wasJumping = false;
            }


        }

        //daca sar, setez atat o forta la dreapta cat si o forta care sa ma traga in sus
        if (jumping && startDirection == 2)
        {
            forceX.setVectX(400);
            forceX.setVectY(0);
            forceY.setVectX(0);
            forceY.setVectY(-400);
            p.calculateAcceleration();
            vector2D.setVectY(vector2D.add(p.getPosition(deltaJumping)).getVectY());
            vector2D.setVectX(vector2D.add(p.getPosition(deltaJumping)).getVectX());
            deltaJumping = deltaJumping - 0.00015f;
            if(deltaJumping<=0.025f)
            {
                jumping = false;
                deltaJumping = 0.039f;
                wasJumping = true;
            }

        }

        if (jumping && startDirection == 1)
        {
            forceX.setVectX(-400);
            forceX.setVectY(0);
            forceY.setVectX(0);
            forceY.setVectY(-400);
            p.calculateAcceleration();
            vector2D.setVectY(vector2D.add(p.getPosition(deltaJumping)).getVectY());
            vector2D.setVectX(vector2D.add(p.getPosition(deltaJumping)).getVectX());
            deltaJumping = deltaJumping - 0.00015f;
            if(deltaJumping<=0.025f)
            {
                jumping = false;
                deltaJumping = 0.039f;
                wasJumping = true;
            }

        }

        //daca schimb directia inversez imaginea
        if(startDirection!=DIRECTION)
        {
            width = width*(-1);
            if(startDirection==1)
            {

                vector2D.modifyX(-32);
                //the_second_box.updateCollidedBox((int)vector2D.getVectX()-27, (int)getVector2D().getVectY());
            }
            else
            {
                vector2D.modifyX(32);
                //the_second_box.updateCollidedBox((int)vector2D.getVectX()-27, (int)getVector2D().getVectY());
            }

        }

    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed)
        {
            aniTick=0;
            aniIndex++;
            if(preparing)
            {
                blockJumping = true;
                if(aniIndex>=6)
                {
                   preparing = false;
                   jumping = true;
                }
            }

            else {
                if(aniIndex>=GetSpriteAmount(player_action))
                {
                    aniIndex = 0;
                    shoot = false;
                    shoot_ani = false;
                    isShooting = false;
                    shoot_now = false;
                    if(!grounded)
                    {
                        System.out.println("aterizez");
                        aniIndex = GetSpriteAmount(player_action)-3;
                    }
                    if(dead)
                    {
                        gameover = true;
                    }
                }
            }

        }
    }

    //metoda prin care urmaresc tilemapul
    public void make_it_hard(TileMap tilemap)
    {


        if(tilemap.getup())
        {
            this.vector2D.modifyY(-0.5f);
        }
        if(tilemap.getdown() && grounded)
        {
            this.vector2D.modifyY(0.5f);
        }
    }

    public void setNewPosition(float xPos, float yPos)
    {
        this.vector2D.setVectX(xPos);
        this.vector2D.setVectY(yPos);
    }

    public void resetDirBooleans(){
        left = false;
        right = false;
        down = false;
        up = false;
    }

    //metoda pe care o urmaresc atunci cand castig
    public void walk_of_fame()
    {
        if(winner) {
            if (this.getVector2D().getVectX() < 1070) {

                this.getVector2D().modifyX(1);
            }
        }
    }





    public boolean getblockJumping() {
        return blockJumping;
    }
    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isPreparing() {
        return preparing;
    }

    public void setPreparing(boolean preparing) {
        this.preparing = preparing;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setShoot(boolean shoot){
        this.shoot = shoot;
    }

    public void setGrounded(boolean grounded)
    {
        this.grounded = grounded;
    }
    public boolean getGrounded()
    {
        return this.grounded;
    }

    public void set_shoot_now(boolean shoot_now)
    {
        this.shoot_now = shoot_now;
    }

    public void set_shoot_ani(boolean shoot_ani)
    {
        this.shoot_ani = shoot_ani;
    }

    public CollidedBox getThe_second_box()
    {
        return the_second_box;
    }

    public void setDIRECTION(int DIRECTION) {
        this.DIRECTION = DIRECTION;
    }

    public void setHintsWon(int hintsWon) {
        this.hintsWon = hintsWon;
    }

    public int getHintsWon() {
        return hintsWon;
    }

    public void setDead(boolean dead){
        this.dead = dead;
    }

    public boolean getDead()
    {
        return dead;
    }

    public void setWinner(boolean winner)
    {
        this.winner = winner;
    }

    public ArrayList <Bullet> getBullet(){
        return this.bullets;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxBullets(int maxBullets)
    {
        this.maxBullets = maxBullets;

    }

    public int getRemainedBullets()
    {
        return this.maxBullets - this.contorBullets;
    }
}
