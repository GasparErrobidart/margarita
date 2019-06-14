package puppeteer;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class AnimatedComponent extends Component{

  private HashMap<String,AnimationTimeline> timelines;
  private String currentTimelineName;
  private AnimationTimeline currentTimeline;
  private boolean horizontalFlip = false;

  public AnimatedComponent(){
    this.timelines = new HashMap<String, AnimationTimeline>();
  }

  /**
   * @return the currentTimelineName
   */
  public String getcurrentTimelineName() {
  	return currentTimelineName;
  }

  /**
   * @param currentTimeline the currentTimelineName to set
   */
  public void setCurrentTimeline(String name) {
  	this.currentTimelineName = name;
    this.currentTimeline = this.timelines.get(name);
  }


  public AnimationTimeline getTimeline(){
    return this.currentTimeline;
  }

  public void addTimeline(String name, AnimationTimeline timeline){
    this.timelines.put(name,timeline);
  }

  public void removeTimeline(String name){
    this.timelines.remove(name);
  }

  public boolean getHorizontalFlip(){
    return this.horizontalFlip;
  }

  public void setHorizontalFlip(boolean flip){
    this.horizontalFlip = flip;
  }

  public BufferedImage render(){
    AnimationFrame frame = this.currentTimeline.next();
    setFrameOffset(frame.getX(),frame.getY());
    BufferedImage image = super.render();
    if(getHorizontalFlip()){
      // Flip the image horizontally
      AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
      tx.translate(-image.getWidth(null), 0);
      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      image = op.filter(image, null);
    }
    return image;
  }

}
