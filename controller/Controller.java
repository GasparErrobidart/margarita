package controller;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import animaper.Scene;

public class Controller {

    private Timer interval;
    private Scene scene;
    private KeyAsigner assigner;

    public Controller(){
      setScene(Scene.getInstance());
      this.assigner = KeyAsigner.getInstance();
      scene.getWindow().addKeyListener(getAssigner());

      interval = new Timer (1000/getScene().getFPS(), new ActionListener () {
         public void actionPerformed(ActionEvent ev) {
            try {
              getAssigner().executeTrueKeys();
            } catch(Exception e) {
              e.printStackTrace();
            }
         }
      });

      interval.start();
    }


    /**
     * @return the assigner
     */
    private KeyAsigner getAssigner() {
    	return assigner;
    }

    public void assign(String key, KeyFunction function,Boolean bool){
      assigner.addControl(key,function,bool);
    }

    private Scene getScene(){
      return scene;
    }

    private void setScene(Scene scene){
      this.scene = scene;
    }

}
