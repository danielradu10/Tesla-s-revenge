package entities;

import Incercari.Vector2D;
import utile.CollidedBox;

import java.awt.*;

public abstract class Entity {
   // protected float x, y;

    protected Vector2D vector2D;
    protected float width, height;

    protected CollidedBox collided_box;
    public Entity(float x, float y, int height, int width){
        vector2D = new Vector2D();
        vector2D.setVectX(x);
        vector2D.setVectY(y);
        this.height = height;
        this.width = width;
        collided_box = new CollidedBox();
        if(height<0)
        {
            height=height*-1;
        }

        collided_box.init_CollidedBox((int) vector2D.getVectX(),(int) vector2D.getVectY(),width, height);

    }




    public Vector2D getVector2D() {
        return vector2D;
    }

    public CollidedBox getHitbox(){
        return collided_box;
    }


}
