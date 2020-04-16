package ooga.data;

public class PlayerStatus {
    private int gameID;
    private int playerID;
    private int score;
    private int level;


    public PlayerStatus(int gameID, int playerID) {
        this.gameID = gameID;
        this.playerID = playerID;
        this.level = 1;

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
}
