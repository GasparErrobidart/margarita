package demo.platformer;


public class ScoreCounter{

    private int score = 0;
    private static final ScoreCounter instance = new ScoreCounter();

    private ScoreCounter(){

    }

    public static ScoreCounter getInstance(){
        return instance;
    }

    public void addPoints(int points){
        score += points;
    }

}