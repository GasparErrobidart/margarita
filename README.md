# Margarita

![Margarita Logo](https://cl.ly/e28c85a749e5/WhatsApp%252520Image%2525202019-06-19%252520at%25252017.50.33.jpeg)

An Open Source 2D game engine.


By 

github/GasparErrobidart && github/FrancoDavidMarsilidelGrosso




Introduction
			Let’s Have Some Fun !

Welcome to Margarita 2D game engine ! From creating as many components as you like to be interacting in the game, making a stop in having a native collider for said components to customizing your controllers on the spot in a really easy and intuitive way.
This is our project and we’re happy to share it with you, so let’s get going !





Quick Start
      Just cut to the chase.


You need to add margarita-engine.jar to your dependencies.
Once you’ve done that, there’s four packages that you care about :

Animaper
Puppeteer
Controller
Configurator

Animaper contains everything related to the “Scene”. Actually, it has a class called that way that’s a singleton component that concentrates the window where the game is taking place, the components in said game and the interaction between those, as well as the behaviour of the game, etc, so you’ll need to import it to your project.


Creating The Scene
			I’m not anxious, you’re anxious.

You can get an instance of the scene this way : 

```java
Scene scene = Scene.getInstance();
```

As it’s a singleton , it will always return the same instance, independently of the environment where it’s called from.

Creating Components
			Am I playing God right now?

Now, create as many classes to introduce to the scene as you like, always remembering that those that will be static should extend the class Component, and the ones with animation such as moving, should extend the class Animated Component. (They’re all part of the puppeteer package, so make sure to import it !). Just for the purpose of giving this example, we’ve created some ground components and a player class, like this : 


```java
package demo.platformer;
import animaper.*;
import puppeteer.*;

public class Ground extends Component {

  public Ground(){
    Scene scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/ground.png");
    // SET COMPONENT FRAME SIZE
    setSize(2000,100);
    setTag("ground");
    setPosition(new Position(0, scene.getHeight() - getHeight() ));
    setCollider(new BoxCollider( getWidth(),getHeight() ));
  }

}
```
and like this :

```java
package demo.platformer;
import animaper.*;
import puppeteer.*;


public class Player extends AnimatedComponent {

  private int speed = 10;
  private boolean landed = false;
  private int maxJumpPower = 150;
  private int jumpPower = 150;
  private boolean jumping = false;
  private int jumpSpeed = 15;

  public Player(){
    Scene scene = Scene.getInstance();
    // LOAD SPRITE SHEET
    loadBitmap(System.getProperty("user.dir")+"/demo/platformer/images/animations.png");
    // SET COMPONENT FRAME SIZE
    setSize(153,150);
    setPosition( new Position(10,10) );
    setCollider(new BoxCollider( 62, 126 ));
    getCollider().setPosition(new Position(39,14));
    // DEFINE TIMELINE FRAMES


    //IDLE ANIMATION
    AnimationFrame[] idleAnimationFrames = {
      new AnimationFrame(0,0),
      new AnimationFrame(-153,0),
      new AnimationFrame(-306,0),
      new AnimationFrame(-459,0),
      new AnimationFrame(-612,0),
      new AnimationFrame(-765,0),
      new AnimationFrame(-918,0),
      new AnimationFrame(-1071,0),
      new AnimationFrame(-1224,0),
      new AnimationFrame(-1377,0)
    };
    // CREATE MAIN TIMELINE FOR COMPONENT
    AnimationTimeline idleTimeline = new AnimationTimeline(idleAnimationFrames);
    // SET ANIMATION TIMELINE TO LOOP OVER
    idleTimeline.setLoop(true);
    // ADD MAIN TIMELINE TO ANIMATED COMPONENT
    this.addTimeline("idle", idleTimeline);


    //JUMP ANIMATION
    AnimationFrame[] jumpAnimationFrames = {
      new AnimationFrame(0,-300),
      new AnimationFrame(-153,-300),
      new AnimationFrame(-306,-300),
      new AnimationFrame(-459,-300),
      new AnimationFrame(-612,-300),
      new AnimationFrame(-765,-300),
      new AnimationFrame(-918,-300),
      new AnimationFrame(-1071,-300),
      new AnimationFrame(-1224,-300),
      new AnimationFrame(-1377,-300)
    };
    // CREATE MAIN TIMELINE FOR COMPONENT
    AnimationTimeline jumpTimeline = new AnimationTimeline(jumpAnimationFrames);
    // SET ANIMATION TIMELINE TO LOOP OVER
    jumpTimeline.setLoop(true);
    // ADD MAIN TIMELINE TO ANIMATED COMPONENT
    this.addTimeline("jump", jumpTimeline);

    //RUN ANIMATION
    AnimationFrame[] runAnimationFrames = {
      new AnimationFrame(0,-150),
      new AnimationFrame(-153,-150),
      new AnimationFrame(-306,-150),
      new AnimationFrame(-459,-150),
      new AnimationFrame(-612,-150),
      new AnimationFrame(-765,-150),
      new AnimationFrame(-918,-150),
      new AnimationFrame(-1071,-150)
    };
    // CREATE MAIN TIMELINE FOR COMPONENT
    AnimationTimeline runTimeline = new AnimationTimeline(runAnimationFrames);
    // SET ANIMATION TIMELINE TO LOOP OVER
    runTimeline.setLoop(true);
    // ADD MAIN TIMELINE TO ANIMATED COMPONENT
    this.addTimeline("run", runTimeline);
    // SET THE ACTIVE TIMELINE TO "main"
    this.setCurrentTimeline("idle");
  }

  /**
   * @return the speed
   */
  public int getSpeed() {
  	return speed;
  }

  @Override
  public void move(int x, int y){
    if(x > 1)   x  = 1;
    if(x < -1)  x = -1;
    if(y > 1)   y  = 1;
    if(y < -1)  y = -1;


    super.move(x*getSpeed(),y*getSpeed());
  }

  public boolean getLanded(){
    return this.landed;
  }

  @Override
  public void update(){
    int gravity = 15;
    if(!this.landed){
      if( getcurrentTimelineName() != "jump"){
        this.setCurrentTimeline("jump");
        this.getTimeline().first();
      }
      super.move(0,gravity);
    }else if(getcurrentTimelineName() == "jump"){
      this.setCurrentTimeline("idle");
      this.getTimeline().first();
    } // SIMULATING GRAVITY
    if(this.jumping && this.jumpPower > 0){
      this.jumpPower-=jumpSpeed;
      super.move(0,-jumpSpeed-gravity);
    }else{
      this.jumping = false;
    }
  }

  @Override
  public void onCollisionStart(Collision collision){
    Component component = collision.getOther(getId());
    if(component.getTag() == "ground"){
      this.landed = true;
      this.jumpPower = this.maxJumpPower;
    }
  }

  @Override
  public void onCollisionEnd(Collision collision){
    Component component = collision.getOther(getId());
    if(component.getTag() == "ground"){
      this.landed = false;
    }
  }

  public void startJump(){
    if(this.landed) this.jumping = true;
  }

  public void stopJump(){
    this.jumping = false;
  }

}

```


As you can see you can set sizes, tags, positions and colliders from the component itself, as well as using a timeline to animate the component and give custom responses when the component starts AND finish (yes, they’re separate events) colliding.


ASSIGNING CONTROLS TO KEYS
			No, you can’t assign a control to make her love you.
      
      

Now, all you’ve got to do is import the controller package and like the scene, the controller is as well a singleton, so you’ll be able to assign functions to the same keyboard independently of where you’ve instantiated it .
You can do it like this :

```java
Controller controls = Controller.getInstance();
```

Now, all you need to do is use the .assign() method of the Controller.
This method takes three parameters :
The Key
The Action
A Boolean
The Key is a string type parameter, you can specify if you want the letter “A”, “Z” or “F4” or even “SHIFT” and arrows and numpad keys and… well, you can, if you want, give an action to every key on your keyboard. Just make sure to get the KeyText of the KeyCode right. Every KeyEvent is attached to a KeyCode, and that KeyCode is an int type data that can give you the KeyText, you can google them.

The Action is a lambda function. What is a lambda function? It’s a Functional Interface that has just one must-override method inside that needs you to redefine it’s behaviour on the spot.
This said interface is called “KeyFunction” and is a part of the Controller package and the function that you need to redefine inside of it it's called “function”.

The boolean has the purpose of discriminating when you want that lambda to be executed. If you want it to be executed when the key you’ve specified is pressed, set the boolean parameter to true, and if you want it when released, set it to false.
Overall, it looks like this :


```java
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
```


Setting Up The Configuration
					C’mon man already


Just one little thing I’ve forgot. You can standardize things up with a package called “Configurator”. Just import it to where you have the scene.
The scene works with a variable called FPS, and this is standardized, just for this example, in a file called configuration.json. Because it needs to exist only one configurator per project, it’s a singleton as well, and it needs to be called ALWAYS before the creation of the scene, that will read the FPS data from this object.
You can change the configurator FPS settings by calling the method .setFps() , or getting them with .getFPS() and it won’t be written to the file until you call the method exportToFile().
And remember, handling files and Json Objects may throw an Exception, so you need to specify that your main throws IOExceptions and ParseExceptions, like this :

```java
public static void main (String [] args) throws IOException,ParseException
```

You can do it all like this : 

```java
Configurator config = Configurator.getInstance();
    config.initiateFPSfromJson();
    config.setFPS(60);
```


Make Everything Start
			This is like pressing a giant red button

Now, all you need to do to make it all work it just call , in your main, the scene method called .start() and the magic will begin !

Is anything else i need to know?
These titles are not getting any shorter, aren’t they?

Yes, there’s more things you can do with this engine, like listening to mouse buttons, manage the collider hitbox, even giving your components specific responses when their ID’s or tags meet ! We’re sure your wisdom hungry spirit will learn it all and even be brave enough to modify them and change the functionality to match your will !

