package ooga.data;

import javafx.scene.input.KeyCode;

import java.util.Map;

public class  PlayerStatus {
    public static int initLevel = 1;
    private int gameID;
    private int playerID;
    private int score;
    private int level;
    private int life;
    private int loudness;
    private int goal;
    private int difficulty;
    private Map<KeyCode, String> keyCodeMap;
    private Map<Integer, String> keyMap;


    public PlayerStatus(int playerID) {
        this.playerID = playerID;
        this.level = initLevel;
    }

    public PlayerStatus(int gameID, int playerID) {
        this(playerID);
        this.gameID = gameID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setKeyCodeMap(Map<KeyCode, String> keyCodeMap) {
        this.keyCodeMap = keyCodeMap;
    }

    public Map<KeyCode, String> getKeyCodeMap() {
        return keyCodeMap;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLoudness() {
        return loudness;
    }

    public void setLoudness(int loudness) {
        this.loudness = loudness;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Map<Integer, String> getKeyMap() {

        return keyMap;
    }

    public void setKeyMap(Map<Integer, String> keyMap){
        this.keyMap = keyMap;
    }
}
