package Levels;

import Incercari.DataBaseLevel2;
import entities.*;
import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Level2 {


    int x_of_first_card = 30;

    int y_of_first_card = 30;

    int x_of_first_seahorse = 100;
    int y_of_first_seahorse = 450;


    int number_of_tesla_cards;
    BufferedImage background;

    TileMap t1test;


    private Seahorse seahorse;




    private List<TileMap> tile_maps = new ArrayList<>(5);

    private List<TeslaCard> teslacards = new ArrayList<>(3);

    Document doc1, doc2;

    boolean player_grounded;

    int remainedBullets;

    Light light_bulb1;

    FireBall fireBall1;

    int capturate;

    DataBaseLevel2 dblvl2 = new DataBaseLevel2();

    String username_text;

    boolean saved = false;

    public void setUsername_text(String username_text)
    {
        this.username_text = username_text;
    }

    public void init()
    {
        background = LoadSave.importImg("/BrownMap/Blue Nebula 2 - 1024x1024.png");
        dblvl2.create_table();
        //dblvl2.insert_in_table();




        seahorse = new Seahorse(x_of_first_card, x_of_first_card, 64, 64);
        seahorse.init_seahorse();


        for(int i=1; i<=12; i++)
        {
            t1test = dblvl2.search_in_table_set_tile(i, "Map2Tile");
            t1test.init_tile_map();
            tile_maps.add(t1test);
        }


//        t1test = new TileMap(630,250,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//
//
//
//
//        t1test = new TileMap(50,350,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(170,300,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(350,325,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(500,280,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(745,300,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(845,500,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(200,580,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(345,560,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(485,540,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);
//
//        t1test = new TileMap(655,520,64,128,3);
//        t1test.init_tile_map();
//        tile_maps.add(t1test);

        doc1 = new Document(100, 340, 16, 48);

        doc2 = new Document(100, 580, 16, 48);

        doc1.init_document();
        doc2.init_document();

        light_bulb1 = new Light(400, 315, 32, 32);
        light_bulb1.init();

        fireBall1 = new FireBall(1300, 270, 32, 74);
        fireBall1.init();



    }



    public void update(Player hero)
    {
        remainedBullets = hero.getRemainedBullets();

        for (TileMap tilemap : tile_maps)
        {
            tilemap.update(hero);
        }

        for (TeslaCard card: teslacards)
        {
            card.update();
        }


        boolean temp = tile_maps.get(0).getOccupied();
        for (int i = 1; i < tile_maps.size(); i++) {
            player_grounded = temp | tile_maps.get(i).getOccupied();
            temp = player_grounded;
        }

        setGrounded(hero);


        seahorse.update(hero);


        doc1.update(hero);
        doc2.update(hero);

        light_bulb1.update(hero);
        fireBall1.update(hero);
        if (light_bulb1.getAprins()) {
            fireBall1.setGo_get_him(true);
        }

        if(doc1.getCapturat() && doc2.getCapturat())
        {
            capturate = 2;
            hero.walk_of_fame();
        }
        else if (doc1.getCapturat() && !doc2.getCapturat())
        {
            capturate = 1;
        }
        else if (!doc1.getCapturat() && doc2.getCapturat())
        {
            capturate = 2;
        }
        else
        {
            capturate = 0;
        }

        if(saved)
        {
            updateUsernamesTableSaved(username_text, hero);
            System.out.println("Am salvat");
        }

        if(capturate == 2)
        {
            hero.walk_of_fame();
        }



    }

    public void render(Graphics g)
    {
        light_bulb1.render(g);
        g.drawImage(background, 0,0,1280,720, null);
        printBullets(g);
        for (TileMap tilemap : tile_maps)
        {
            tilemap.render(g);
        }

        for (TeslaCard card: teslacards)
        {
            card.render(g);
        }


        seahorse.render(g);

        doc1.render(g);
        doc2.render(g);
        light_bulb1.render(g);
        fireBall1.render(g);



    }

    public void setGrounded(Player p)
    {
        p.setGrounded(player_grounded);
    }

    public void setNumber_of_tesla_cards(int value){

        this.number_of_tesla_cards = value;
        for(int i =0; i<number_of_tesla_cards; i++)
        {
            TeslaCard tesla_card = new TeslaCard(x_of_first_card, y_of_first_card, 64, 64);

            tesla_card.setFulgerSeahorse(seahorse);


            x_of_first_card = x_of_first_card + 70;
            teslacards.add(tesla_card);

        }

    }

    public List<TeslaCard> getTeslaList()
    {
        return this.teslacards;
    }

    public void printBullets(Graphics g)
    {

        Font fnt0 = new Font("arial", Font.BOLD, 15);
        g.setFont(fnt0);
        g.setColor(new Color(191, 19, 19));
        g.drawString("Bullets: " + remainedBullets , 1150, 20);

        g.drawString("Documents: " + capturate , 1150, 40);

    }

    //daca apas esc, salvez progresul
    public void updateUsernamesTableSaved(String username_text, Player hero)
    {

        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            int playerposX = (int) (hero.getVector2D().getVectX());
            int playerposY = (int) (hero.getVector2D().getVectY());
            String update = "UPDATE Usernames set playerX = " + playerposX + " where username='" + username_text + "';";

            stmt.executeUpdate(update);

            update = "UPDATE Usernames set playerY = " + playerposY + " where username='" + username_text + "';";

            stmt.executeUpdate(update);

            int seahorseX = (int) seahorse.getVector2D().getVectX();
            int seahorseY = (int) seahorse.getVector2D().getVectY();

            update = "UPDATE Usernames set seahorseX = " + seahorseX + " where username='" + username_text + "';";
            stmt.executeUpdate(update);

            update = "UPDATE Usernames set seahorseY = " + seahorseY + " where username='" + username_text + "';";
            stmt.executeUpdate(update);



            conn.commit();
            System.out.println("Cred ca e ok, pentru " + username_text +" am facut update-ul cerut");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "ceva nu e bine");
        }

    }



    public void setSaved(boolean saved)
    {
        this.saved = saved;
    }

    public void setSeahorseCord(int x, int y) {
        this.seahorse.getVector2D().setVectX(x);
        this.seahorse.getVector2D().setVectY(y);

    }
}
