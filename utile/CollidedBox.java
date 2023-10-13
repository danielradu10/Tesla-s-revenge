package utile;

import java.awt.*;

public class CollidedBox{

    public Rectangle rect;
    int x, y, width, height;


    public void init_CollidedBox(int x, int y, int width, int height)
    {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x,y,width,height);
    }
    public void drawCollidedBox(Graphics g)
    {
        g.setColor(Color.pink);
        g.drawRect(rect.x,rect.y, rect.width, rect.height);
    }

    public void updateCollidedBox(int x, int y)
    {
        this.x = x;
        this.y = y;
        rect.x = x;
        rect.y = y;
    }

    public Rectangle getRect()
    {
        return this.rect;
    }

    public void updateCollidedBoxWH(float width, float height) {
        this.width = (int) width;
        this.height = (int) height;
        rect.width = (int) width;
        rect.height = (int) height;

    }

    public void reverseWidth()
    {
        this.rect.width = this.rect.width*(-1);
    }

    @Override
    public String toString() {
        return "CollidedBox: " + height + "Width: "+ width + "COORD X: " + x + "COORD Y: " + y;
    }
}
