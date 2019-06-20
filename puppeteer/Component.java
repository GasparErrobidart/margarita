package puppeteer;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.Graphics;
import animaper.Scene;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Component extends Renderer {

  private BufferedImage bitmap;
  private int frameOffsetX;
  private int frameOffsetY;
  private int id = -1;
  private BoxCollider collider;
  private String tag;

  public Component() {
    setFrameOffset(0, 0);
  }

  /**
   * @return the collider
   */
  public BoxCollider getCollider() {
    return collider;
  }

  /**
   * @return the tag
   */
  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public int getBottom() {
    return getPosition().getY() + getHeight();
  }

  public int getRight() {
    return getPosition().getX() + getWidth();
  }

  /**
   * @param collider the collider to set
   */
  public void setCollider(BoxCollider collider) {
    this.collider = collider;
  }

  public void onCollision(Collision collision) {

  }

  public void onCollisionStart(Collision collision) {

  }

  public void onCollisionEnd(Collision collision) {

  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Scene scene) {
    this.id = scene.nextId();
  }

  /**
   * @return the frameOffsetX
   */
  public int getFrameOffsetX() {
    return frameOffsetX;
  }

  /**
   * @return the frameOffsetY
   */
  public int getFrameOffsetY() {
    return frameOffsetY;
  }

  /**
   * @param x the frameOffsetX to set
   */
  public void setFrameOffsetX(int x) {
    this.frameOffsetX = x;
  }

  /**
   * @param y the frameOffsetY to set
   */
  public void setFrameOffsetY(int y) {
    this.frameOffsetY = y;
  }

  /**
   * @param x the frameOffsetY to set
   * @param y the frameOffsetX to set
   */
  public void setFrameOffset(int x, int y) {
    setFrameOffsetX(x);
    setFrameOffsetY(y);
  }

  protected BufferedImage getBitmap() {
    return bitmap;
  }

  public void move(int x, int y, int z) {
    setPosition(new Position(x + getPosition().getX(), y + getPosition().getY(), z + getPosition().getZ()));
  }

  public void move(int x, int y) {
    move(x, y, 0);
  }

  public void setBitmap(BufferedImage image) {
    bitmap = image;
  }

  public void loadBitmap(String location) {
    try {
      BufferedImage image = ImageIO.read(new File(location));
      setBitmap(image);
    } catch (IOException exc) {
      exc.printStackTrace();
    }
  }

  public void setSizeFromBitmap() {
    if (bitmap != null) {
      setWidth(bitmap.getWidth());
      setHeight(bitmap.getHeight());
    }
  }

  public void update() {
    // DO NOTHING
  }

  public BufferedImage render() {
    BufferedImage bitmap = getBitmap();
    BufferedImage frame = new BufferedImage(getWidth(), getHeight(), bitmap.getType());
    Graphics g = frame.getGraphics();
    g.drawImage(bitmap, frameOffsetX, frameOffsetY, null);
    return frame;
  }

  public void playSound(Clip clip) {
    if(clip != null){
      clip.start();      
    }
  }

  public Clip loadSound(File Sound){
    Clip clip;
    try{
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(Sound));      
    }
    catch(Exception e){
      e.printStackTrace();
      clip = null;
    }
    return clip;
  }

  @Override
  public boolean equals(Object obj) {
    Component component = (Component) obj;
    int cId = component.getId();
    boolean isEqual = cId != -1 && cId == this.getId();
    return isEqual;
  }

}
