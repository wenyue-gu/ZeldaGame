package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.enums.PlayerPara;

import java.util.HashMap;
import java.util.Map;

import static ooga.model.map.GameGridInMap.ID_NOT_DEFINED;

public class PlayerStatus {
    public static int initLevel = 1;
    private int playerID;
    private Map<PlayerPara, Integer> playerParaMap;
    private Map<KeyCode, String> keyCodeMap;
    private Map<Integer, String> keyMap;


    public PlayerStatus(int playerID) {
        this.playerID = playerID;
        playerParaMap = new HashMap<>();
        for (PlayerPara i : PlayerPara.values()) {
            playerParaMap.put(i, ID_NOT_DEFINED);
        }
        setPlayerParam(PlayerPara.CURRENT_LEVEL, initLevel);
        keyCodeMap = new HashMap<>();
    }

    public PlayerStatus(int gameID, int playerID) {
        this(playerID);
        setPlayerParam(PlayerPara.Game, gameID);
    }

    public int getPlayerParam(PlayerPara playerPara) {
        return playerParaMap.get(playerPara);
    }
    public void setPlayerParam(PlayerPara playerParam, int paramValue) {
        playerParaMap.replace(playerParam, paramValue);
    }


    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }


    public void setKeyCodeMap(Map<KeyCode, String> keyCodeMap) {
        this.keyCodeMap = keyCodeMap;
    }

    public Map<KeyCode, String> getKeyCodeMap() {
        return keyCodeMap;
    }

    public Map<PlayerPara, Integer> getPlayerParaMap() {
        return playerParaMap;
    }

    public void setPlayerParaMap(Map<PlayerPara, Integer> playerParaMap) {
        this.playerParaMap = playerParaMap;
    }

    public Map<Integer, String> getKeyMap() {

        return keyMap;
    }

    public void setKeyMap(Map<Integer, String> keyMap){
        this.keyMap = keyMap;
    }
}
