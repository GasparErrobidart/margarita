package demo.platformer;
import animaper.*;
import puppeteer.*;
import controller.*;
import configurator.*;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.util.Random;


public class Main {

  public static void main (String [] args) throws IOException,ParseException{

    Configurator config = Configurator.getInstance();
    config.initiateFPSfromJson();
    config.setFPS(24);
    Random rand = new Random();
    Scene scene = Scene.getInstance();

    Ground ground = new Ground();
    // GroundDecoration groundDec = new GroundDecoration(ground);
    Ground ground2 = new Ground();
    // GroundDecoration groundDec2 = new GroundDecoration(ground2);
    Ground ground3 = new Ground();
    Ground ground4 = new Ground();
    Ground ground5 = new Ground();
    // GroundDecoration groundDec3 = new GroundDecoration(ground3);
    Background background = new Background();
    Player player = new Player();

    String themeSongPath = System.getProperty("user.dir")+"/demo/platformer/sounds/themeSong.wav";
    ThemeSongPlayer themePlayer = ThemeSongPlayer.getInstance();

    DoughnutGenerator doughGen = new DoughnutGenerator();

    ground2.setPosition(new Position(400,150));
    ground2.setSize(100,50);

    ground3.setPosition(new Position(300,400));
    ground3.setSize(50,100);


    ground4.setPosition(new Position(500,450));
    ground4.setSize(50,100);

    ground5.setPosition(new Position(50,475));
    ground5.setSize(100,50);



    scene.add(background);
    scene.add(ground);
    // scene.add(groundDec);
    scene.add(ground2);
    // scene.add(groundDec2);
    scene.add(ground3);
    scene.add(ground4);
    scene.add(ground5);
    // scene.add(groundDec3);
    doughGen.addDoughnutsToScene();
    scene.add(player);

    themePlayer.setUpPlayer(themeSongPath);
    themePlayer.setVolume(0.7f);
    Controller controls = Controller.getInstance();

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

    controls.assign("D",new KeyFunction(){
      public void function(){
        if( player.getcurrentTimelineName() == "run" && player.getLanded()){
          player.setCurrentTimeline("idle");
          player.getTimeline().first();
        }
      }
    },false);

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

    controls.assign("A",new KeyFunction(){
      public void function(){
        if( player.getcurrentTimelineName() == "run" && player.getLanded()){
          player.setCurrentTimeline("idle");
          player.getTimeline().first();
        }
      }
    },false);

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

    themePlayer.playSound();
    scene.start();

  }

}
