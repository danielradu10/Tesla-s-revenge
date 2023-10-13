package Levels;

import java.awt.*;

public interface LogIn {


    void connect(String username);

     void render(Graphics g);

     Rectangle getLvl1Rect();

     void setLvl1(boolean val);

    void setalmostLvl1(boolean val);
    boolean getLvl1();

    int getLevel();

    Rectangle getLvl2Rect();
    void setalmostLvl2(boolean val);

    void setLvl2(boolean val);

    boolean getLvl2();

}
