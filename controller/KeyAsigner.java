package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyAsigner implements KeyListener{

    private Map<String,HashMap<String,KeyFunction>> keyFunctionsMap = null; 
    private Map<String,Boolean> boolMap = null;
    private Map<String,Boolean> releasedMap = null;
    
    private static final KeyAsigner instance = new KeyAsigner();

    
    private KeyAsigner(){
        keyFunctionsMap = new HashMap<String,HashMap<String,KeyFunction>>();
        boolMap = new HashMap<String,Boolean>();
        releasedMap = new HashMap<String,Boolean>();
    }
    
    public static KeyAsigner getInstance(){
        return instance;
    }
    
    //true for keypressed, false for keyreleased
    public void addControl(String string, KeyFunction function, Boolean bool){
        HashMap<String,KeyFunction> functionMap = new HashMap<>();
        functionMap.put("KeyPressed",new KeyFunction(){public void function(){}});
        functionMap.put("KeyReleased",new KeyFunction(){public void function(){}});
        if (bool == true){
            functionMap.replace("KeyPressed",function);
        }
        else{
            functionMap.replace("KeyReleased",function);
        }
        keyFunctionsMap.put(string,functionMap);
        boolMap.put(string,false);
        releasedMap.put(string,false);
    }
    
    public void executeTrueKeys(){

        boolMap.forEach((k,v)->{
            if(v == true){
                if (keyFunctionsMap.containsKey(k)){
                    HashMap<String,KeyFunction> keyMap = keyFunctionsMap.get(k);
                    KeyFunction function = keyMap.get("KeyPressed");
                    if (function != null){
                        function.function();
                    }
                }
            }
            else if (v == false){
                if(releasedMap.containsKey(k)){
                    Boolean previousState = releasedMap.get(k);

                    if (previousState == true){
                        if (keyFunctionsMap.containsKey(k)){
                            HashMap<String,KeyFunction> keyMap = keyFunctionsMap.get(k);
                            KeyFunction function = keyMap.get("KeyReleased");
                            if (function != null){
                                function.function();
                            }
                        }
                    }
                }
            }
            
            

        });

        releasedMap.putAll(boolMap);

    }
    
    

    @Override
    public void keyPressed(KeyEvent ke) {
        
        int keyCode = ke.getKeyCode();
        
        String keyText = ke.getKeyText(keyCode);

        if (boolMap.containsKey(keyText)){

            boolMap.replace(keyText,true);
            
        }

    }
    
    @Override
    public void keyReleased(KeyEvent ke){
        
        int keyCode = ke.getKeyCode();
        
        String keyText = ke.getKeyText(keyCode);
        
        
        if (boolMap.containsKey(keyText)){

            boolMap.replace(keyText,false);

        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {}

}
