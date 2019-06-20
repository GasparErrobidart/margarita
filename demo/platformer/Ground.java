package demo.platformer;
import animaper.*;
import puppeteer.*;
import java.awt.image.BufferedImage;

public class Ground extends Component {

  public Ground(){
    Scene scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/ground.png");
    // SET COMPONENT FRAME SIZE
    setSize(800,100);
    setTag("ground");
    setPosition(new Position(0,scene.getHeight()-50));
    setCollider(new BoxCollider( getWidth(),getHeight() ));
  }


  // RENDER THE BOX COLLIDER AREA, GOOD FOR DEBUG
  @Override
  public BufferedImage render(){
    BufferedImage colliderImg = new BufferedImage(getCollider().getWidth(),getCollider().getHeight(),BufferedImage.TYPE_INT_RGB);
    BufferedImage img = colliderImg;
    // BufferedImage img = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
    // Graphics g = img.getGraphics();
    // g.setColor ( new Color (100, 0, 0 ) );
    // g.fillRect(0, 0, getWidth(),getHeight());
    // g.drawImage(colliderImg, getCollider().getPosition().getX(), getCollider().getPosition().getY(), null);
    return img;
  }

}
