package ooga.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ooga.data.DataLoaderAPI;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.gameElements.Element;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.gameMap.GameMap;
import ooga.model.map.GameMapInstance;

public class Model implements ModelInterface {
  private DataLoaderAPI dataLoader;
  private GameMap gameMap;
  private List<ZeldaPlayer> players;
  private List<ZeldaCharacter> npcs;

  // TODO: use reflection API to create players dynamically once the data figures out
  public Model(DataLoaderAPI dataLoader, String gameType) {
    this.dataLoader = dataLoader;
    intialize();
  }

  private void intialize() {
    gameMap = new GameMapInstance(dataLoader);
    players = new ArrayList<ZeldaPlayer>();
    npcs = new ArrayList<>();
    // TODO: change this after data is implemented
    players.add(new ZeldaPlayer(100, 0));
    npcs.add(new ZeldaCharacter(100,0));
  }

  @Override
  public void saveGame(String directory) {
  }

  @Override
  public List<?> getPlayers() {
    return players;
  }

  @Override
  public List<?> getNPCs() {
    return npcs;
  }

  @Override
  public GameMap getMap() {
    return gameMap;
  }

  @Override
  public Map<Element, Integer> getInventory() {
    return null;
  }

  @Override
  public List<?> getGameElements() {
    return null;
  }
}
