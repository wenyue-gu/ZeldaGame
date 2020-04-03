package ooga.model.interfaces;

import java.util.List;
import java.util.Map;
import ooga.model.gameElements.Element;
import ooga.model.interfaces.gameMap.GameMap;

public interface ModelInterface {
  List<?> getPlayers();

  List<?> getNPCs();

  GameMap getMap();

  Map<Element, Integer> getInventory();

}
