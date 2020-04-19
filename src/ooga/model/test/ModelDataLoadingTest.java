package ooga.model.test;

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
import ooga.model.enums.PlayerPara;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelDataLoadingTest {
  DataLoaderAPI loader;
  DataStorerAPI storer;
  Model model;

  public ModelDataLoadingTest() throws DataLoadingException {
    loader = new DataLoader();
    storer = new DataStorer();
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), 1);
    storer.setPlayerParam(PlayerPara.CURRENT_LEVEL, 1, 1);
    model = new Model(loader);

    System.out.println("--------------Basic game info in the test---------------");
    System.out.println("Game Type: " + GameType.byIndex(loader.getGameType()));
    System.out.println("NPC Number: " + loader.loadGameParam(GamePara.NPC_NUM));
    System.out.println("Player Number: " + loader.loadGameParam(GamePara.PLAYER_NUM));
  }

  @Test
  public void playerLoading() {
    List<ZeldaPlayer> players = (List<ZeldaPlayer>) model.getPlayers();

    assertEquals(loader.loadGameParam(GamePara.PLAYER_NUM), players.size());
    for (ZeldaPlayer p: players) {
      
    }

  }
}
