import renderer.*;
import javax.swing.JFrame;;

public class Main {

  public static JFrame window;

  public static void main (String [] args) {

    Frame frame = new Frame(600,600);
    window = new JFrame("WINDOW");
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    window.add(frame.draw());
    window.pack();
    window.repaint();


  }

}
