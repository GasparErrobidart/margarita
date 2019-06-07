package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyAsigner implements KeyListener{
    
    private Map<String,KeyFunction> map = null;
    private static final KeyAsigner instance = new KeyAsigner();
    
    private KeyAsigner(){
        map = new HashMap<String,KeyFunction>();
    }
    
    public static KeyAsigner getInstance(){
        return instance;
    }
    
    
    public void addControl(String string, KeyFunction function){
        map.put(string,function);
    }
    
    
    

    @Override
    public void keyPressed(KeyEvent ke) {
        
        int keyCode = ke.getKeyCode();
        
        String keyText = ke.getKeyText(keyCode);
        
        KeyFunction function;
        
        if (map.containsKey(keyText)){
            
            function = map.get(keyText);
            
            if (function != null){
                function.function();
            }
        }
    
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}
    @Override
    public void keyReleased(KeyEvent ke) {}
    
}