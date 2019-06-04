package animaper;

public class Screen{

  private static final Screen instance = new Screen();

  public  static Screen getInstance() {
    return instance;
  }


  private Screen(){

  }

  public void test(){
    System.out.println("TESTT!");
  }


}
