package demo;
import animaper.*;
import puppeteer.*;

public class Ship extends AnimatedComponent {

  public Ship(){
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/images/galactica.png");
    // SET COMPONENT FRAME SIZE
    setSize(80,80);
    setCollider(new BoxCollider( 80,80 ));
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
    this.addTimeline("main", mainTimeline);
    // SET THE ACTIVE TIMELINE TO "main"
    this.setCurrentTimeline("main");
  }

  @Override
  public void onCollision(Collision collision){
    System.out.println("Chocando");
  }

  @Override
  public void onCollisionStart(Collision collision){
    System.out.println("Collision start");
  }

  @Override
  public void onCollisionEnd(Collision collision){
    System.out.println("Collision end");
  }


}
