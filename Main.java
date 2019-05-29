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
    AnimatedComponent box = new AnimatedComponent();
    box.loadBitmap("demo/images/galactica.png");
    box.setSize(80,80);
    AnimationFrame[] frames = {
      new AnimationFrame(-372,-1765),
      new AnimationFrame(-372,-1845),
      new AnimationFrame(-372,-1925)
    };
    AnimationTimeline mainTimeline = new AnimationTimeline(frames);
    mainTimeline.setLoop(true);
    box.addTimeline("main", mainTimeline);
    box.setcurrentTimeline("main");
    // Component box2 = new Component(0,0,60,60);
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
