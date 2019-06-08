package demo.platformer;
import animaper.*;
import puppeteer.*;
import controller.*;

public class Main {

  public static void main (String [] args) {

    Scene scene = Scene.getInstance();
    Controller controls = new Controller();

    Ground ground = new Ground();

    // controls.assign("W",new KeyFunction(){
    //   public void function(){
    //     ship3.move(0,-1);
    //   }
    // })


    scene.add(ground);
    scene.start();

  }

}
