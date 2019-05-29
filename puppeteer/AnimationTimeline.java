package puppeteer;

public class AnimationTimeline{

  private Frames[] frames;

  public AnimationTimeline(){}

  public AnimationFrame(){
    this(0,0);
  }

  /**
   * @return the y
   */
  public int getY() {
  	return y;
  }

  /**
   * @return the x
   */
  public int getX() {
  	return x;
  }

  /**
   * @param y the y to set
   */
  public void setY(int y) {
  	this.y = y;
  }

  /**
   * @param x the x to set
   */
  public void setX(int x) {
  	this.x = x;
  }

}
