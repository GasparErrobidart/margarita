package puppeteer;
import java.util.ArrayList;


public class BoxCollider implements Collider{

  private boolean enabled;
  private int width;
  private int height;

  public BoxCollider(int width, int height){
    setWidth(width);
    setHeight(height);
    enable();
  }

  public ArrayList<Collision> detectCollision(Component self, ArrayList<Component> components){
    ArrayList<Collision> collisionList = new ArrayList<Collision>();
    Component a = self;
    for( Component b : components){
      if(a.getPosition().getX() < b.getPosition().getX() + b.getWidth() &&
         a.getPosition().getX() + a.getWidth() > b.getPosition().getX() &&
         a.getPosition().getY() < b.getPosition().getY() + b.getHeight() &&
         a.getHeight() + a.getPosition().getY() > b.getPosition().getY())
      {
         collisionList.add(new Collision(a,b));
      }
    };
    return collisionList;
  };

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  /**
   * @param width the width to set
   */
  public void setWidth(int width) {
  	this.width = width;
  }

  /**
   * @param height the height to set
   */
  public void setHeight(int height) {
  	this.height = height;
  }

  public void enable(){
    this.enabled = true;
  };

  public void disable(){
    this.enabled = false;
  };

  public boolean isEnabled(){
    return this.enabled;
  };

}
