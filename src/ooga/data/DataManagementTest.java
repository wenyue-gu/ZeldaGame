package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;
import ooga.model.interfaces.gameMap.Cell;

import java.util.LinkedList;

/**
 * Testing for DataManagement for sprint 1.
 * @author Guangyu Feng
 */
public class DataManagementTest {
    private static DataLoader a = new DataLoader();
    private static DataStorer b = new DataStorer();

    public static void main(String[] args) {
        gameMapLoadingTest();
//        characterLoadingStoringTest();
    }

    /**
     * the following is testing the Game map loading and storing
     */
    private static void gameMapLoadingTest() {

        LinkedList<Cell> cellLinkedList = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            Cell newCell = new GameCell(1);
            newCell.setState(1);
            newCell.setImage(10);
            cellLinkedList.add(newCell);
        }

        b.storeSubMap(cellLinkedList, 1, 2);
        a.loadCell(1, 1, 0, 1);

    }
    /**
     * the following is testing character loading and storing
     *
     */
    private static void characterLoadingStoringTest() {

        ZeldaCharacter ZC = new ZeldaCharacter(1, 2, 3, 4);
        ZC.setFiringDirection(Direction.EAST);
        b.storeCharacter(2, ZC);
        a.loadCharacter(2, CharacterProperty.ATTACK);
    }



}
