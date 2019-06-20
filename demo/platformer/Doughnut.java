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


  public Doughnut(){
    this.scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/doughnut.png");
    // SET COMPONENT FRAME SIZE
    setSize(66,54);
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

}
