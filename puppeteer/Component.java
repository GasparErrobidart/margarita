package puppeteer;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class Component{

  public int x;
  public int y;
  private int width;
  private int height;

  public Component(int _x,int _y,int _w,int _h){
    x = _x;
    y = _x;
    width = _w;
    height = _h;
  }

  public void move(int _x, int _y){
    x+=_x;
    y+=_y;
  }

  public BufferedImage render(){
    BufferedImage img = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    for(int i=0; i< height; i++) {
        for(int j=0; j< width; j++) {
            img.setRGB(j,i,new Color(120,0,0).getRGB());
        }
    }
    return img;
  }

}
