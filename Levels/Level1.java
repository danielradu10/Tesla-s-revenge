package Levels;

import entities.*;
import utile.Constante;
import utile.LoadSave;

import javax.print.Doc;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level1 {

    private BufferedImage back_ground;

    private boolean start_removing_all;
    private Document doc;

    private Document doc2;

    private Document doc3;

    private Document last_document;

    private boolean moveBackground;


    int contor = 0;



    Random rand = new Random();
    private boolean player_grounded = false;

    TileMap final_tilemap;
    private List<TileMap> tile_maps = new ArrayList<>(5);

    private List<Meteors> moving_meteors = new ArrayList<>(10);

    private List<Meteors> static_meteors = new ArrayList<>(5);

    private List<Turtle> turtles = new ArrayList<>(5);
    float x1 = 0 , y1 =0 , x2 = 1275, y2 = 0; //coordonatele pentru efectul de endless
    float x3=0, y3=-720;

    ButtonPlatform b1 ;

    ButtonPlatform b2;

    boolean moveItUp = false;
    Light light_bulb1;
    Light light_bulb2;
    Light light_bulb3;
    FireBall fireBall1;
    FireBall fireBall2;
    FireBall fireBall3;




    public Level1(){
        this.init_Level1_bg();
        this.init_meteors();
        this.init_tilemaps();

        doc2 = new Document(1150 ,100, 16, 48);
        doc2.init_document();

        this.init_turtles();



        light_bulb1 = new Light(920, 328, 32, 32);
        light_bulb1.init();

        fireBall1 = new FireBall(1300, 270, 32, 74);
        fireBall1.init();


        light_bulb2 = new Light(1050, 105, 32, 32);
        light_bulb2.init();

        fireBall2= new FireBall(1360, 60, 32, 74);
        fireBall2.init();

        light_bulb3 = new Light(770, 563, 32, 32);
        light_bulb3.init();

        fireBall3 = new FireBall(1400, 490, 32, 74);
        fireBall3.init();

        doc = new Document(1040, 525, 16, 48);
        this.doc.init_document();

        doc3 = new Document(1154, 310, 16,48);
        doc3.init_document();




        last_document = new Document(-20,-20,16,48);
        last_document.init_document();


        b1 = new ButtonPlatform(140, 472, 20, 20);
        b1.init();

        b2 = new ButtonPlatform(670,418, 20,20);
        b2.init();
    }


    public void init_tilemaps(){
        TileMap  t1 = new TileMap(200, 200 , 64, 128, 1);
        t1.init_tile_map();
        tile_maps.add(t1);
        TileMap  t2 = new TileMap(-300, -300 , 64, 128, 1);
        t2.init_tile_map();
        tile_maps.add(t2);
        TileMap  t3 = new TileMap(496, 100 , 64, 128, 1);
        t3.init_tile_map();
        tile_maps.add(t3);
        TileMap  t4 = new TileMap(600, -200 , 64, 128, 1);
        t4.init_tile_map();
        tile_maps.add(t4);
        TileMap  t5 = new TileMap(600, 400 , 64, 128, 1);
        t5.init_tile_map();
        tile_maps.add(t5);
        TileMap  t6=new TileMap(1000, 525 , 64, 128, 2);
        t6.init_tile_map();
        tile_maps.add(t6);
        TileMap  t7 = new TileMap(100, 450 , 64, 128, 0);
        t7.init_tile_map();
        tile_maps.add(t7);

        TileMap  t8 = new TileMap(475, 650 , 64, 128, 0);
        t8.init_tile_map();
        tile_maps.add(t8);

        TileMap  t9 = new TileMap(50, 150 , 64, 128, 1);
        t9.init_tile_map();
        tile_maps.add(t9);




//        TileMap  t12 = new TileMap(450, 500 , 64, 128, 1);
//        t12.init_tile_map();
//        tile_maps.add(t12);

        TileMap  t13 = new TileMap(600, 600 , 64, 128, 1);
        t13.init_tile_map();
        tile_maps.add(t13);

        TileMap  t14 = new TileMap(725, 575 , 64, 128, 1);
        t14.init_tile_map();
        tile_maps.add(t14);

        TileMap  t15 = new TileMap(850, 550 , 64, 128, 1);
        t15.init_tile_map();
        tile_maps.add(t15);

//        TileMap  t16 = new TileMap(610, 100 , 64, 128, 2);
//        t16.init_tile_map();
//        tile_maps.add(t16);
        TileMap  t17 = new TileMap(740, 120 , 64, 128, 2);
        t17.init_tile_map();
        tile_maps.add(t17);

        TileMap  t18 = new TileMap(870, 100 , 64, 128, 2);
        t18.init_tile_map();
        tile_maps.add(t18);

        TileMap  t19 = new TileMap(1000, 120 , 64, 128, 2);
        t19.init_tile_map();
        tile_maps.add(t19);

        TileMap  t20 = new TileMap(1114, 100 , 64, 128, 1);
        t20.init_tile_map();
        tile_maps.add(t20);
//
//        TileMap  t21 = new TileMap(610, 300 , 64, 128, 2);
//        t21.init_tile_map();
//        tile_maps.add(t21);
        TileMap  t22 = new TileMap(740, 300 , 64, 128, 2);
        t22.init_tile_map();
        tile_maps.add(t22);

        TileMap  t23 = new TileMap(870, 340 , 64, 128, 2);
        t23.init_tile_map();
        tile_maps.add(t23);

        TileMap  t24 = new TileMap(1000, 300 , 64, 128, 2);
        t24.init_tile_map();
        tile_maps.add(t24);

        TileMap  t25 = new TileMap(1114, 330 , 64, 128, 1);
        t25.init_tile_map();
        tile_maps.add(t25);

    }

    public void init_turtles(){
        Turtle turtle_enemy = new Turtle.TurtleBuilder(850, 540, 32, 32).setTurtleAction(Constante.TurtleConstants.WALK).setTurtleLife(3).setLeftRight(false, true).setXmaxXmin(950,860).setVitezaX(0.15f).build();
        turtle_enemy.init_turtle();
        turtles.add(turtle_enemy);
        Turtle turtle_enemy2 =new Turtle.TurtleBuilder(740, 187, 32, -32).setTurtleAction(Constante.TurtleConstants.WALK).setTurtleLife(3).setLeftRight(false, true).setUpDown(true, false).setXmaxXmin(840,760).setVitezaX(0.1f).build();
        turtle_enemy2.init_turtle();
        turtles.add(turtle_enemy2);

        Turtle turtle_enemy3 =new Turtle.TurtleBuilder(1000, 187, 32, -32).setTurtleAction(Constante.TurtleConstants.WALK).setTurtleLife(3).setLeftRight(false, true).setUpDown(true, false).setXmaxXmin(1040,1020).setVitezaX(0.1f).build();
        turtle_enemy3.init_turtle();
        turtles.add(turtle_enemy3);

        Turtle turtle_enemy4 =new Turtle.TurtleBuilder(870, 90, 32, 32).setTurtleAction(Constante.TurtleConstants.WALK).setTurtleLife(3).setLeftRight(false, true).setUpDown(true, false).setXmaxXmin(930,880).setVitezaX(0.2f).build();
        turtle_enemy4.init_turtle();
        turtles.add(turtle_enemy4);

        Turtle turtle_enemy5 =new Turtle.TurtleBuilder(1120, 90, 32, 32).setTurtleAction(Constante.TurtleConstants.WALK).setTurtleLife(3).setLeftRight(false, true).setUpDown(true, false).setXmaxXmin(1180,1130).setVitezaX(0.3f).build();
        turtle_enemy5.init_turtle();
        turtles.add(turtle_enemy5);
    }
    public void init_meteors(){


        Meteors met1 = new Meteors(1280, -300, 32, 32);
        moving_meteors.add(met1);
        Meteors met2 = new Meteors(1350, -30, 32, 32);
        moving_meteors.add(met2);
        Meteors met3 = new Meteors(1200, -150, 32, 32);
        moving_meteors.add(met3);
        Meteors met4 = new Meteors(1100, -450, 32, 32);
        moving_meteors.add(met4);
        Meteors met5 = new Meteors(1000, -30, 32, 32);
        moving_meteors.add(met5);
        Meteors met6 = new Meteors(1480, -30, 32, 32);
        moving_meteors.add(met6);
        Meteors met7 = new Meteors(1290, -250, 32, 32);
        moving_meteors.add(met7);
        Meteors met8 = new Meteors(1000, -550, 32, 32);
        moving_meteors.add(met8);
        Meteors met9 = new Meteors(1390, -650, 32, 32);
        moving_meteors.add(met9);
        Meteors met10 = new Meteors(1150, -50, 32, 32);
        moving_meteors.add(met10);
//
//        Meteors met11 = new Meteors(1280, -600, 32, 32);
//        moving_meteors.add(met11);
//        Meteors met12 = new Meteors(1350, -730, 32, 32);
//        moving_meteors.add(met12);
//        Meteors met13 = new Meteors(1200, -1280, 32, 32);
//        moving_meteors.add(met13);
//        Meteors met14 = new Meteors(1100, -750, 32, 32);
//        moving_meteors.add(met14);
//        Meteors met15 = new Meteors(1000, -850, 32, 32);
//        moving_meteors.add(met15);
//        Meteors met16 = new Meteors(1480, -930, 32, 32);
//        moving_meteors.add(met16);
//        Meteors met17 = new Meteors(1290, -1250, 32, 32);
//        moving_meteors.add(met17);
//        Meteors met18 = new Meteors(1000, -450, 32, 32);
//        moving_meteors.add(met18);
//        Meteors met19 = new Meteors(1390, -850, 32, 32);
//        moving_meteors.add(met19);
//        Meteors met20 = new Meteors(1150, -20, 32, 32);
//        moving_meteors.add(met20);

        Meteors met_static = new Meteors(400, 300, 32, 32);
        static_meteors.add(met_static);


    }
    public void init_Level1_bg()
    {
        back_ground = LoadSave.importImg("/Map/image 1.png");
    }


    public void render(Graphics g)
    {


        g.drawImage(back_ground, (int)x1 , (int)y1, 1280, 720, null);
        g.drawImage(back_ground, (int)x2, (int)y2, 1280, 720, null);
        g.drawImage(back_ground, (int)x3,(int)y3,1280,720, null);




        for (TileMap t: tile_maps)
        {
            t.render(g);
        }

        for(Meteors met: moving_meteors)
        {
            met.render(g);

        }
        doc2.render(g);
        doc3.render(g);
        for (Turtle turtle: turtles)
        {
            turtle.render(g);
        }

        light_bulb1.render(g);
        light_bulb2.render(g);
        light_bulb3.render(g);

        fireBall1.render(g);
        fireBall2.render(g);
        fireBall3.render(g);

        b1.render(g);
        b2.render(g);
        doc.render(g);

        last_document.render(g);
    }

    public void update(Player hero)
    {




            for (TileMap t : tile_maps) {
                t.update(hero);
            }



            //verific daca unul dintre tilemapuri este ocupat
            //fac sau intre toate valorile occupied
            //daca este 0 inseamna ca sunt in cadere

            boolean temp = tile_maps.get(0).getOccupied();
            for (int i = 1; i < tile_maps.size(); i++) {
                player_grounded = temp | tile_maps.get(i).getOccupied();
                temp = player_grounded;
            }

            //hero.player grounded = player grounded
            setGrounded(hero);

            //daca sunt in primul nivel
            if(contor==0) {

                //verific coliziunea cu bulleturile
                for (Turtle turtle : turtles) {
                    turtle.update(hero.getBullet(), hero);
                }

                light_bulb1.update(hero);
                light_bulb2.update(hero);
                light_bulb3.update(hero);


                b1.update(hero);
                b2.update(hero);
                //daca primul buton este apasat
                if (b1.getStare() == 1) {
                    //misc tilemapul pe care se afla
                    tile_maps.get(6).setMoveIt(true);
                    //misc si eroul
                    movePlatformRight(hero);
                } else {
                    tile_maps.get(6).setMoveIt(false);
                }
                if (moveItUp && b1.getStare() == 1) {
                    //daca trebuie sa il misc in sus
                    tile_maps.get(6).getVector2D().modifyY(-0.5f);
                    hero.getVector2D().modifyY(-0.5f);
                    b1.getVector2D().modifyY(-0.5f);
                    if (tile_maps.get(6).getVector2D().getVectY() < 120) {
                        moveItUp = false;
                        //System.out.println(" e false");
                    }
                }
                //daca cel de ai doilea buton nu este apasat, acel tilemap se misca
                if(!(b2.getStare()==1))
                {
                    tile_maps.get(4).init_borders(250, 100);
                    tile_maps.get(4).make_it_hard();
                    b2.init_borders(250,100);
                    b2.make_it_follow();
                }


                //daca am ajuns pe un anumit tilemap, un turtle va cadea
                if (tile_maps.get(4).getOccupied()) {
                    //System.out.println("Am intrat aici");
                    if(!(b2.getStare()==1))
                    {
                        hero.make_it_hard(tile_maps.get(4));
                    }

                    turtles.get(1).setFall(true);
                    turtles.get(1).collision_with_tilemap(tile_maps.get(16));

                }

                //in functie de becuri opresc sau nu fireballuri
                fireBall1.update(hero);
                if (light_bulb1.getAprins()) {
                    fireBall1.setGo_get_him(true);
                }

                fireBall2.update(hero);
                if (light_bulb2.getAprins()) {
                    fireBall2.setGo_get_him(true);
                }

                if (!light_bulb1.getFunctional()) {
                    turtles.get(2).setFall(true);
                    turtles.get(2).collision_with_tilemap(tile_maps.get(18));
                }

                fireBall3.update(hero);
                if (light_bulb3.getAprins()) {
                    fireBall3.setGo_get_him(true);
                }


                doc.update(hero);
                doc2.update(hero);
                doc3.update(hero);

                //daca ultimul document este capturat fac tranzitia pentru level 2
                if (doc.getCapturat())  {
                    start_removing_all = true;
                    removeAll();
                    backgroundChanging();
                    moveBackground = true;
                }
            }


        //level 2
        if(contor>=1)
        {


            fireBall1.getVector2D().setVectY(-100);
            fireBall1.getVector2D().setVectX(-100);

            fireBall2.getVector2D().setVectY(-100);
            fireBall2.getVector2D().setVectX(-100);

            fireBall3.getVector2D().setVectY(-100);
            fireBall3.getVector2D().setVectX(-100);


            //setez tilemapruile pentru level2
            tile_maps.get(0).getVector2D().setVectY(490);
            tile_maps.get(0).setItAgain(900);

            tile_maps.get(1).getVector2D().setVectY(450);
            tile_maps.get(1).setItAgain(800);

            tile_maps.get(2).getVector2D().setVectY(420);
            tile_maps.get(2).setItAgain(700);

            tile_maps.get(3).getVector2D().setVectY(380);
            tile_maps.get(3).setItAgain(600);

            tile_maps.get(4).getVector2D().setVectY(350);
            tile_maps.get(4).setItAgain(500);

            tile_maps.get(6).getVector2D().setVectY(330);
            tile_maps.get(6).setItAgain(400);

            if(tile_maps.get(6).getfinished())
            {
                //pornesc meteoritii
                for (Meteors met : moving_meteors) {
                    met.update(hero);
                }
                last_document.getVector2D().setVectX(430);
                last_document.getVector2D().setVectY(310);
            }

            last_document.update(hero);
            //daca ultimul document este capturat
            //partea eroului s a terminat, a castigat
            //va intra in quiz
            if(last_document.getCapturat())
            {
                tile_maps.get(6).setItAgain(1000);
                hero.setWinner(true);
                backgroundChanging();
            }
        }






    }

    public void setGrounded(Player p)
    {
        p.setGrounded(player_grounded);
    }

    public void movePlatformRight(Player hero)
    {

        if(tile_maps.get(6).getCoordonateX()<=350)
        {
            tile_maps.get(7).move();
            hero.getVector2D().modifyX(0.5f);
            b1.getVector2D().modifyX(0.5f);
        }
       else
        {
          tile_maps.get(6).setMoveIt(false);
          moveItUp = true;
          System.out.println("hei");

        }


    }


    public void removeAll()
    {
        if(start_removing_all) {
            for (TileMap tilemap : tile_maps) {
                if (!tilemap.getOccupied())
                    tilemap.removeTileMap();
                else
                    System.out.println(tilemap);
            }

            for (Turtle t : turtles) {
                t.removeTurtle();
            }
            doc2.remove();
            doc.remove();
            b1.removeButton();
            b2.removeButton();
            light_bulb2.removeLight();
            light_bulb1.removeLight();
            light_bulb3.removeLight();
        }

    }

    //se misca imaginea din spatele ecranului
    public void backgroundChanging()
    {

            x1=x1-0.5f;
            x2=x2-0.5f;

            if (x1 <= -1275) {
                x1 = 1275;
                contor++;
            }
            if (x2 <= -1275) {
                x2 = 1275;
                contor++;
            }


    }



    public Document getLast_document()
    {
        return last_document;
    }




}
