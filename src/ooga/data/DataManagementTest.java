package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;
import ooga.model.enums.ImageCategory;
import ooga.model.interfaces.gameMap.Cell;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static ooga.data.GamePara.NPC_NUM;

/**
 * Testing for DataManagement.
 * @author Guangyu Feng
 */

public class  DataManagementTest {
    private static DataLoader loader = new DataLoader();
    private static DataStorer storer = new DataStorer();

    public static void main(String[] args) {
//        gameMapLoadingTest();
//        characterLoadingStoringTest();
//        playerLoadingAndStoring();
//        KeyCodeTest();

    }

    /**
     * the following is testing the Game map loading and storing
     */
    @Test
    public void gameMapLoadingTest() {
        new GameObjectConfiguration();
//        LinkedList<Cell> cellLinkedList = new LinkedList<>();
//        for (int i = 0; i < 20; i++) {
//            Cell newCell = new GameCell(1);
//            newCell.setState(1);
//            newCell.setImage(10);
//            cellLinkedList.add(newCell);
//        }
//
//        b.storeSubMap(cellLinkedList, 1, 1);
        Cell testCell = loader.loadCell(0, 0, 0, 1);
        Assert.assertTrue(testCell.isMapCellWalkable());
        System.out.println(testCell.getState());

    }
    /**
     * the following is testing character loading and storing
     *
     */
    @Test
    public void characterLoadingStoringTest() {

        ZeldaCharacter ZC = new ZeldaCharacter(9, 2, 3, 4);
        ZC.setFiringDirection(Direction.E);
        storer.storeCharacter(2, ZC);
        Assert.assertEquals(loader.loadCharacter(2, CharacterProperty.HP), 9);
//        a.loadCharacter(2, CharacterProperty.SCORE);
    }

    /**
     * test player info loading and storing
     */
    public void playerLoadingAndStoring() {
        storer.initializePlayerStatus(1);
        System.out.println(loader.loadGameParam(NPC_NUM));
    }
    @Test
    public void KeyCodeTest() {
        Map<KeyCode, String> keyCodeMap = new HashMap<>();
        keyCodeMap.put(KeyCode.UP, "hello");
        storer.storeKeyCode(keyCodeMap, 1);
        storer.storeKeyCode(keyCodeMap, 2);
        Map<KeyCode, String> keyCodeMap2 = loader.loadKeyCode(1);
        Assert.assertEquals("hello", loader.loadKeyCode(1).get(KeyCode.UP));
    }
    @Test
    public void imageLoadingStoringTest() {
        storer.storeImage("321", 2, ImageCategory.RESOURCE);
        storer.storeImage("123", 2, ImageCategory.RESOURCE);
        String imagePath = loader.loadImagePath(2, ImageCategory.RESOURCE);
        Assert.assertEquals("123", imagePath);
    }



}
