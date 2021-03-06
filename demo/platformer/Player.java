package demo.platformer;
import animaper.*;
import puppeteer.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;


public class Player extends AnimatedComponent {

  private int speed = 10;
  private boolean landed = false;
  private int maxJumpPower = 300;
  private int jumpPower = 300;
  private boolean jumping = false;
  private int jumpSpeed = 15;

  public Player(){
    Scene scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/animations.png");
    // SET COMPONENT FRAME SIZE
    setSize(153,150);
    setTag("player");
    setPosition( new Position(10,200) );
    setCollider(new BoxCollider( 62, 126 ));
    getCollider().setPosition(new Position(39,14));
    // DEFINE TIMELINE FRAMES


    //IDLE ANIMATION
    AnimationFrame[] idleAnimationFrames = {
      new AnimationFrame(0,0),
      new AnimationFrame(-153,0),
      new AnimationFrame(-306,0),
      new AnimationFrame(-459,0),
      new AnimationFrame(-612,0),
      new AnimationFrame(-765,0),
      new AnimationFrame(-918,0),
      new AnimationFrame(-1071,0),
      new AnimationFrame(-1224,0),
      new AnimationFrame(-1377,0)
    };
    // CREATE MAIN TIMELINE FOR COMPONENT
    AnimationTimeline idleTimeline = new AnimationTimeline(idleAnimationFrames);
    // SET ANIMATION TIMELINE TO LOOP OVER
    idleTimeline.setLoop(true);
    // ADD MAIN TIMELINE TO ANIMATED COMPONENT
    this.addTimeline("idle", idleTimeline);


    //JUMP ANIMATION
    AnimationFrame[] jumpAnimationFrames = {
      new AnimationFrame(0,-300),
      new AnimationFrame(-153,-300),
      new AnimationFrame(-306,-300),
      new AnimationFrame(-459,-300),
      new AnimationFrame(-612,-300),
      new AnimationFrame(-765,-300),
      new AnimationFrame(-918,-300),
      new AnimationFrame(-1071,-300),
      new AnimationFrame(-1224,-300),
      new AnimationFrame(-1377,-300)
    };
    // CREATE MAIN TIMELINE FOR COMPONENT
    AnimationTimeline jumpTimeline = new AnimationTimeline(jumpAnimationFrames);
    // SET ANIMATION TIMELINE TO LOOP OVER
    jumpTimeline.setLoop(true);
    // ADD MAIN TIMELINE TO ANIMATED COMPONENT
    this.addTimeline("jump", jumpTimeline);

    //RUN ANIMATION
    AnimationFrame[] runAnimationFrames = {
      new AnimationFrame(0,-150),
      new AnimationFrame(-153,-150),
      new AnimationFrame(-306,-150),
      new AnimationFrame(-459,-150),
      new AnimationFrame(-612,-150),
      new AnimationFrame(-765,-150),
      new AnimationFrame(-918,-150),
      new AnimationFrame(-1071,-150)
    };
    // CREATE MAIN TIMELINE FOR COMPONENT
    AnimationTimeline runTimeline = new AnimationTimeline(runAnimationFrames);
    // SET ANIMATION TIMELINE TO LOOP OVER
    runTimeline.setLoop(true);
    // ADD MAIN TIMELINE TO ANIMATED COMPONENT
    this.addTimeline("run", runTimeline);
    // SET THE ACTIVE TIMELINE TO "main"
    this.setCurrentTimeline("idle");
  }



  /**
   * @return the speed
   */
  public int getSpeed() {
  	return speed;
  }



  @Override
  public void move(int x, int y){
    if(x > 1)   x  = 1;
    if(x < -1)  x = -1;
    if(y > 1)   y  = 1;
    if(y < -1)  y = -1;


    super.move(x*getSpeed(),y*getSpeed());
  }

  public boolean getLanded(){
    return this.landed;
  }

  @Override
  public void update(){
    int gravity = 15;
    if(!this.landed){
      if( getcurrentTimelineName() != "jump"){
        this.setCurrentTimeline("jump");
        this.getTimeline().first();
      }
      super.move(0,gravity);
    }else if(getcurrentTimelineName() == "jump"){
      this.setCurrentTimeline("idle");
      this.getTimeline().first();
    } // SIMULATING GRAVITY
    if(this.jumping && this.jumpPower > 0){
      this.jumpPower-=jumpSpeed;
      super.move(0,-jumpSpeed-gravity);
    }else{
      this.jumping = false;
    }
  }


