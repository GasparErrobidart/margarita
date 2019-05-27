import animaper.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class Main {

  public static Screen screen;

  public static void main (String [] args) {

    Screen screen = new Screen(600,600);

    BufferedImage img1 = new BufferedImage(256,256, BufferedImage.TYPE_INT_RGB);
    for(int i=0; i< 256; i++) {
        for(int j=0; j< 256; j++) {
            Color newColor = new Color(i,i,i);
            img1.setRGB(j,i,newColor.getRGB());
        }
    }

    screen.setImage(img1);
    screen.render();




  }

}
