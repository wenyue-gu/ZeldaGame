package ooga.game.test;

import ooga.data.DataLoader;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.model.enums.GamePara;

public class DataTest {

  public static void main(String[] args) throws DataLoadingException {
    DataLoaderAPI dataLoaderAPI = new DataLoader();
    System.out.println("Game Type: " + dataLoaderAPI.getGameType());
    System.out.println("Game Para, npc num: " + dataLoaderAPI.loadGameParam(GamePara.NPC_NUM));
    System.out.println("Game Para, npc num: " + dataLoaderAPI.loadGameParam(GamePara.NPC_NUM));
    System.out.println("Game Para, npc num: " + dataLoaderAPI.loadGameParam(GamePara.NPC_NUM));
    System.out.println("Game Para, npc num: " + dataLoaderAPI.loadGameParam(GamePara.NPC_NUM));
    System.out.println("Game Para, npc num: " + dataLoaderAPI.loadGameParam(GamePara.NPC_NUM));
  }

}
