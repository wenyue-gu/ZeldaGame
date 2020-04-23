package ooga.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import ooga.data.DataLoader;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.DataStorer;
import ooga.data.DataStorerAPI;
import ooga.game.GameType;
import ooga.model.Model;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.GamePara;
import org.junit.jupiter.api.Test;

public class ModelDataLoadingTest {
  DataLoaderAPI loader;
  DataStorerAPI storer;
  Model model;

  public ModelDataLoadingTest() throws DataLoadingException {
    loader = new DataLoader();
    storer = new DataStorer();
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(1));
//    storer.setPlayerParam(PlayerPara.CURRENT_LEVEL, 1, 1);

    System.out.println("--------------Basic game info in the test---------------");
    System.out.println("Game Type: " + GameType.byIndex(loader.getGameType()));
    System.out.println("NPC Number: " + loader.loadGameParam(GamePara.NPC_NUM));
    System.out.println("Player Number: " + loader.loadGameParam(GamePara.PLAYER_NUM));
  }

  @Test
  public void playersLoading() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0, 2));
    model = new Model(loader);

    List<ZeldaPlayer> players = (List<ZeldaPlayer>) model.getPlayers();
    System.out.println("--------------Loading Players---------------");
    for (ZeldaPlayer p: players) {
      System.out.println("Player id: " + p.getId());
    }
    assertEquals(2, players.size());
    assertEquals(0, players.get(0).getId());
    assertEquals(2, players.get(1).getId());
  }
}
