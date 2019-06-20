package puppeteer;

public class Timeline<T>{

  private T[] frames;
  private boolean loop;
  private int current;

  public Timeline(T[] frames){
    setFrames(frames);
    setCurrent(0);
    setLoop(false);
  }

  /**
   * @return the current
   */
  public int getCurrent() {
  	return current;
  }

  /**
   * @param current the current to set
   */
  public void setCurrent(int current) {
  	this.current = current;
  }

  /**
   * @param loop the loop to set
   */
  public void setLoop(boolean loop) {
  	this.loop = loop;
  }

  public boolean getLoop(){
    return this.loop;
  }

  /**
   * @return the frames
   */
  public T[] getFrames() {
  	return frames;
  }

  /**
   * @param frames the frames to set
   */
  public void setFrames(T[] frames) {
  	this.frames = frames;
  }

  public int getSize(){
    return this.frames.length;
  }

  public T getAnimationFrame(){
    return this.frames[getCurrent()];
  }

  public T next(){
    if(getCurrent() < getSize()-1){
      setCurrent(getCurrent()+1);
    }else if(getLoop()){
      setCurrent(0);
    }
    return getAnimationFrame();
  }

  public T previous(){
    if(getCurrent() > 0){
      setCurrent(getCurrent()-1);
    }else if(getLoop()){
      setCurrent(getSize()-1);
    }
    return getAnimationFrame();
  }

  public T last(){
    setCurrent(getSize()-1);
    return getAnimationFrame();
  }

  public T first(){
    setCurrent(0);
    return getAnimationFrame();
  }

}
