package puppeteer;

public interface Collider{

  public boolean detectCollision(ArrayList<Component> components);
  public void enable();
  public void disable();
  public boolean isEnabled();
  public Position getPosition();
  public int getWidth();
  public int getHeight();

}
