import animaper.*;
import puppeteer.*;
import controller.*;
import demo.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Graphics;

public class Main {

  public static void main (String [] args) {

    Scene scene = Scene.getInstance();

    //Get singleton Controllet
    KeyAsigner asigner = KeyAsigner.getInstance();

    //Get Window where the controller is going to be attached to
    JFrame window = scene.getWindow();


    // Ship ship1 = new Ship();
    // Ship ship2 = new Ship();
    Ship ship3 = new Ship();
    Ship ship4 = new Ship();



    //Declaration of lambdas for each player movement options
    //Controls for ship 3
    KeyFunction p1MoveUp = new KeyFunction(){
      public void function(){
        ship3.move(0,-1);
      }
    };

    KeyFunction p1MoveDown = new KeyFunction(){
      public void function(){
        ship3.move(0,1);
      }
    };

    KeyFunction p1MoveLeft = new KeyFunction(){
      public void function(){
        ship3.move(-1,0);
      }
    };

    KeyFunction p1MoveRight = new KeyFunction(){
      public void function(){
        ship3.move(1,0);
      }
    };

    //Controls for ship 4
    KeyFunction p2MoveUp = new KeyFunction(){
      public void function(){
        ship4.move(0,-1);
      }
    };

    KeyFunction p2MoveDown = new KeyFunction(){
      public void function(){
        ship4.move(0,1);
      }
    };

    KeyFunction p2MoveLeft = new KeyFunction(){
      public void function(){
        ship4.move(-1,0);
      }
    };

    KeyFunction p2MoveRight = new KeyFunction(){
      public void function(){
        ship4.move(1,0);
      }
    };

    //Asignment of controls to controller
    asigner.addControl("A",p1MoveLeft);
    asigner.addControl("D",p1MoveRight);
    asigner.addControl("W",p1MoveUp);
    asigner.addControl("S",p1MoveDown);

    asigner.addControl("J",p2MoveLeft);
    asigner.addControl("L",p2MoveRight);
    asigner.addControl("I",p2MoveUp);
    asigner.addControl("K",p2MoveDown);
    





    // scene.add(ship1);
    // scene.add(ship2);
    scene.add(ship3);
    scene.add(ship4);

    

    window.addKeyListener(asigner);
    scene.start();





    Timer t = new Timer (1000/24, new ActionListener ()
    {
        public void actionPerformed(ActionEvent ev)
        {
          try {

            // TIME LOOP FOR MOVING THE X,Y POSITION OF THE COMPONENT
            // ship2.move(4,4);
            //ship3.move(1,0);
            //ship4.move(0,1);

            asigner.executeTrueKeys();

          }
          catch(Exception e) {
            e.printStackTrace();
          }
         }
    });

    t.start();




  }

}
