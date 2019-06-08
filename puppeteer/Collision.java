package puppeteer;

public class Collision{

  private Component self;
  private Component component;


  public Collision(Component self,Component component){
    this.self = self;
    this.component = component;
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

}
