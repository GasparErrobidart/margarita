package renderer;
import java.awt.Color;

public class RGBA extends RGB{

  private int a;

  public RGBA(int _r,int _g,int _b,int _a){
    super(_r,_g,_b);
    setAlpha(_a);
  }

  public RGBA(int _r,int _g,int _b){
    this(_r,_g,_b,1);
  }

  public RGBA(int _r,int _g){
    this(_r,_g,0,1);
  }

  public RGBA(int _r){
    this(_r,0,0,1);
  }

  public RGBA(){
    this(0,0,0,1);
  }

  public void setAlpha(int _a){
    a = _a;
  }

  public int alpha(){
    return a;
  }

  public void setRGBA(int _r,int _g,int _b,int _a){
    setRGB(_r,_g,_b);
    setAlpha(_a);
  }

  public Color getColor(){
    return new Color(red(),green(),blue(),alpha());
  }

}
