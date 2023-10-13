package main;

import Incercari.Quiz;
import Incercari.Username;
import Levels.*;
import entities.Meteors;
import entities.Player;
import entities.Turtle;
import utile.Constante;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameLoopThread;
    private final int FPS_SET=120; //how many frames per second we want

    private final int UPS_SET =200;


    String username_text;
    private Player player;
    private Level1 lvl1;

    private Level2 lvl2;
    int newPosition = 0;

    private boolean level2;

   // private List<Turtle> turtle_list = new ArrayList<>(5);

  // private Turtle turtle_enemy = new Turtle(0,0,0,0);
   // private Turtle UpsideDownTurtle;
    boolean visibleQuiz = true;

    JPanel quizPanel;
    Quiz quiz;

    boolean time_for_quiz = true;

    private static int gameState = Constante.GameStates.MENIU ;

    Meniu meniu;

    Username username;

    LogIn roadmap;

    ResumeMenu resumeMenu;




    public Game(){
        System.out.println("lalalalal");
        initClasses();
        gamePanel = new GamePanel(this);
        quizPanel = new JPanel();
        quiz = new Quiz(5);
        quiz.initQuiz();

        username = new Username();
        username.initUsername(roadmap);


        gamePanel.add(quiz.getPanel());
        gamePanel.add(username.getPanel());




        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true); //setam astfel incat sa preluam inputul

        gamePanel.requestFocus();



        startGameLoop();


    }

    private void initClasses() {

        lvl1 = new Level1();
        player = new Player(200,50, 64, 64);
        //gameState = Constante.GameStates.MENIU;
        meniu = new Meniu();


        meniu.initMeniu();
        lvl2 = new Level2();
        lvl2.init();
        resumeMenu = new ResumeMenu();




//        Turtle turtle_enemy = new Turtle(850, 540, 32, 32);
//
//
//
//
//        turtle_list.add(turtle_enemy);
//
//        Turtle UpsideDownTurtle = new Turtle(30, 30, 32, 32);
//        if(UpsideDownTurtle == null)
//        {
//            System.out.println("E null");
//        }
//        turtle_list.add(UpsideDownTurtle);
//
//        for(Turtle turtle : turtle_list)
//        {
//            turtle.init_turtle();
//        }






    }

    private void startGameLoop(){
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    public void update(){

        if(gameState == Constante.GameStates.LEVEL_ONE)
        {
            lvl1.update(player);
            player.update();


            if( lvl1.getLast_document().getCapturat() &&time_for_quiz)
            {
                //System.out.println("QUIZ");
                 quiz.setVisible();
                 if(quiz.getDone())
                 {
                     quiz.setInvisible();
                     time_for_quiz = false;
                     player.setWinner(false);
                     lvl1.getLast_document().setCapturat(false);
                     gameState = Constante.GameStates.LEVEL_two;

                 }
            }
        }
        if(gameState == Constante.GameStates.MENIU)
        {
            meniu.update();
        }

        if(gameState == Constante.GameStates.USERNAME)
        {

            if(username.getVisbility())
            {
                username.setVisible();
            }
            else
            {
                username.setInvisible();
            }

            username.update();
            username_text = username.getUsername_text();
            quiz.setUsername_text(username_text);



            //username.afisare();
            //username.afisare();
        }

        if (gameState == Constante.GameStates.LEVEL_two)
        {
            level2 = true;
            if(newPosition == 0)
            {

                int xpos = getPlayerX(username_text);
                int ypos = getPlayerY(username_text);
                lvl2.setNumber_of_tesla_cards(getCorrectAnswers(username_text));
                lvl2.setUsername_text(username_text);
                player.setNewPosition(xpos, ypos);
                lvl2.setSeahorseCord(getSeahorseX(), getSeahorseY());

                newPosition++;
            }

                lvl2.update(player);
                player.update();
                player.setLevel(2);
                player.setMaxBullets(5);
        }


//        for(Turtle turtle : turtle_list)
//        {
//            turtle.update(player.getBullet());
//            System.out.println(turtle);
//        }
        //turtle_enemy.update(player.getBullet());



        //turte_enemy_2.update(player.getBullet());

//        if(turtle_enemy.dead)
//        {
//            visibleQuiz = true;
//        }






    }
    public void render(Graphics g)
    {
        if(gameState == Constante.GameStates.LEVEL_ONE)
        {
            lvl1.render(g);
            player.render(g);
        }

        if(gameState == Constante.GameStates.MENIU)
        {
            meniu.render(g);
        }

        if(gameState == Constante.GameStates.USERNAME)
        {
            username.render(g);
        }
        if (gameState == Constante.GameStates.LEVEL_two)
        {
            lvl2.render(g);
            player.render(g);
        }
        if(gameState == Constante.GameStates.MENUSTATE)
        {

            resumeMenu.render(g);
        }




//        for(Turtle turtle : turtle_list)
//        {
//            turtle.render(g);
//        }
        //turtle_enemy.render(g);
        //turte_enemy_2.render(g);

    }
    @Override
    public void run(){
        double timePerFrame =  1000000000.0/FPS_SET; //how long will each frame last  in nanoseconds
        double timePerUpdate = 1000000000.0/UPS_SET; //time of the freq
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        long previousTime = System.nanoTime();


        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;

        double deltaF = 0;

        while(true)
        {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;

            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if(deltaU >=1)
            {
                update();
                updates++;
                deltaU--;
            }
            if(deltaF >=1)
            {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }


            if(System.currentTimeMillis() - lastCheck >= 1000)
            {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS " + updates);
                frames = 0;
                updates =0;
            }

        }
    }


    public int getCorrectAnswers(String username_text) {
        int numberCards = 1;

        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String verify = "SELECT * FROM Usernames WHERE username = '" + username_text + "' ";

            ResultSet rs = stmt.executeQuery(verify);
            if (rs.next()) {
                System.out.println("Ma prind de cate carduri trebuie sa fie");
                numberCards = rs.getInt("quiz");
                System.out.println("Sunt returnate un numar de: " + numberCards + " carduri");
            } else {
                System.out.println("Nu am gasit asemenea id pentru username ul: " + username_text);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  numberCards;
    }

    int getPlayerX(String username_text){

        int x = 600;
        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String verify = "SELECT * FROM Usernames WHERE username = '" + username_text + "' ";

            ResultSet rs = stmt.executeQuery(verify);
            if (rs.next()) {
                System.out.println("Ma prind de coordonata X a player-ului");
                x = rs.getInt("playerX");

                System.out.println("Coordonata playerului x: " + x + " carduri");
            } else {
                System.out.println("Nu am gasit asemenea id pentru username ul: " + username_text);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  x;
    }

    int getPlayerY(String username_text){

        int y = 0;
        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String verify = "SELECT * FROM Usernames WHERE username = '" + username_text + "' ";

            ResultSet rs = stmt.executeQuery(verify);
            if (rs.next()) {
                System.out.println("Ma prind de coordonata X a player-ului");
                y = rs.getInt("playerY");
                System.out.println("Coordonata playerului x: " + y + " carduri");
            } else {
                System.out.println("Nu am gasit asemenea id pentru username ul: " + username_text);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  y;
    }

    public int getSeahorseX()
    {
        int x = 0;
        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String verify = "SELECT * FROM Usernames WHERE username = '" + username_text + "' ";

            ResultSet rs = stmt.executeQuery(verify);
            if (rs.next()) {
                System.out.println("Ma prind de coordonata X a player-ului");
                x = rs.getInt("seahorseX");

                System.out.println("Coordonata seahorse x: " + x);
            } else {
                System.out.println("Nu am gasit asemenea id pentru username ul: " + username_text + " in cautarea seahorse ");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  x;
    }

    public int getSeahorseY()
    {
        int y = 0;
        String url = "jdbc:sqlite:D://ANUL2/PAOO/PAOO/PregatireaJocului_5_Meniu/tests.db";
        try {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String verify = "SELECT * FROM Usernames WHERE username = '" + username_text + "' ";

            ResultSet rs = stmt.executeQuery(verify);
            if (rs.next()) {
                System.out.println("Ma prind de coordonata Y a player-ului");
                y = rs.getInt("seahorseY");

                System.out.println("Coordonata seahorse y: " + y);
            } else {
                System.out.println("Nu am gasit asemenea id pentru username ul: " + username_text);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  y;
    }


    public int getState()
    {
        return gameState;
    }


    public void setGameState(int gameState1)
    {
        gameState = gameState1;
    }


    public Player getPlayer()
    {
        return  player;
    }

    public Username getUsername()
    {
        return username;
    }

    public Level1 getLvl1() {
        return lvl1;
    }

    public Level2 getLvl2(){
        return lvl2;
    }

    public Meniu getMeniu(){ return meniu;}

    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    public boolean isLevel2(){
        return this.level2;
    }
}


