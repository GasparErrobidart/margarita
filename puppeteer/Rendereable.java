package puppeteer;
import java.awt.image.BufferedImage;
import java.awt.Color;

abstract public class Rendereable{

  private Position position;
  private int width;
  private int height;

  public Rendereable(){
    setPosition(new Position(0,0,0));
    setWidth(0);
    setHeight(0);
  }

  /**
   * @return the position
   */
  public Position getPosition() {
  	return position;
  }

  /**
   * @return the width
   */
  public int getWidth() {
  	return width;
  }

  /**
   * @return the height
   */
  public int getHeight() {
  	return height;
  }

  /**
   * @param height the height to set
   */
  public void setHeight(int height) {
  	this.height = height;
  }

  /**
   * @param width the width to set
   */
  public void setWidth(int width) {
  	this.width = width;
  }

  /**
   * @param width the width to set
   * @param height the height to set
   */
  public void setSize(int width, int height) {
    setWidth(width);
  	setHeight(height);
  }

  /**
   * @param position the position to set
   */
  public void setPosition(Position position) {
  	this.position = position;
  }

  public BufferedImage render(){
    return new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
  }

}
