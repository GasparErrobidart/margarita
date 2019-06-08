package animaper;
import puppeteer.Component;
import puppeteer.Collision;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.Graphics;

public class Scene{

  private static final int width = 600;
  private static final int height = 600;
  private static final Scene instance = new Scene();
  private static final Screen screen = new Screen(width,height);
  private boolean paused = false;
  private int lastId = -1;
  private ArrayList<Component> components;
  private int FPS = 24;

  private Scene(){
    components = new ArrayList<Component>();
  }

  public static Scene getInstance() {
    return instance;
  }

  public static Screen getScreenInstance() {
    return screen;
  }

  public int nextId(){
    return ++lastId;
  }

  public void add(Component component){
    if(component.getId() == -1){
      component.setId(getInstance());
    }
    components.add(component);
  }

  // THIS IS THE MAIN METHOD FOR THIS CLASS
  private void nextFrame(){
    update();
    detectCollision();
    render();
  }

  public boolean isPaused(){
    return paused;
  }

  private void update(){
    components.forEach((Component c) -> c.update());
  }

  public void pause(){
    this.paused = true;
  }

  public void resume(){
    this.paused = false;
  }

  private void detectCollision(){
    for(Component component : components){
      if(component.getCollider() != null && component.getCollider().isEnabled()){
        ArrayList<Collision> collisions = component.getCollider().detectCollision(component,components);
        for(Collision collision : collisions){
          component.onCollision(collision);
        }
      }
    }
  }

  private void render(){
    // TEMPORARY WINDOW FRAME IMAGE FOR COMPOSING
    BufferedImage tmp = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
    Graphics g = tmp.getGraphics();

    components.forEach((Component c) -> g.drawImage(c.render(), c.getPosition().getX(), c.getPosition().getY(), null) );

    screen.setImage(tmp);
    screen.render();
  }

  public JFrame getWindow(){
    return getScreenInstance().getWindow();
  }

  public void start(){
    Timer t = new Timer( 1000/FPS, new ActionListener (){
      public void actionPerformed(ActionEvent ev){
        if(!isPaused()){
          try{
            nextFrame();
          }catch(Exception e) {
            e.printStackTrace();
          }
        }
      }
    });
    t.start();
  }


}
