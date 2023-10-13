package Levels;

import utile.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ResumeMenu{

    public static BufferedImage buff = LoadSave.importImg("/menubg.png");




    public void render(Graphics g)
    {
        g.drawImage(buff, 0, 0, 1280, 720, null);
        g.drawString("Press R to resume", 200, 200);
    }

}
