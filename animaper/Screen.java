package animaper;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.Dimension;


public class Screen{

  private JFrame window;
  private ImagePanel current;


  public Screen(int width, int height){
    window = new JFrame("WINDOW");
    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    window.setVisible(true);
    window.setSize(new Dimension(width,height));
  }

  public void render(){
    window.revalidate();
    window.repaint();
  }

  public void clear(){
    if(current != null ) window.remove(current);
  }

  public void setImage(BufferedImage image){
    clear();
    current = new ImagePanel(image);
    window.add(current);
  }

}
