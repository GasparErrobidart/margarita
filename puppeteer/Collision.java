package puppeteer;

public class Collision{

  private Component self;
  private Component component;
  private Box boxA;
  private Box boxB;


  public Collision(Component self,Component component){
    this.self = self;
    this.component = component;
    this.boxA = self.getCollider().getBox(self.getBox());
    this.boxB = component.getCollider().getBox(component.getBox());
  }

  public Component getComponent(){
    return this.component;
  };

  public Component getSelf(){
    return this.self;
  };

  public Component getOther(int id){
    return id != self.getId() ? getSelf() : getComponent();
  }

  /**
   * @return the boxA
   */
  public Box getBoxA() {
  	return boxA;
  }

  /**
   * @return the boxB
   */
  public Box getBoxB() {
  	return boxB;
  }

}
