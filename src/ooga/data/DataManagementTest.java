package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.PlayerPara;
import ooga.model.interfaces.gameMap.Cell;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Testing for DataManagement.
 * @author Guangyu Feng
 */

public class  DataManagementTest {
    private static DataLoader loader;

    static {
        try {
            loader = new DataLoader();
        } catch (DataLoadingException e) {
            e.printStackTrace();
        }
    }

    private static DataStorer storer;

    static {
        try {
            storer = new DataStorer();
        } catch (DataLoadingException e) {
            e.printStackTrace();
        }
    }

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
    public void gameMapLoadingTest() throws DataLoadingException {
        loader.setGameAndPlayer(1,1);
        ExampleDataGenerator.generateTheMapForFirstSprint();

        Cell testCell = loader.loadCell(6, 2, 0, 1);
        Assert.assertTrue(testCell.isMapCellWalkable());
        Assert.assertEquals(testCell.getImage(), 82);
        System.out.println(testCell.getState());

    }
    /**
     * the following is testing character loading and storing
     *
     */
    @Test
    public void characterLoadingStoringTest() {

        ZeldaCharacter ZC = new ZeldaCharacter(9, 2, 3, 4,0,0);
        ZC.setFiringDirection(Direction.E);
        storer.storeCharacter(4, ZC);
        Assert.assertEquals(loader.loadCharacter(4, CharacterProperty.HP), 9);
//        a.loadCharacter(2, CharacterProperty.SCORE);
        loader.getGameObjectConfiguration().storeGameEverything();
    }

    @Test
    public void KeyCodeTest() throws DataLoadingException {
        Map<KeyCode, String> keyCodeMap = new HashMap<>();
        keyCodeMap.put(KeyCode.UP, "hello");
        storer.addPlayer(3);
        storer.addPlayer(2);
        storer.storeKeyCode(keyCodeMap, 3);
        storer.storeKeyCode(keyCodeMap, 2);
        Map<KeyCode, String> keyCodeMap2 = loader.loadKeyCode(3);
        Assert.assertEquals("hello", loader.loadKeyCode(3).get(KeyCode.UP));
        loader.getGameObjectConfiguration().storeGameEverything();
    }
    @Test
    public void imageLoadingStoringTest() {
        storer.storeImage("321", 2, ImageCategory.RESOURCE);
        storer.storeImage("123", 2, ImageCategory.RESOURCE);
        String imagePath = loader.loadImagePath(2, ImageCategory.RESOURCE);
        Assert.assertEquals("123", imagePath);
    }

    /**
     * todo: interger 99 != String 99
     */
    @Test
    public void loadAndStoreParam() throws DataLoadingException {
        storer.addPlayer(3);
        storer.setPlayerParam(PlayerPara.COLOR, 99, 3);
        Assert.assertEquals(99, loader.loadPlayerPara(PlayerPara.COLOR, 3));
        System.out.println("谢谢cady同学帮忙refactor！！");
    }



}
