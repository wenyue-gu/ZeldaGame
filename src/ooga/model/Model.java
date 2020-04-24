package ooga.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.PlayerStatus;
import ooga.game.GameType;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.backend.CharacterType;
import ooga.model.enums.backend.PlayerPara;
import ooga.model.gameElements.Element;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.gameMap.GameMap;

@SuppressWarnings("unchecked")
public class Model implements ModelInterface {

  private DataLoaderAPI dataLoader;
  private GameMap gameMap;
  private Map players;
  private Map npcs;

  public Model(DataLoaderAPI dataLoader) throws DataLoadingException {
    this.dataLoader = dataLoader;
//    gameMap = new GameMapInstance(dataLoader);
    switch (GameType.byIndex(dataLoader.getGameType())) {
      case ZELDA:
        initializeZelda();
        break;
      default:
        throw new DataLoadingException("Game type is not supported in the backend");
    }
  }

  private void initializeZelda() {
    List<ZeldaCharacter> characters = dataLoader.getZeldaCharacters();
    npcs = new HashMap<Integer, ZeldaCharacter>();
    for (ZeldaCharacter c: characters) {
      int rand = new Random().nextInt(CharacterType.values().length - 1) + 1;
      c.setType(CharacterType.byIndex(rand));
      npcs.put(c.getId(), c);
    }

    players= new HashMap<Integer, ZeldaPlayer>();
    List<PlayerStatus> playerStatuses = dataLoader.getCurrentPlayers();
    for (PlayerStatus p : playerStatuses) {
      ZeldaPlayer current = new ZeldaPlayer(
          p.getPlayerParam(PlayerPara.LIFE),
          p.getPlayerID(),
          p.getPlayerParam(PlayerPara.CURRENT_SCORE),
          p.getPlayerParam(PlayerPara.SCORE_GOAL),
          CharacterType.PLAYER);
      players.put(p.getPlayerID(), current);
    }
  }

  @Override
  public void saveGame(String directory) {
  }

  @Override
  public Map getPlayers() {
    return players;
  }

  @Override
  public Map getNPCs() {
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
