package renderer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Frame extends Bitmap{

  public Frame(int width, int height){
    super(width,height);
  }

  public JComponent draw(){
    return (JComponent) new JLabel(new ImageIcon(this.buffer()));
  }

  public void setPixels(int x,int y,Bitmap bitmap){

  }

}
