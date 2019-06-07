package puppeteer;
import java.util.ArrayList;

public interface Collider{

  public ArrayList<Collision> detectCollision(ArrayList<Component> components);
  public void enable();
  public void disable();
  public boolean isEnabled();
  public Position getPosition();
  public int getWidth();
  public int getHeight();

}
