package Levels;

import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LogInRoadMap implements LogIn{

    Rectangle lvl1, quiz, lvl2;

    boolean startLvl1 =false;

    boolean startLvl2 = false;

    boolean almoststartLvl1 = false;
    boolean almoststartLvl2 = false;
    BufferedImage roadmap;
    int level;




    LogInRoadMap(int level)
    {
        this.level = level;
        if(level == 1 || level == 2)
        {
            roadmap = LoadSave.importImg("/menubg.png");
        }


    }
    @Override
    public void connect(String username) {

        System.out.println("Welcome: " + username);


    }

    public void render(Graphics g)
    {
        //in functie de level ul pe care sunt setez culorile 
        g.drawImage(roadmap, 0 , 0, 1280, 720, null);
        if(level ==1)
        {
            g.setColor(new Color(105, 105, 105));
            Graphics2D g2d = (Graphics2D) g;

            Font fnt0 = new Font("arial", Font.BOLD, 40);
            g.setFont(fnt0);

            if(almoststartLvl1)
            {
                g.setColor(new Color(204,178,255));
            }


            lvl1 = new Rectangle(50, 350, 200, 50);

            g2d.draw(lvl1);

            g.drawString("LEVEL 1", lvl1.x+22, lvl1.y+40);

             quiz = new Rectangle(350, 350, 200, 50);

            g2d.draw(quiz);
            if(almoststartLvl2)
            {
                System.out.println("Aproape vrea sa inceapa primul nivel");
                g.setColor(new Color(204,178,255));
            }

            g.drawString("QUIZ 1", quiz.x+22, quiz.y+40);

             lvl2 = new Rectangle(600, 350, 200, 50);

            g2d.draw(lvl2);

            g.drawString("LEVEL 2", lvl2.x+22, lvl2.y+40);

        }

        if(level==2)
        {
            g.setColor(new Color(102, 178, 255));
            Graphics2D g2d = (Graphics2D) g;

            Font fnt0 = new Font("arial", Font.BOLD, 40);
            g.setFont(fnt0);

            if(almoststartLvl1)
            {
                g.setColor(new Color(204,178,255));
            }
            lvl1 = new Rectangle(50, 350, 200, 50);

            g2d.draw(lvl1);

            g.drawString("LEVEL 1", lvl1.x+22, lvl1.y+40);

            quiz = new Rectangle(350, 350, 200, 50);

            g2d.draw(quiz);

            g.drawString("QUIZ 1", quiz.x+22, quiz.y+40);

            g.setColor(new Color(105, 105, 105));

            if(almoststartLvl2)
            {
                System.out.println("Aproape vrea sa inceapa primul nivel");
                g.setColor(new Color(204,178,255));
            }
            lvl2 = new Rectangle(600, 350, 200, 50);

            g2d.draw(lvl2);

            g.drawString("LEVEL 2", lvl2.x+22, lvl2.y+40);
        }



    }
    @Override
    public Rectangle getLvl1Rect()
    {
            return this.lvl1;
    }

    public void setLvl1(boolean val)
    {
        this.startLvl1 = val;
    }

    @Override
    public void setalmostLvl1(boolean val) {
        this.almoststartLvl1 = val;
    }

    @Override
    public boolean getLvl1()
    {
        return this.almoststartLvl1;
    }

    @Override
    public int getLevel(){
        return this.level;
    }

    @Override
    public Rectangle getLvl2Rect()
    {
        return this.lvl2;
    }
    @Override
    public void setalmostLvl2(boolean val)
    {
        this.almoststartLvl2 = val;
    }

    @Override
    public void setLvl2(boolean val)
    {
        this.startLvl2 = val;
    }

    @Override
    public boolean getLvl2()
    {
        return this.almoststartLvl2;
    }
}
