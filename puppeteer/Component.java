package puppeteer;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Component extends Rendereable{

  private BufferedImage bitmap;

  public Component(){}

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

  protected BufferedImage getBitmap(){
    return bitmap;
  }

  public BufferedImage render(){
    return getBitmap();
  }

}
