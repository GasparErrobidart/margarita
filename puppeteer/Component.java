package puppeteer;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

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

  public void setBitmap(String location){
    try{
      bitmap = ImageIO.read(new File(location));
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
    return bitmap;
  }

}
