package puppeteer;

public class Position{

  private int xPos;
  private int yPos;
  private int zPos;

  public Position(int x,int y, int z){
    setX(x);
    setY(y);
    setZ(z);
  }

  public int getX(){
    return xPos;
  }

  public int getY(){
    return yPos;
  }

  public int getZ(){
    return zPos;
  }

  public void setX(int x){
    xPos = x;
  }

  public void setY(int y){
    yPos = y;
  }

  public void setZ(int z){
    zPos = z;
  }


}
