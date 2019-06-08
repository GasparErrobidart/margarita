
public class Controller {

    private Timer interval;
    private Scene scene;
    private KeyAsigner asigner;

    public Controller(){
      setScene(Scene.getInstance());

      interval = new Timer (1000/getScene().getFPS(), new ActionListener () {
         public void actionPerformed(ActionEvent ev) {
            try {
              asigner.executeTrueKeys();
            } catch(Exception e) {
              e.printStackTrace();
            }
         }
      });

      interval.start();
      scene.getWindow().addKeyListener(getAssigner());
    }

    /**
     * @return the asigner
     */
    private KeyAsigner getAsigner() {
    	return asigner;
    }

    public void assign(String key, KeyFunction function){
      asigner.addControl("A",function);
    }

    private Scene getScene(){
      return scene;
    }

    private void setScene(Scene scene){
      this.scene = scene;
    }

}
