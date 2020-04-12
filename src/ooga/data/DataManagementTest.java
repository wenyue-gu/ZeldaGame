package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;
import ooga.model.interfaces.gameMap.Cell;

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
        characterLoadingStoringTest();
//        playerLoadingAndStoring();
    }

    /**
     * the following is testing the Game map loading and storing
     */
    private static void gameMapLoadingTest() {

//        LinkedList<Cell> cellLinkedList = new LinkedList<>();
//        for (int i = 0; i < 20; i++) {
//            Cell newCell = new GameCell(1);
//            newCell.setState(1);
//            newCell.setImage(10);
//            cellLinkedList.add(newCell);
//        }
//
//        b.storeSubMap(cellLinkedList, 1, 1);
        Cell testCell = a.loadCell(19, 5, 0, 1);
        System.out.println(testCell.isMapCellWalkable());
        System.out.println(testCell.getState());

    }
    /**
     * the following is testing character loading and storing
     *
     */
    private static void characterLoadingStoringTest() {

        ZeldaCharacter ZC = new ZeldaCharacter(1, 2, 3, 4);
        ZC.setFiringDirection(Direction.E);
        b.storeCharacter(2, ZC);
        a.loadCharacter(2, CharacterProperty.ATTACK);
    }

    /**
     * test player info loading and storing
     */
    private static void playerLoadingAndStoring() {
        b.initializePlayerStatus(1, 1);
        System.out.println(a.loadGameParam(NPC_NUM));
    }



}
