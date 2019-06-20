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
import java.util.HashMap;
import configurator.*;

public class Scene{

  private static final int width = 800;
  private static final int height = 600;
  private static final Scene instance = new Scene();
  private static final Screen screen = new Screen(width,height);
  private Configurator config = Configurator.getInstance();
  private boolean paused = false;
  private int lastId = -1;
  private ArrayList<Component> components;
  private int FPS = config.getFPS();
  private HashMap<String,Collision> collisionMap;

  private Scene(){
    components = new ArrayList<Component>();
  }

  /**
   * @return the width
   */
  public static int getWidth() {
  	return width;
  }

  /**
   * @return the height
   */
  public static int getHeight() {
  	return height;
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

  /**
   * @return the fPS
   */
  public int getFPS() {
  	return FPS;
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
    HashMap<String,Collision> updatedCollisionMap = new HashMap<String,Collision>();
    for(Component component : components){
      if(component.getCollider() != null && component.getCollider().isEnabled()){
        ArrayList<Collision> collisions = component.getCollider().detectCollision(component,components);
        for(Collision collision : collisions){
          Component a = collision.getSelf();
          Component b = collision.getComponent();
          // COLLISION ID IS EQUAL TO [Smaller ID + "to" + Bigger ID]
          String collisionID = a.getId() < b.getId() ? a.getId()+"to"+b.getId() : b.getId()+"to"+a.getId() ;
          updatedCollisionMap.put(collisionID,collision);
        }
      }
    }
    emitCollisionEvents(getCollisionMap(),updatedCollisionMap);
    setCollisionMap(updatedCollisionMap);
  }

  private void emitCollisionEvents(HashMap<String,Collision> oldMap , HashMap<String,Collision> newMap){

    for (String id : newMap.keySet()) {
      Collision collision = newMap.get(id);
      Component a = collision.getSelf();
      Component b = collision.getComponent();
      if(oldMap == null || oldMap.get(id) == null){
        a.onCollisionStart(collision);
        b.onCollisionStart(collision);
      }
      a.onCollision(collision);
      b.onCollision(collision);
    }

    if(oldMap != null){
      for (String id : oldMap.keySet()) {
        Collision collision = oldMap.get(id);
        Component a = collision.getSelf();
        Component b = collision.getComponent();
        if( newMap.get(id) == null ){
          a.onCollisionEnd(collision);
          b.onCollisionEnd(collision);
        }
      }
    }

  }

  private HashMap<String,Collision> getCollisionMap(){
    return this.collisionMap;
  }

  private void setCollisionMap(HashMap<String,Collision> updatedCollisionMap){
    this.collisionMap = updatedCollisionMap;
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

  public void remove(Component component){
    this.components.remove(component);
  }


}
