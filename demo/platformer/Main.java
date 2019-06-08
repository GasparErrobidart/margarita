package demo.platformer;
import animaper.*;
import puppeteer.*;
import controller.*;

public class Main {

  public static void main (String [] args) {

    Scene scene = Scene.getInstance();
    Ground ground = new Ground();
    Player player = new Player();
    scene.add(ground);
    scene.add(player);

    Controller controls = new Controller();

    controls.assign("D",new KeyFunction(){
      public void function(){
        player.move(1,0);
      }
    });

    controls.assign("A",new KeyFunction(){
      public void function(){
        player.move(-1,0);
      }
    });

    controls.assign("W",new KeyFunction(){
      public void function(){
        player.startJump();
      }
    });

    scene.start();

  }

}
