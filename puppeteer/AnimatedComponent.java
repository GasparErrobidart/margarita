package puppeteer;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class AnimatedComponent extends Component{

  private HashMap<String,AnimationTimeline> timelines;
  private String currentTimelineName;
  private AnimationTimeline currentTimeline;

  public AnimatedComponent(){
    this.timelines = new HashMap<String, AnimationTimeline>();
  }

  public void addTimeline(String name, AnimationTimeline timeline){
    this.timelines.put(name,timeline);
  }

  public void removeTimeline(String name){
    this.timelines.remove(name);
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
  public void setcurrentTimeline(String name) {
  	this.currentTimelineName = name;
    this.currentTimeline = this.timelines.get(name);
  }

  public BufferedImage render(){
    AnimationFrame frame = this.currentTimeline.next();
    setFrameOffset(frame.getX(),frame.getY());
    return super.render();
  }

}
