package demo.platformer;

import animaper.*;
import puppeteer.*;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.util.Random;

public class Doughnut extends Component {

  private String coinSoundPath = System.getProperty("user.dir")+"/demo/platformer/sounds/coin.wav";
  private File coinSound = new File(coinSoundPath);
  private Clip loadedCoinSound;
  private Scene scene;
  private int floatingDistance = 0;
  private int floatingDirection = 1;
  private Random rand = new Random();
  private int pointsGiven = 2;

  public Doughnut(){
    this.scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/doughnut.png");
    setSizeFromBitmap();
    setTag("apple");
    addFloatingDistance( rand.nextInt(10) );
    setCollider(new BoxCollider( getWidth(),getHeight() ));
    loadedCoinSound = loadSound(coinSound);
  }

  @Override
  public void onCollisionStart(Collision collision){
    Component component = collision.getOther(getId());
    ScoreCounter scoreCounter = ScoreCounter.getInstance();
    if(component.getTag() == "player"){
      this.playSound(loadedCoinSound);
      scene.remove(this);
      scoreCounter.addPoints(pointsGiven);
    }
  }

  @Override
  public void playSound(Clip clip) {
    if(clip != null){
      clip.setMicrosecondPosition(350000);
      clip.start();
    }
  }

  public void setVolume(float volume){
    if(loadedCoinSound !=null ){
        FloatControl gainControl = (FloatControl) loadedCoinSound.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();

        float gain = (range *volume ) + gainControl.getMinimum();

        gainControl.setValue(gain);
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
