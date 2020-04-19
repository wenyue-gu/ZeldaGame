package ooga.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.PlayerStatus;
import ooga.game.GameType;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.GamePara;
import ooga.model.enums.PlayerPara;
import ooga.model.gameElements.Element;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.gameMap.GameMap;
import ooga.model.map.GameMapInstance;

@SuppressWarnings("unchecked")
public class Model implements ModelInterface {

  private DataLoaderAPI dataLoader;
  private GameMap gameMap;
  private List players;
  private List npcs;

  public Model(DataLoaderAPI dataLoader) throws DataLoadingException {
    this.dataLoader = dataLoader;
//    gameMap = new GameMapInstance(dataLoader);
    System.out.println(dataLoader.getGameType());
    switch (GameType.byIndex(dataLoader.getGameType())) {
      case MARIO:
        initializeMario();
        break;
      case ZELDA:
        initializeZelda();
        break;
      default:
        throw new DataLoadingException("Game type is not supported in the backend");
    }
  }

  private void initializeMario() {
    //TODO: implement this
  }

  private void initializeZelda() {
    players = new ArrayList<ZeldaPlayer>();
    npcs = dataLoader.getZeldaCharacters();
    List<PlayerStatus> playerStatuses = dataLoader.getPlayerStatus();
    for (PlayerStatus p: playerStatuses) {
      players.add(new ZeldaPlayer(p.getPlayerParam(PlayerPara.LIFE), p.getPlayerID()));
    }
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
