package puppeteer;

public class Box{

  private Position x1;
  private Position x2;
  private Position x3;
  private Position x4;

  private int top;
  private int right;
  private int bottom;
  private int left;

  public Box(int top,int right, int bottom,int left){
    setX1(new Position(0,0));
    setX2(new Position(0,0));
    setX3(new Position(0,0));
    setX4(new Position(0,0));
    setTop(top);
    setRight(right);
    setBottom(bottom);
    setLeft(left);
  }

  /**
   * @param top the top to set
   */
  public void setTop(int top) {
    setX1(new Position(getX1().getX(),top));
    setX2(new Position(getX2().getX(),top));
  	this.top = top;
  }

  /**
   * @param right the right to set
   */
  public void setRight(int right) {
    setX2(new Position(right,getX2().getY()));
    setX4(new Position(right,getX4().getY()));
  	this.right = right;
  }

  /**
   * @param bottom the bottom to set
   */
  public void setBottom(int bottom) {
    setX3(new Position(getX3().getX(),bottom));
    setX4(new Position(getX4().getX(),bottom));
  	this.bottom = bottom;
  }

  /**
   * @param left the left to set
   */
  public void setLeft(int left) {
    setX1(new Position(left,getX1().getY()));
    setX3(new Position(left,getX3().getY()));
  	this.left = left;
  }

  /**
   * @param x1 the x1 to set
   */
  private void setX1(Position x1) {
  	this.x1 = x1;
  }

  /**
   * @param x2 the x2 to set
   */
  private void setX2(Position x2) {
  	this.x2 = x2;
  }

  /**
   * @param x3 the x3 to set
   */
  private void setX3(Position x3) {
  	this.x3 = x3;
  }

  /**
   * @param x4 the x4 to set
   */
  private void setX4(Position x4) {
  	this.x4 = x4;
  }

  /**
   * @return the top
   */
  public int getTop() {
  	return top;
  }

  /**
   * @return the right
   */
  public int getRight() {
  	return right;
  }

  /**
   * @return the bottom
   */
  public int getBottom() {
  	return bottom;
  }

  /**
   * @return the left
   */
  public int getLeft() {
  	return left;
  }

  /**
   * @return the x1
   */
  public Position getX1() {
  	return x1;
  }

  /**
   * @return the x2
   */
  public Position getX2() {
  	return x2;
  }

  /**
   * @return the x3
   */
  public Position getX3() {
  	return x3;
  }

  /**
   * @return the x4
   */
  public Position getX4() {
  	return x4;
  }


}
