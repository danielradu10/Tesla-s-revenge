package Incercari;

import java.util.ArrayList;
import java.util.List;

public class Physics {

   public List<Vector2D> Forces = new ArrayList<>(3);
   Vector2D acceleration;
   public final float masa = 1.0f;




   //TO DO

   //trebuie sa calculez viteza urmatoare pentru a afla de fapt pozitia viitoare
   //respect formulele: a = F/m
   // v = a*dt
   // distanta = v*dt;


  public void addForce(Vector2D force)
  {
     Forces.add(force);
  }
   public Physics(int numberForces)
   {
      acceleration = new Vector2D();

   }

   public void calculateAcceleration()
   {
      Vector2D suma = new Vector2D();
      suma.setVectY(0);
      suma.setVectX(0);
      for (Vector2D force: Forces)
      {
         suma = suma.add(force);
      }

      this.acceleration = suma.multiply(1/masa);

   }

   public Vector2D getPosition(float deltaTimp)
   {
      Vector2D v;
      v = acceleration.multiply(deltaTimp);
      Vector2D pos = v.multiply(deltaTimp);

      return pos;
   }

   public void setAccelerationAsGravity()
   {
       this.acceleration.setVectX(0);
       this.acceleration.setVectY(1250f);
   }

}
