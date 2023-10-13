package utile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {


    public static BufferedImage importImg(String image_path){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream(image_path);
        System.out.println("Incerc sa dau upload");
        try {
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }
}
