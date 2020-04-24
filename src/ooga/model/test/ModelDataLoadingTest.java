package ooga.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    Map<Integer, ZeldaPlayer> players = (HashMap<Integer, ZeldaPlayer>) model.getPlayers();
    System.out.println("--------------Loading Players---------------");
    for (ZeldaPlayer p: players.values()) {
      System.out.println("Player id: " + p.getId());
    }
    assertEquals(2, players.size());
    assertEquals(0, players.get(0).getId());
    assertEquals(2, players.get(2).getId());
  }

  @Test
  public void playerScoring() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0));
    model = new Model(loader);

    ZeldaPlayer player = ((HashMap<Integer, ZeldaPlayer>) model.getPlayers()).get(0);
    assertEquals(0, player.getScore());
    player.addScore(50);
    assertEquals(50, player.getScore());
  }

  @Test
  public void levelGoalScore() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0, 2));
    model = new Model(loader);

    Map<Integer, ZeldaPlayer> players = (HashMap<Integer, ZeldaPlayer>) model.getPlayers();
    assertEquals(100, players.get(0).getGoalScore());
    assertEquals(666, players.get(2).getGoalScore());
  }

  @Test
  public void npcLoading() {

  }
}
