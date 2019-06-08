package demo.platformer;
import animaper.*;
import puppeteer.*;

public class Player extends Component {

  private int speed = 10;
  private boolean landed = false;
  private int maxJumpPower = 70;
  private int jumpPower = 70;
  private boolean jumping = false;
  private int jumpSpeed = 15;

  public Player(){
    Scene scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/player.png");
    // SET COMPONENT FRAME SIZE
    setSize(100,100);
    setPosition( new Position(10,10) );
    setCollider(new BoxCollider( getWidth(),getHeight() ));
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

  @Override
  public void update(){
    int gravity = 15;
    if(!this.landed) super.move(0,gravity); // SIMULATING GRAVITY
    if(this.jumping && this.jumpPower > 0){
      this.jumpPower-=jumpSpeed;
      super.move(0,-jumpSpeed-gravity);
    }else{
      this.jumping = false;
    }
  }

  @Override
  public void onCollisionStart(Collision collision){
    Component component = collision.getOther(getId());
    if(component.getTag() == "ground"){
      this.landed = true;
      this.jumpPower = this.maxJumpPower;
    }
  }

  @Override
  public void onCollisionEnd(Collision collision){
    Component component = collision.getOther(getId());
    if(component.getTag() == "ground"){
      this.landed = false;
    }
  }

  public void startJump(){
    if(this.landed) this.jumping = true;
  }

  public void stopJump(){
    this.jumping = false;
  }

}
