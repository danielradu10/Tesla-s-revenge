package Levels;

import entities.Fulger;
import entities.Turtle;
import utile.LoadSave;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Meniu {

    BufferedImage background;

    boolean play_almost_pressed = false, info_almost_pressed = false, quit_almost_pressed = false;

    boolean play_pressed = false, info_pressed = false, quit_pressed = false;

    public Rectangle play = new Rectangle(580, 350, 150, 50);
    public Rectangle info = new Rectangle(580, 450, 150, 50);

    public Rectangle Quit = new Rectangle(580, 550, 150, 50);

    Fulger f1 = new Fulger(50, 50, 128, 128, 1);

    Fulger f2 = new Fulger(1070, 50, 128, 128, 2);

    Fulger f3 = new Fulger(50, 550, 128, 128, 3);

    Fulger f4 = new Fulger(1070, 550, 128, 128, 0);

    Fulger fmeniu = new Fulger(-20,-20,32,32, 0);
    Fulger fmeniu2 = new Fulger(-20,-20,32,32, 0);



    //public Rectangle  = new Rectangle(580, 350, 150, 50);




    public void initMeniu()
    {
        background =LoadSave.importImg("/menubg.png");
        f1.initFulger();

    }

    public void update()
    {
        f1.update();
        f2.update();
        f3.update();
        f4.update();
        if(play_almost_pressed)
        {
            fmeniu.getVector2D().setVectX(play.x);
            fmeniu.getVector2D().setVectY(play.y);

            fmeniu2.getVector2D().setVectX(play.x+120);
            fmeniu2.getVector2D().setVectY(play.y);
        }

        else if(quit_almost_pressed)
        {
            fmeniu.getVector2D().setVectX(Quit.x);
            fmeniu.getVector2D().setVectY(Quit.y);

            fmeniu2.getVector2D().setVectX(Quit.x+120);
            fmeniu2.getVector2D().setVectY(Quit.y);


        }
        else if(info_almost_pressed)
        {
            fmeniu.getVector2D().setVectX(info.x);
            fmeniu.getVector2D().setVectY(info.y);

            fmeniu2.getVector2D().setVectX(info.x+120);
            fmeniu2.getVector2D().setVectY(info.y);
        }
        else
        {
            fmeniu.getVector2D().setVectX(-20);
            fmeniu.getVector2D().setVectY(-20);

            fmeniu2.getVector2D().setVectX(-20);
            fmeniu2.getVector2D().setVectY(-20);


        }
        fmeniu.update();
        fmeniu2.update();
    }


    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(background, 0 , 0, 1280, 720, null);
        Font fnt0 = new Font("arial", Font.BOLD, 70);
        g.setFont(fnt0);
        g.setColor(new Color(102, 178, 255));
        g.drawString("TESLA'S", 450, 150);
       // g.setColor(new Color(153, 204, 255));
        g.drawString("REVENGE", 600, 220);

        Font fnt1 = new Font("arial", Font.BOLD, 40);

        if (play_almost_pressed)
        {
            g.setColor(new Color(204,178,255));
        }
        else
        {
            g.setColor(new Color(153, 204, 255));

        }

        g2d.draw(play);


        g.setFont(fnt1);
        g.drawString("PLAY", play.x+18, play.y+40);

        if (info_almost_pressed)
        {
            g.setColor(new Color(204,178,255));
        }
        else
        {
            g.setColor(new Color(153, 204, 255));

        }

        g2d.draw(info);
        g.drawString("INFO", info.x+22, info.y+40);


        if (quit_almost_pressed)
        {
            g.setColor(new Color(204,178,255));
        }
        else
        {
            g.setColor(new Color(153, 204, 255));

        }

        g2d.draw(Quit);
        g.drawString("QUIT", Quit.x+22, Quit.y+40);

        f1.render(g);
        f2.render(g);
        f3.render(g);
        f4.render(g);
        fmeniu.render(g);
        fmeniu2.render(g);
    }

    public void addUsername()
    {
        JLabel label = new JLabel("Username");
        label.setBounds(100,8,70,20);

    }

    public void setPlay_almost_pressed(boolean val)
    {
        this.play_almost_pressed = val;
    }

    public void setInfo_almost_pressed(boolean val)
    {
        this.info_almost_pressed = val;
    }

    public void setQuit_almost_pressed(boolean val)
    {
        this.quit_almost_pressed = val;
    }

    public boolean isPlay_pressed() {
        return play_pressed;
    }

    public void setPlay_pressed(boolean play_pressed) {
        this.play_pressed = play_pressed;
    }

    public boolean isInfo_pressed() {
        return info_pressed;
    }

    public void setInfo_pressed(boolean info_pressed) {
        this.info_pressed = info_pressed;
    }

    public boolean isQuit_pressed() {
        return quit_pressed;
    }

    public void setQuit_pressed(boolean quit_pressed) {
        this.quit_pressed = quit_pressed;
    }

    public boolean isPlay_almost_pressed() {
        return play_almost_pressed;
    }

    public boolean isInfo_almost_pressed() {
        return info_almost_pressed;
    }

    public boolean isQuit_almost_pressed() {
        return quit_almost_pressed;
    }
}
