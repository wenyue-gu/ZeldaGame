package ooga;

import java.util.List;
import java.util.Map;

public interface DataStorerAPI {

    /**
     * load text files from the database. Same concern as loadGameIcon.
     * @return
     */
    List<String> StoreText();

    /**
     * update the game's status and string is the name of the status while integer is the value
     * @return
     */
     void updateGameStatus(Map<String, Integer> gameStatus);



    /**
     * update the player's preference and string is the name of the preference while integer is the value
     * @return
     */
     void updatePlayerPreference(Map<String, Integer> playerPreference);


}
