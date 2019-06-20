package demo.platformer;
import animaper.*;
import puppeteer.*;

public class GroundDecoration extends Component {

    private Ground baseGround;

    public GroundDecoration(Ground base){
        baseGround = base;
        Scene scene = Scene.getInstance();
        // LOAD SPRITE SHEET
        loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/grassdecorator.png");
        // SET COMPONENT FRAME SIZE
        setSize(2000,30);
        setTag("baseGround");
        setPosition(new Position(0, baseGround.getBottom() - (baseGround.getHeight() + (baseGround.getHeight()/4))));
        setCollider(new BoxCollider(0,0));
    }

    public void setPosition(){
        setPosition(new Position(baseGround.getRight()-baseGround.getWidth() , baseGround.getBottom() - (baseGround.getHeight() + (baseGround.getHeight()/2))));
    }

    public void setSize(){
        setSize(baseGround.getWidth(),baseGround.getHeight());
    }
}