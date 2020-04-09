package ooga.data;

import javafx.scene.image.Image;
import ooga.model.interfaces.gameMap.Cell;

import java.util.Map;

/**
 * The interface for game loader
 */
public interface DataLoaderAPI {

     Cell loadCell(int row, int col, int level);

    /**
     * load text files from the database. Keyword specifies one piece of data out of a category. Category can be Dialog content
     *
     */
    String loadText(String keyword, String category);

    int loadCharacter(int ID, int property);

    int loadWeapon(int ID, int property);

    Object loadInventoryElement(int ID);

    Map<String, Integer> loadInternalStorage(String category);

    Image loadImage(int imageID, String category);

    Integer loadInteger(String keyword, String category);

}
