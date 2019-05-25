package renderer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.*;


public class Renderer{

  public static JFrame frame;
  private JLabel current;
  private int counter;

  public Renderer(){
    counter = 0;
  }

  public void bitmap(){
    frame = new JFrame("WINDOW");
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    BufferedImage img1 = new BufferedImage(256,256, BufferedImage.TYPE_INT_RGB);
    for(int i=0; i< 256; i++) {
        for(int j=0; j< 256; j++) {
            Color newColor = new Color(i,i,i);
            img1.setRGB(j,i,newColor.getRGB());
        }
    }
    JLabel imagen1 = new JLabel(new ImageIcon(img1));

    BufferedImage img2 = new BufferedImage(256,256, BufferedImage.TYPE_INT_RGB);
    for(int i=0; i< 256; i++) {
        for(int j=0; j< 256; j++) {
            Color newColor = new Color(j,i,j);
            img2.setRGB(j,i,newColor.getRGB());
        }
    }
    JLabel imagen2 = new JLabel(new ImageIcon(img2));

    current = imagen1;
    frame.add(current);
    Timer t = new Timer (1000/24, new ActionListener ()
    {
        public void actionPerformed(ActionEvent ev)
        {
          try {
            ++counter;
            frame.remove(current);
            current = (counter%2 == 0) ? imagen1 : imagen2;
            frame.add(current);
            frame.pack();
            frame.repaint();

          }
          catch(Exception e) {
            e.printStackTrace();
          }
         }
    });

    t.start();

  }

}
