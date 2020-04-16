package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.UnchangableCharacter;
import ooga.model.enums.ImageCategory;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.Inventory;
import ooga.model.interfaces.gameMap.Cell;

import java.util.Collection;
import java.util.Map;

public interface  DataStorerAPI {
    int getGameType();
    void setGame(int GameID);
    /**
     * store text files to the database.
     * @return
     */
    void StoreText(String text, String keyword, String category);

    void storeCharacter(int ID, UnchangableCharacter character);

    void storeWeapons(int ID, WeaponBase weapon);

    void StoreInventory(Inventory inventory);

    void storeKeyCode(Map<KeyCode, String> keyCodeMap, int playerID);


    void storeImage(String image, int ImageID, ImageCategory imageCategory);

    void storeInteger(String keyword, String category, int value);

    void updateParamSetting(Map<String, Integer> playerPreference, int category);

    /**
     *
     * @param map cells in the collection will first fill in the 1st row, then the 2nd row of the screen and so on...
     * @param level the level the game displays.
     */
    void storeSubMap(Collection<Cell> map, int level, int subMapID);
    void addLevel(int levelNumber);

}
