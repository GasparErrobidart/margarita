import animaper.*;
import puppeteer.*;
import demo.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Graphics;

public class Main {

  public static void main (String [] args) {

    Scene scene = Scene.getInstance();

    Ship ship1 = new Ship();
    Ship ship2 = new Ship();
    Ship ship3 = new Ship();
    Ship ship4 = new Ship();


    scene.add(ship1);
    scene.add(ship2);
    scene.add(ship3);
    scene.add(ship4);

    scene.start();





    Timer t = new Timer (1000/24, new ActionListener ()
    {
        public void actionPerformed(ActionEvent ev)
        {
          try {
            // TIME LOOP FOR MOVING THE X,Y POSITION OF THE COMPONENT
            ship2.move(4,4);
            ship3.move(1,0);
            ship4.move(0,1);

          }
          catch(Exception e) {
            e.printStackTrace();
          }
         }
    });

    t.start();




  }

}
