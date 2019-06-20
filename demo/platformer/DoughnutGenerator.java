package demo.platformer;

import animaper.*;
import puppeteer.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DoughnutGenerator{

  ArrayList<Doughnut> doughnuts = new ArrayList<>();
  private Scene scene = Scene.getInstance();
  private Random rand = new Random();

  public DoughnutGenerator(){
    
    

    

    for(int i = 0; i<3 ; i++){

      Doughnut doughnut = new Doughnut();
      doughnut.setSize(66,54);

      Integer x = rand.nextInt(500)+100-doughnut.getWidth();
      Integer y = rand.nextInt(500)+100-doughnut.getHeight();

      doughnut.setPosition(new Position(x,y));
      doughnuts.add(doughnut);
      ;
    }

 }

 public DoughnutGenerator(int quantity){


  for(int i = 0; i<quantity && i<10 ; i++){
      Doughnut doughnut = new Doughnut();
      doughnut.setSize(66,54);

      Integer x = rand.nextInt(500)+100-doughnut.getWidth();
      Integer y = rand.nextInt(500)+100-doughnut.getHeight();

      doughnut.setPosition(new Position(x,y));
      doughnut.getCollider().setSize(66,54);
      doughnuts.add(doughnut);
  }

}

public void addDoughnutsToScene(){

  for(int i = 0; i < doughnuts.size() ;i++){
    scene.add(doughnuts.get(i));
  }

}

}