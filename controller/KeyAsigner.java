package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyAsigner implements KeyListener{
    
    private Map<String,KeyFunction> functionMap = null;
    private Map<String,Boolean> boolMap = null;

    private static final KeyAsigner instance = new KeyAsigner();

    
    private KeyAsigner(){
        functionMap = new HashMap<String,KeyFunction>();
        boolMap = new HashMap<String,Boolean>();
    }
    
    public static KeyAsigner getInstance(){
        return instance;
    }
    
    
    public void addControl(String string, KeyFunction function){
        functionMap.put(string,function);
        boolMap.put(string,false);
    }
    
    public void executeTrueKeys(){

        boolMap.forEach((k,v)->{
            if(v == true){
                if (functionMap.containsKey(k)){
                    KeyFunction function = functionMap.get(k);
                    if (function != null){
                        function.function();
                    }
                }
            }
        });

    }
    
    

    @Override
    public void keyPressed(KeyEvent ke) {
        
        int keyCode = ke.getKeyCode();
        
        String keyText = ke.getKeyText(keyCode);
        
        
        if (boolMap.containsKey(keyText)){

            boolMap.put(keyText,true);
            
        }
    
    }
    
    @Override
    public void keyReleased(KeyEvent ke){
        int keyCode = ke.getKeyCode();
        
        String keyText = ke.getKeyText(keyCode);
        
        
        if (boolMap.containsKey(keyText)){

            boolMap.put(keyText,false);
            
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {}
    
    
}