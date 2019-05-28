import animaper.*;
import puppeteer.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Graphics;

public class Main {

  public static Screen screen;
  public static int r = 0;
  public static int g = 0;
  public static int b = 0;
  public static Color color;

  public static void main (String [] args) {

    Screen screen = new Screen(600,600);
    Component box = new Component(0,0,100,100);
    Component box2 = new Component(0,0,60,60);
    BufferedImage img = new BufferedImage(600,600, BufferedImage.TYPE_INT_RGB);


    // color = new Color(r,g,b);
    //
    // BufferedImage img1 = new BufferedImage(256,256, BufferedImage.TYPE_INT_RGB);
    // for(int i=0; i< 256; i++) {
    //     for(int j=0; j< 256; j++) {
    //         img1.setRGB(j,i,color.getRGB());
    //     }
    // }

    // paint both images, preserving the alpha channels
    Graphics g = img.getGraphics();
    g.drawImage(box.render(), box.x, box.y, null);
    g.drawImage(box2.render(), box2.x, box2.y, null);
    screen.setImage(img);
    screen.render();

    Timer t = new Timer (1000/24, new ActionListener ()
    {
        public void actionPerformed(ActionEvent ev)
        {
          try {
            // r = color.getRed();
            // g = color.getGreen();
            // b = color.getBlue();
            // System.out.println("rgb:"+r+","+g+","+b);
            // if(r < 120){
            //   r++;
            // }else if(b < 120){
            //   b++;
            // }else if(g < 120){
            //   g++;
            // }
            // for(int i=0; i< 256; i++) {
            //     for(int j=0; j< 256; j++) {
            //         color = new Color(r,g,b);
            //         img1.setRGB(j,i,color.getRGB());
            //     }
            // };
            box.move(1,1);
            box2.move(1,0);
            g.clearRect(0, 0, 600, 600);
            g.drawImage(box.render(), box.x, box.y, null);
            g.drawImage(box2.render(), box2.x, box2.y, null);
            screen.setImage(img);
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
