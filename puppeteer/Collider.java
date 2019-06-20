package puppeteer;
import java.util.ArrayList;

public interface Collider{

  public ArrayList<Collision> detectCollision(Component self, ArrayList<Component> components);
  public void enable();
  public void disable();
  public boolean isEnabled();
  public int getWidth();
  public int getHeight();
  public void setSize(int width, int height);
  public Position getPosition();
  public void setPosition(Position position);
  public Box getBox(Box ComponentBox);

}