  public boolean collisionIsBeneath(Collision collision){
    Component component = collision.getOther(getId());
    Box boxA = collision.getBoxA();
    Box boxB = collision.getBoxB();;
    return  boxA.getBottom() >= boxB.getTop()  &&
            boxA.getRight()   >= boxB.getLeft()  &&
            boxA.getLeft()    <= boxB.getRight() &&
            boxA.getBottom() - boxB.getTop() < 15;
  }

  public boolean collisionIsAbove(Collision collision){
    Component component = collision.getOther(getId());
    Box boxA = collision.getBoxA();
    Box boxB = collision.getBoxB();;
    return  boxA.getTop() <= boxB.getBottom()   &&
            boxA.getRight()   >= boxB.getLeft()  &&
            boxA.getLeft()    <= boxB.getRight() &&
            boxB.getBottom() - boxA.getTop() < 15;
  }

  public boolean collisionIsRight(Collision collision){
    Component component = collision.getOther(getId());
    Box boxA = collision.getBoxA();
    Box boxB = collision.getBoxB();;
    return  boxA.getRight()  >= boxB.getLeft()   &&
            boxA.getBottom() >= boxB.getTop()  &&
            boxA.getTop()    <= boxB.getBottom() &&
            boxA.getRight()   - boxB.getLeft() < 15;
  }

  public boolean collisionIsLeft(Collision collision){
    Component component = collision.getOther(getId());
    Box boxA = collision.getBoxA();
    Box boxB = collision.getBoxB();;
    return  boxA.getLeft()  <= boxB.getRight()   &&
            boxA.getBottom() >= boxB.getTop()  &&
            boxA.getTop()    <= boxB.getBottom() &&
            boxB.getRight() - boxA.getLeft() < 15;
  }


  @Override
  public void onCollisionStart(Collision collision){
    // Component component = collision.getOther(getId());
    // if( component.getTag().equals("ground") && collisionIsBeneath(collision) ){
    //     this.landed = true;
    //     this.jumpPower = this.maxJumpPower;
    // }
  }

  @Override
  public void onCollision(Collision collision){
    Component component = collision.getOther(getId());
    Box boxA = getCollider().getBox(getBox());
    Box boxB = component.getCollider().getBox(component.getBox());
    if(component.getTag().equals("ground")){

      // DETECT IF COLLISION IS BENEATH
      if( collisionIsBeneath(collision) ){
        setPosition(new Position(
          getPosition().getX(),
          boxB.getTop() - ( getHeight() - (getBox().getBottom() - boxA.getBottom()) )
        ));
        System.out.println("Landed");
        this.landed = true;
        this.jumpPower = this.maxJumpPower;
      }

      // DETECT IF COLLISION IS ABOVE
      if( collisionIsAbove(collision) ){
        stopJump();
        setPosition(new Position(
          getPosition().getX(),
          boxB.getBottom() - ( getBox().getTop() - boxA.getTop())
        ));
      }

      // DETECT IF COLLISION IS ON RIGHT

      if( collisionIsRight(collision) ){
        setPosition(new Position(
          boxB.getLeft() - ( getWidth() - (getBox().getRight() - boxA.getRight()) ),
          getPosition().getY()
        ));
      }

      // DETECT IF COLLISION IS ON LEFT

      if( collisionIsLeft(collision) ){
        setPosition(new Position(
          boxB.getRight() + ( getBox().getLeft() - boxA.getLeft() ),
          getPosition().getY()
        ));
      }


    }
  }

  @Override
  public void onCollisionEnd(Collision collision){
    Component component = collision.getOther(getId());
    System.out.println("Collision end - " + collisionIsBeneath(collision));
    if( component.getTag().equals("ground") && collisionIsBeneath(collision)){
      System.out.println("UnLanded");
      this.landed = false;
    };
  }

  public void startJump(){
    if(this.landed) this.jumping = true;
  }

  public void stopJump(){
    this.jumping = false;
  }

  // RENDER THE BOX COLLIDER AREA, GOOD FOR DEBUG

  // @Override
  // public BufferedImage render(){
  //   BufferedImage colliderImg = new BufferedImage(getCollider().getWidth(),getCollider().getHeight(),BufferedImage.TYPE_INT_RGB);
  //   BufferedImage img = colliderImg;
  //   img = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
  //   Graphics g = img.getGraphics();
  //   g.setColor ( new Color (100, 0, 0 ) );
  //   g.fillRect(0, 0, getWidth(),getHeight());
  //   g.drawImage(colliderImg, getCollider().getPosition().getX(), getCollider().getPosition().getY(), null);
  //   return img;
  // }

}
