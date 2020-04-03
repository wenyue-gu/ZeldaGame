package ooga;

import javafx.scene.image.Image;
import ooga.model.interfaces.Weapon;
import ooga.model.interfaces.gameMap.Cell;

import java.util.Map;

/**
 * The interface for game loader
 */
public interface Data_Loader_API {

    Cell loadCell(int row, int col);

    /**
     * load text files from the database. Keyword specifies one piece of data out of a category. Category can be Dialog content
     *
     */
    String loadText(String keyword, String category);

    Character loadCharacter(int ID);

    Weapon loadWeapon(int ID);

    Map<String, Integer> loadGameStatus();


    Image loadImage(String keyword, String category);

    
    /**
     * Load the player's preference and string is the name of the preference while integer is the value
     * @return
     */
    Map<String, Integer>  loadPlayerPreference();

    Integer loadInteger(String keyword, String category);



}
