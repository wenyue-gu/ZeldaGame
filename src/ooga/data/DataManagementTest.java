package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;
import ooga.model.interfaces.gameMap.Cell;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static ooga.data.GamePara.NPC_NUM;

/**
 * Testing for DataManagement for sprint 1.
 * @author Guangyu Feng
 */

public class DataManagementTest {
    private static DataLoader a = new DataLoader();
    private static DataStorer b = new DataStorer();

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

//        LinkedList<Cell> cellLinkedList = new LinkedList<>();
//        for (int i = 0; i < 20; i++) {
//            Cell newCell = new GameCell(1);
//            newCell.setState(1);
//            newCell.setImage(10);
//            cellLinkedList.add(newCell);
//        }
//
//        b.storeSubMap(cellLinkedList, 1, 1);
        Cell testCell = a.loadCell(0, 0, 0, 1);
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
        b.storeCharacter(2, ZC);
        Assert.assertEquals(a.loadCharacter(2, CharacterProperty.HP), 9);
//        a.loadCharacter(2, CharacterProperty.SCORE);
    }

    /**
     * test player info loading and storing
     */
    private static void playerLoadingAndStoring() {
        b.initializePlayerStatus(1, 1);
        System.out.println(a.loadGameParam(NPC_NUM));
    }
    @Test
    public void KeyCodeTest() {
        Map<KeyCode, String> keyCodeMap = new HashMap<>();
        keyCodeMap.put(KeyCode.UP, "hello");
        b.storeKeyCode(keyCodeMap, 1);
        b.storeKeyCode(keyCodeMap, 2);
        Map<KeyCode, String> keyCodeMap2 = a.loadKeyCode(1);
        Assert.assertEquals("hello", a.loadKeyCode(1).get(KeyCode.UP));
    }



}
