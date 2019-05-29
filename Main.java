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

    // DEFINE SCREEN
    Screen screen = new Screen(600,600);
    // CREATE ANIMATED COMPONET TYPE
    AnimatedComponent box = new AnimatedComponent();

    // LOAD SPRITE SHEET
    box.loadBitmap("demo/images/galactica.png");
    // SET COMPONENT FRAME SIZE
    box.setSize(80,80);
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
    box.addTimeline("main", mainTimeline);
    // SET THE ACTIVE TIMELINE TO "main"
    box.setCurrentTimeline("main");

    // TEMPORARY WINDOW FRAME IMAGE FOR COMPOSING
    BufferedImage img = new BufferedImage(600,600, BufferedImage.TYPE_INT_ARGB);
    Graphics g = img.getGraphics();
    g.drawImage(box.render(), box.getPosition().getX(), box.getPosition().getY(), null);
    screen.setImage(img);
    screen.render();

    Timer t = new Timer (1000/24, new ActionListener ()
    {
        public void actionPerformed(ActionEvent ev)
        {
          try {
            // TIME LOOP FOR MOVING THE X,Y POSITION OF THE COMPONENT
            box.move(1,2);
            g.clearRect(0, 0, 600, 600);
            g.drawImage(box.render(), box.getPosition().getX(), box.getPosition().getY(), null);
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
