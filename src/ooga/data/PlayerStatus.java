package ooga.data;

import static ooga.model.map.GameGridInMap.ID_NOT_DEFINED;

import java.util.HashMap;
import java.util.Map;
import ooga.model.enums.backend.PlayerPara;

public class PlayerStatus {
    public static int initLevel = 1;
    public static int initLife = 5;

    private int playerID;
    private Map<PlayerPara, Integer> playerParaMap;
    private Map<Integer, String> keyCodeMap;



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


    public void setKeyCodeMap(Map<Integer, String> keyCodeMap) {
        this.keyCodeMap = keyCodeMap;
    }

    public Map<Integer, String> getKeyCodeMap() {
        return keyCodeMap;
    }

    public Map<PlayerPara, Integer> getPlayerParaMap() {
        return playerParaMap;
    }

    public void setPlayerParaMap(Map<PlayerPara, Integer> playerParaMap) {
        this.playerParaMap = playerParaMap;
    }
}
