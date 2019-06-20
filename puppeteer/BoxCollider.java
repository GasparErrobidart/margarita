package puppeteer;
import java.util.ArrayList;


public class BoxCollider implements Collider{

  private boolean enabled;
  private int width;
  private int height;
  private Position position;

  public BoxCollider(int width, int height){
    setWidth(width);
    setHeight(height);
    setPosition(new Position(0,0));
    enable();
  }

  /**
   * @param position the position to set
   */
  public void setPosition(Position position) {
  	this.position = position;
  }

  /**
   * @return the position
   */
  public Position getPosition() {
  	return position;
  }

  public Box getBox(Box componentBox){
    int top     = componentBox.getTop()  + getPosition().getY();
    int left    = componentBox.getLeft() + getPosition().getX();
    int right   = left + getWidth();
    int bottom  = top + getHeight();
    return new Box(top,right,bottom,left);
  }

  public ArrayList<Collision> detectCollision(Component self, ArrayList<Component> components){
    ArrayList<Collision> collisionList = new ArrayList<Collision>();
    Box aBox = getBox(self.getBox());
    Component a = self;
    // Collider aCol = self.getCollider();
    for( Component b : components){
      Box bBox = b.getCollider().getBox(b.getBox());
      // Collider bCol = b.getCollider();
      if(
        // a.getPosition().getX() != b.getPosition().getX() + bCol.getPosition().getX()
         a.getId() != b.getId() &&
         aBox.getLeft() <= bBox.getRight() &&
         aBox.getRight() >= bBox.getLeft() &&
         aBox.getTop() <= bBox.getBottom() &&
         aBox.getBottom() >= bBox.getTop()
         )
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

  public void setSize(int width, int height){
    setWidth(width);
    setHeight(height);
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
