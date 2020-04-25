package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.*;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.GamePara;
import ooga.model.enums.backend.PlayerPara;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;

import java.awt.image.BufferedImage;
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

    void setGameAndPlayer(int GameID, List<Integer> PlayersID);

    int getGameType();

    Cell loadCell(int row, int col, int subMapID, int level) throws DataLoadingException;

    int getNextSubMapID(Direction direction, int current);

    GameMapGraph loadMap(int level, int subMapID) throws DataLoadingException;

    BufferedImage loadBufferImage(int ImageID, ImageCategory category) throws DataLoadingException;

    /**
     * load text files from the database. Keyword specifies one piece of data out of a category. Category can be Dialog content
     */
    String loadText(String keyword, String category) throws DataLoadingException;
    @Deprecated
    int loadCharacter(int ID, CharacterProperty property) throws DataLoadingException;
    @Deprecated
    int loadWeapon(int ID, int property) throws DataLoadingException;

    int currentLevel();

    Map<Integer, String> loadKeyCode(int playerID) throws DataLoadingException;

    String loadImagePath(int imageID, ImageCategory category) throws DataLoadingException;


    List<ZeldaCharacter> getZeldaCharacters();

    List<PlayerStatus> getCurrentPlayers();

    Map<String, Animation2D> loadAnimation(AnimationType animationType);
}
