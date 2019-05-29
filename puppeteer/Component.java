package puppeteer;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

public class Component extends Rendereable{

  private BufferedImage bitmap;
  private int frameOffsetX;
  private int frameOffsetY;

  public Component(){
    setFrameOffset(0,0);
  }

  /**
   * @return the frameOffsetX
   */
  public int getFrameOffsetX() {
  	return frameOffsetX;
  }

  /**
   * @return the frameOffsetY
   */
  public int getFrameOffsetY() {
  	return frameOffsetY;
  }

  /**
   * @param x the frameOffsetX to set
   */
  public void setFrameOffsetX(int x) {
  	this.frameOffsetX = x;
  }

  /**
   * @param y the frameOffsetY to set
   */
  public void setFrameOffsetY(int y) {
  	this.frameOffsetY = y;
  }

  /**
   * @param x the frameOffsetY to set
   * @param y the frameOffsetX to set
   */
  public void setFrameOffset(int x,int y) {
    setFrameOffsetX(x);
  	setFrameOffsetY(y);
  }

  protected BufferedImage getBitmap(){
    return bitmap;
  }

  public void move(int x, int y,int z){
    setPosition(new Position(
      x + getPosition().getX(),
      y + getPosition().getY(),
      z + getPosition().getZ()
    ));
  }

  public void move(int x, int y){
    move(x,y,0);
  }

  public void setBitmap(BufferedImage image){
    bitmap = image;
  }

  public void loadBitmap(String location){
    try{
      BufferedImage image = ImageIO.read(new File(location));
      setBitmap(image);
    }catch(IOException exc){
      exc.printStackTrace();
    }
  }

  public void setSizeFromBitmap(){
    if(bitmap != null){
      setWidth(bitmap.getWidth());
      setHeight(bitmap.getHeight());
    }
  }

  public BufferedImage render(){
    BufferedImage bitmap = getBitmap();
    BufferedImage frame = new BufferedImage(getWidth(),getHeight(),bitmap.getType());
    Graphics g = frame.getGraphics();
    g.drawImage(bitmap, frameOffsetX, frameOffsetY, null);
    return frame;
  }

}
