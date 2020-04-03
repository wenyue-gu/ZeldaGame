package ooga.model;

import java.util.List;
import java.util.Map;
import ooga.data.DataLoaderAPI;
import ooga.model.gameElements.Element;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.gameMap.GameMap;

public class Model implements ModelInterface {

  public Model(DataLoaderAPI dataLoader) {

  }

  @Override
  public List<?> getPlayers() {
    return null;
  }

  @Override
  public List<?> getNPCs() {
    return null;
  }

  @Override
  public GameMap getMap() {
    return null;
  }

  @Override
  public Map<Element, Integer> getInventory() {
    return null;
  }
}
