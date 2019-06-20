package demo.platformer;
import animaper.*;
import puppeteer.*;

public class Ground extends Component {

  public Ground(){
    Scene scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/grass.jpg");
    // SET COMPONENT FRAME SIZE
    setSize(800,100);
    setTag("ground");
    setPosition(new Position(0,scene.getHeight()-20));
    setCollider(new BoxCollider( getWidth(),getHeight() ));
  }

}
