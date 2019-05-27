package renderer;
import java.awt.Color;

public class RGB{

  private int r;
  private int g;
  private int b;

  public RGB(int _r,int _g,int _b){
    setRGB(_r,_g,_b);
  }

  public RGB(int _r,int _g){
    this(_r,_g,0);
  }

  public RGB(int _r){
    this(_r,0,0);
  }

  public RGB(){
    this(0,0,0);
  }

  public void setRed(int _r){
    r = _r;
  }

  public void setGreen(int _g){
    g = _g;
  }

  public void setBlue(int _b){
    b = _b;
  }

  public void setRGB(int _r,int _g,int _b){
    setRed(_r);
    setGreen(_g);
    setBlue(_b);
  }

  public int red(){
    return r;
  }

  public int green(){
    return g;
  }

  public int blue(){
    return b;
  }

  public Color getColor(){
    return new Color(red(),green(),blue());
  }

}
