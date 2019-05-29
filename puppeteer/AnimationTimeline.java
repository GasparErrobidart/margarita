package puppeteer;

public class AnimationTimeline{

  private AnimationFrame[] frames;
  private boolean loop;
  private int current;

  public AnimationTimeline(AnimationFrame[] frames){
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
  public AnimationFrame[] getFrames() {
  	return frames;
  }

  /**
   * @param frames the frames to set
   */
  public void setFrames(AnimationFrame[] frames) {
  	this.frames = frames;
  }

  public int getSize(){
    return this.frames.length;
  }

  public AnimationFrame getAnimationFrame(){
    return this.frames[getCurrent()];
  }

  public AnimationFrame next(){
    if(getCurrent() < getSize()-1){
      setCurrent(getCurrent()+1);
    }else if(getLoop()){
      setCurrent(0);
    }
    return getAnimationFrame();
  }

  public AnimationFrame previous(){
    if(getCurrent() > 0){
      setCurrent(getCurrent()-1);
    }else if(getLoop()){
      setCurrent(getSize()-1);
    }
    return getAnimationFrame();
  }

  public AnimationFrame last(){
    setCurrent(getSize()-1);
    return getAnimationFrame();
  }

  public AnimationFrame first(){
    setCurrent(0);
    return getAnimationFrame();
  }

}
