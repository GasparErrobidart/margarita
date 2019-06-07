package puppeteer;
import java.awt.image.BufferedImage;

public interface Rendereable{

  public boolean isVisible();
  public void setVisibility(boolean visibility);

  /**
   * @return the position
   */
  public Position getPosition();

  /**
   * @return the width
   */
  public int getWidth();

  /**
   * @return the height
   */
  public int getHeight();


  public BufferedImage render();

}
