import animaper.*;
import puppeteer.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Graphics;

public class Main {

  public static int r = 0;
  public static int g = 0;
  public static int b = 0;
  public static Color color;

  public static void main (String [] args) {

    Scene scene = Scene.getInstance();

    // CREATE ANIMATED COMPONET TYPE
    AnimatedComponent ship = new AnimatedComponent();
    // LOAD SPRITE SHEET
    ship.loadBitmap("demo/images/galactica.png");
    // SET COMPONENT FRAME SIZE
    ship.setSize(80,80);
    // DEFINE TIMELINE FRAMES
    AnimationFrame[] frames = {
      new AnimationFrame(-372,-1765),
      new AnimationFrame(-372,-1845),
      new AnimationFrame(-372,-1925)
    };

    // CREATE MAIN TIMELINE FOR COMPONENT
    AnimationTimeline mainTimeline = new AnimationTimeline(frames);
    // SET ANIMATION TIMELINE TO LOOP OVER
    mainTimeline.setLoop(true);
    // ADD MAIN TIMELINE TO ANIMATED COMPONENT
    ship.addTimeline("main", mainTimeline);
    // SET THE ACTIVE TIMELINE TO "main"
    ship.setCurrentTimeline("main");


    scene.add(ship);

    scene.start();



















    Timer t = new Timer (1000/24, new ActionListener ()
    {
        public void actionPerformed(ActionEvent ev)
        {
          try {
            // TIME LOOP FOR MOVING THE X,Y POSITION OF THE COMPONENT
            ship.move(1,2);

          }
          catch(Exception e) {
            e.printStackTrace();
          }
         }
    });

    t.start();




  }

}
