package demo.platformer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import animaper.Scene;

public class ThemeSongPlayer {

    private String themeSongPath;
    private File themeSong;
    private Clip loadedThemeSong;

    private static final ThemeSongPlayer instance = new ThemeSongPlayer();

    private ThemeSongPlayer() {
        JFrame window = Scene.getInstance().getWindow();

        window.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                stopSound();
            }
        });
    }

    public static ThemeSongPlayer getInstance() {
        return instance;
    }

    private void setLoop(Clip clip) {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void setUpPlayer(String path) {
        themeSongPath = path;
        themeSong = new File(themeSongPath);
        loadedThemeSong = loadSound(themeSong);
    }

    public void playSound(){
        if (loadedThemeSong != null){
            loadedThemeSong.start();
            setLoop(loadedThemeSong);
        }
    };

    public void stopSound(){
        if (loadedThemeSong != null){
            loadedThemeSong.stop();
        }
    }
    
    public Clip loadSound(File Sound){
        Clip clip;
        try{
          clip = AudioSystem.getClip();
          clip.open(AudioSystem.getAudioInputStream(Sound));
        }
        catch(Exception e){
          e.printStackTrace();
          clip = null;
        }
        return clip;
    }
}
