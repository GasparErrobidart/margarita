package demo.platformer;
import animaper.*;
import puppeteer.*;

public class Ground extends Component {

  public Ground(){
    Scene scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/ground.png");
    // SET COMPONENT FRAME SIZE
    setSize(2000,100);
    setPosition(new Position(0, scene.getHeight() - getHeight() ));
    setCollider(new BoxCollider( getWidth(),getHeight() ));
  }

}
