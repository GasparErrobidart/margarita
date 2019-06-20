package demo.platformer;
import animaper.*;
import puppeteer.*;
import java.util.ArrayList;

public class Background extends AnimatedComponent {

  public Background(){
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/background-castle.jpg");
    // SET COMPONENT FRAME SIZE
    setSize(800,600);
    setTag("background");

    ArrayList<AnimationFrame> frames = new ArrayList<AnimationFrame>();
    for(int x = 0; x < 20 ; x++){
      frames.add(new AnimationFrame(x*-800,0));
    }

    // CREATE MAIN TIMELINE FOR COMPONENT
    AnimationTimeline bgTimeline = new AnimationTimeline(frames.toArray(new AnimationFrame[frames.size()]));
    // SET ANIMATION TIMELINE TO LOOP OVER
    bgTimeline.setLoop(true);
    // ADD MAIN TIMELINE TO ANIMATED COMPONENT
    addTimeline("bg", bgTimeline);
    setCurrentTimeline("bg");
  }

}
