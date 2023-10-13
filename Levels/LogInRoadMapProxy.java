package Levels;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.*;

public class LogInRoadMapProxy implements LogIn{

    private LogInRoadMap loginObject;

    int level = 1;

    @Override
    public void connect(String username) {

        create_table();
        insert_in_table(username);
        search_in_table(username);
        loginObject = new LogInRoadMap(level);
        loginObject.connect(username);

    }



    //daca nu exista tabel, creez
    public void create_table(){

        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        String sql = "CREATE TABLE IF NOT EXISTS Usernames (\n"
                + "	id integer PRIMARY KEY,\n"
                + " username text NOT NULL,\n"
                + "	level1 integer,\n"
                + "	quiz integer,\n"
                + " playerX integer,\n"
                + " playerY integer,\n"
                + " seahorseX integer,\n"
                + " seahorseY integer, \n"
                + " level2 integer\n"
                + ");";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {


            // create a new table
            stmt.execute(sql);




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //inserez in tabel usernameul luat de la login
    void insert_in_table(String username)
    {

        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {


            {
                String verify = "SELECT id FROM Usernames WHERE username = '" + username + "' ";
                ResultSet rs = stmt.executeQuery(verify);
                if(rs.next())
                {
                    if(rs.getInt("id")==0) {

                        System.out.println("Nu am gasit acest username, asa ca adaug");
                        String INSERT = "INSERT INTO Usernames(username, level1, quiz, playerX, playerY, seahorseX, seahorseY, level2)" + "VALUES ('" + username + "',0,0,600,0,100,450,0);";
                        stmt.executeUpdate(INSERT);
                    }
                    else
                    {
                        System.out.println("Am gasit deja acest username, nu mai adaug.");
                    }

                }
                else
                {
                    System.out.println("Adaug un nous username");
                    String INSERT = "INSERT INTO Usernames(username, level1, quiz, playerX, playerY, seahorseX, seahorseY, level2)" + "VALUES ('" + username + "',0,0,600,0,100,450,0);";
                    stmt.executeUpdate(INSERT);

                }
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //caut in tabel levelul la care a ramas
    void search_in_table(String username)
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

            String verify = "SELECT * FROM Usernames WHERE username = '" + username + "' ";

            ResultSet rs = stmt.executeQuery(verify);
            if(rs.next())
            {
                System.out.println("Ma prind de ce level are username-ul");
                if(rs.getInt("level1") == 0)
                {
                    System.out.println("Username-ul "+ username +" nu a rezolvat nici un nivel");
                    level = 1;
                }
                else
                {
                    if(rs.getInt("level1") == 1)
                    {
                        System.out.println("Username-ul "+ username +"a rezolvat primul nivel si primul quiz");
                        level = 2;
                    }
                }
            }
            else
            {
                System.out.println("Nu am gasit asemenea id ");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void render(Graphics g)
    {
        this.loginObject.render(g);
    }

    @Override
    public Rectangle getLvl1Rect() {
        return this.loginObject.getLvl1Rect();
    }

    @Override
    public void setalmostLvl1(boolean val) {
        this.loginObject.setalmostLvl1(val);
    }

    @Override
    public void setLvl1(boolean val) {
        this.loginObject.setLvl1(val);
    }

    @Override
    public boolean getLvl1()
    {
        return this.loginObject.getLvl1();
    }

    public int getLevel() { return this.loginObject.getLevel();}
    public Rectangle getLvl2Rect()
    {
        return this.loginObject.getLvl2Rect();
    }
    @Override
    public void setalmostLvl2(boolean val)
    {
        this.loginObject.setalmostLvl2(val);
    }

    @Override
    public void setLvl2(boolean val)
    {
        this.loginObject.setLvl2(val);
    }

    @Override
    public boolean getLvl2()
    {
        return this.loginObject.getLvl2();
    }
}
