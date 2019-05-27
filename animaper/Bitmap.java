package renderer;
import java.awt.image.BufferedImage;

public class Bitmap {

  private RGBA[][] matrix;
  private int width;
  private int height;

  public Bitmap(int _width,int _height){
    width = _width;
    height = _height;
    matrix = new RGBA[width][height];
  }

  public BufferedImage buffer(){
    BufferedImage buff = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    for(int y=0; y< height; y++) {
        for(int x=0; x< width; x++) {
            RGBA pixel = new RGBA(100, 50, 0);
            buff.setRGB(x, y, pixel.getColor().getRGB());
        }
    }
    return buff;
  }


}
