package demo.platformer;
import animaper.*;
import puppeteer.*;
import controller.*;

public class Main {

  public static void main (String [] args) {

    Scene scene = Scene.getInstance();
    Ground ground = new Ground();
    Ground ground2 = new Ground();
    Ground ground3 = new Ground();
    Player player = new Player();

    ground2.setPosition(new Position(400,150));
    ground2.setSize(100,50);
    ground2.getCollider().setSize(100,50);

    ground3.setPosition(new Position(300,350));
    ground3.setSize(50,50);
    ground3.getCollider().setSize(50,100);

    scene.add(ground);
    scene.add(ground2);
    scene.add(ground3);
    scene.add(player);

    Controller controls = new Controller();


    // if(y != 0 && getcurrentTimelineName() != "idle"){
    //   this.setCurrentTimeline("idle");
    //   this.getTimeline().first();
    // }else if( x != 0 && getcurrentTimelineName() != "run"){
    //   this.setCurrentTimeline("run");
    //   this.getTimeline().first();
    // }else if (getcurrentTimelineName() != "idle"){
    //   this.setCurrentTimeline("idle");
    //   this.getTimeline().first();
    // }

    controls.assign("D",new KeyFunction(){
      public void function(){
        player.move(1,0);
        player.setHorizontalFlip(false);
        if( player.getcurrentTimelineName() != "run" && player.getLanded()){
          player.setCurrentTimeline("run");
          player.getTimeline().first();
        }
      }
    },true);

    controls.assign("A",new KeyFunction(){
      public void function(){
        player.move(-1,0);
        player.setHorizontalFlip(true);
        if( player.getcurrentTimelineName() != "run" && player.getLanded() ){
          player.setCurrentTimeline("run");
          player.getTimeline().first();
        }
      }
    },true);

    controls.assign("W",new KeyFunction(){
      public void function(){
        player.startJump();
      }
    },true);

    controls.assign("W",new KeyFunction(){
      public void function(){
        player.stopJump();
      }
    },false);

    scene.start();

  }

}
