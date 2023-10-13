package Incercari;

public class Vector2D {

    //functii de baza pentru Vector2D
    private float vectX, vectY;


    public void setVectX(float vectX)
    {
        this.vectX = vectX;
    }
    public void setVectY(float vectY)
    {
        this.vectY = vectY;
    }

    public float getVectX()
    {
        return vectX;
    }

    public float getVectY()
    {
        return vectY;
    }



    public void modifyX(float x)
    {
        this.vectX = this.vectX + x;
    }

    public void modifyY(float y)
    {
        this.vectY = this.vectY + y;
    }

    public Vector2D add(Vector2D v)
    {
        Vector2D calc = new Vector2D();
        calc.setVectX(this.vectX + v.getVectX());
        calc.setVectY(this.vectY + v.getVectY());
        return calc;
    }

    public Vector2D multiply(float val)
    {
        Vector2D calc = new Vector2D();
        calc.setVectX(this.getVectX()*val);
        calc.setVectY(this.getVectY()*val);
        return calc;
    }

    @Override
    public String toString() {
        String val = "X: " + this.getVectX() + " Y: "+ this.getVectY();
        return val;
    }

}
