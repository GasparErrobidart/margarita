package animaper;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(BufferedImage _image) {
       setImage(_image);
       new JPanel().setPreferredSize(new Dimension(
          _image.getWidth(),
          _image.getHeight()
       ));
    }

    public void setImage(BufferedImage _image){
      image = _image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
