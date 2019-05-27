import animaper.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.Timer;

public class Main {

  public static Screen screen;
  public static int r = 0;
  public static int g = 0;
  public static int b = 0;
  public static Color color;

  public static void main (String [] args) {

    Screen screen = new Screen(600,600);

    color = new Color(r,g,b);

    BufferedImage img1 = new BufferedImage(256,256, BufferedImage.TYPE_INT_RGB);
    for(int i=0; i< 256; i++) {
        for(int j=0; j< 256; j++) {
            img1.setRGB(j,i,color.getRGB());
        }
    }

    screen.setImage(img1);
    screen.render();

    Timer t = new Timer (1000/24, new ActionListener ()
    {
        public void actionPerformed(ActionEvent ev)
        {
          try {
            r = color.getRed();
            g = color.getGreen();
            b = color.getBlue();
            System.out.println("rgb:"+r+","+g+","+b);
            if(r < 120){
              r++;
            }else if(b < 120){
              b++;
            }else if(g < 120){
              g++;
            }
            for(int i=0; i< 256; i++) {
                for(int j=0; j< 256; j++) {
                    color = new Color(r,g,b);
                    img1.setRGB(j,i,color.getRGB());
                }
            };
            screen.setImage(img1);
            screen.render();

          }
          catch(Exception e) {
            e.printStackTrace();
          }
         }
    });

    t.start();




  }

}
