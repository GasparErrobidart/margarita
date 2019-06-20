package demo.platformer;
import animaper.*;
import puppeteer.*;

public class Background extends Component {

  public Background(){
    Scene scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/background.jpg");
    // SET COMPONENT FRAME SIZE
    setSize(600,600);
    setTag("ground");
    setPosition(new Position(0, scene.getHeight() - getHeight() ));
    setCollider(new BoxCollider(0,0));
  }

}
