package ooga.data;

import javafx.scene.image.Image;
import ooga.model.characters.UnchangableCharacter;
import ooga.model.gameElements.Weapon;
import ooga.model.interfaces.Inventory;
import ooga.model.interfaces.gameMap.Cell;

import java.util.Collection;
import java.util.Map;

public interface DataStorerAPI {

    /**
     * store text files to the database.
     * @return
     */
    void StoreText(String text, String keyword, String category);

    void StoreCharacter(int ID, UnchangableCharacter character);

    void storeWeapons(int ID, Weapon weapon);

    void StoreInventory(Inventory inventory);

    void storeImage(Image image, int ImageID, String category);

    void storeInteger(String keyword, String category, int value);

    void updateParamSetting(Map<String, Integer> playerPreference, int category);

    /**
     *
     * @param map cells in the collection will first fill in the 1st row, then the 2nd row of the screen and so on...
     * @param level the level the game displays.
     */
    void storeMap(Collection<Cell> map, int level);
    void addLevel(int levelNumber);

}
