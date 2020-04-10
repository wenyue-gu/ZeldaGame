package ooga.data;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.gameMap.Cell;

import java.util.Map;

/**
 * The interface for game loader
 */
public interface DataLoaderAPI {
    void setGame(int GameID);
    int getGameType();
    Cell loadCell(int row, int col, int level);

    /**
     * load text files from the database. Keyword specifies one piece of data out of a category. Category can be Dialog content
     *
     */
    String loadText(String keyword, String category);

    int loadCharacter(int ID, int property);

    int loadWeapon(int ID, int property);

    int currentLevel();

    Object loadInventoryElement(int ID);

    Map<String, Integer> loadInternalStorage(String category);

    Map<String, KeyCode> loadKeyCode(String category);

    Image loadImage(int imageID, String category);

    Integer loadInteger(String keyword, String category);

}
