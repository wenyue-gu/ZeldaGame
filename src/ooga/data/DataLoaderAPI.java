package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.*;
import ooga.model.interfaces.gameMap.Cell;

import java.util.List;
import java.util.Map;

/**
 * The interface for game loader
 */
public interface DataLoaderAPI {
    int loadCurrentPlayerPara(PlayerPara playerPara) throws DataLoadingException;

    int loadPlayerPara(PlayerPara playerPara, int playerID) throws DataLoadingException;

    int loadGameParam(GamePara para);

    List<Direction> loadAvailableDirection(GamePara para);

    void setGameAndPlayer(int GameID, int PlayerID);

    int getGameType();

    Cell loadCell(int row, int col, int subMapID, int level);

    int getNextSubMapID(Direction direction, int current);
    /**
     * load text files from the database. Keyword specifies one piece of data out of a category. Category can be Dialog content
     */
    String loadText(String keyword, String category);

    int loadCharacter(int ID, CharacterProperty property);

    int loadWeapon(int ID, int property);

    int currentLevel();

    Object loadInventoryElement(int ID);

    Map<String, Integer> loadInternalStorage(String category);

    Map<KeyCode, String> loadKeyCode(int playerID) throws DataLoadingException;

    Map<Integer, String> loadKey(int playerID);

    String loadImagePath(int imageID, ImageCategory category);

    Integer loadInteger(String keyword, String category);


    List<ZeldaCharacter> getZeldaCharacters();

    List<PlayerStatus> getPlayerStatus();
}
