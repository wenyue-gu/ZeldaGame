package ooga;

import javafx.scene.image.Image;

import java.util.Map;

/**
 * The interface for game loader
 */
public interface Data_Loader_API {

     int loadCell(int row, int col);

    /**
     * load text files from the database. Keyword specifies one piece of data out of a category. Category can be Dialog content
     *
     */
    String loadText(String keyword, String category);

    int loadCharacter(int ID, int property);

    int loadWeapon(int ID, int property);

    Object loadInventoryElement(int ID);

    Map<String, Integer> loadInternalStorage(String category);

    Image loadImage(String keyword, String category);

    Integer loadInteger(String keyword, String category);

}
