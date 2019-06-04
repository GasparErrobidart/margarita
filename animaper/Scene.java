package animaper;

public class Scene{

  private static final Scene instance = new Scene();

  public  static Scene getInstance() {
    return instance;
  }


  private Scene(){

  }

  public void test(){
    System.out.println("TESTT!");
  }


}
