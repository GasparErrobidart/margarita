package demo.platformer;
import animaper.*;
import puppeteer.*;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Doughnut extends Component {

  private String coinSoundPath = System.getProperty("user.dir")+"/demo/platformer/sounds/coin.wav";
  private File coinSound = new File(coinSoundPath);
  private Clip loadedCoinSound;
  private Scene scene;
  private int floatingDistance;
  private int floatingDirection = 1;


  public Doughnut(){
    this.scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/doughnut.png");
    setSizeFromBitmap();
    setTag("apple");
    setCollider(new BoxCollider( getWidth(),getHeight() ));
    loadedCoinSound = loadSound(coinSound);
  }

  @Override
  public void onCollisionStart(Collision collision){
    Component component = collision.getOther(getId());
    if(component.getTag() == "player"){
      this.playSound(loadedCoinSound);
      scene.remove(this);
    }
  }

  @Override
  public void playSound(Clip clip) {
    if(clip != null){
      clip.setMicrosecondPosition(350000);
      clip.start();
    }
  }
  /**
   * @return the floatingDistance
   */
  public int getFloatingDistance() {
  	return floatingDistance;
  }

  /**
   * @return the floatingDistance
   */
  public void addFloatingDistance(int distance) {
  	this.floatingDistance += distance;
  }

  /**
   * @return the floatingDirection
   */
  public int getFloatingDirection() {
  	return floatingDirection;
  }

  /**
   * @param floatingDirection the floatingDirection to set
   */
  public void setFloatingDirection(int floatingDirection) {
  	this.floatingDirection = floatingDirection;
  }

  @Override
  public void update(){
    if(getFloatingDistance() <= 0){
      setFloatingDirection(1);
    }else if(getFloatingDistance() >= 10){
      setFloatingDirection(-1);
    }
    addFloatingDistance(getFloatingDirection());
    this.move(0,getFloatingDirection());
  }

}
