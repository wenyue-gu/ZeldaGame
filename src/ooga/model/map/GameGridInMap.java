package ooga.model.map;

import java.util.Map;
import ooga.data.DataLoaderAPI;
import ooga.model.enums.Direction;
import ooga.model.interfaces.gameMap.GridInMap;

public class GameGridInMap extends GameSingleGrid implements GridInMap {

  public static final int ID_NOT_DEFINED = 666;

  protected int id;
  protected Map<Direction, Integer> surroundingMaps;

  public GameGridInMap(DataLoaderAPI loader, int id) {
    super(loader);
    this.id = id;
    initializeSurrounding();
  }

  private void initializeSurrounding() {
    for (Direction d: Direction.values()) {
      int neighbor = loader.getNextSubMaID(d, id);
      if (neighbor != ID_NOT_DEFINED) {
        surroundingMaps.put(d, neighbor);
      }
    }
  }

  @Override
  public void setID(int id) {
    this.id = id;
  }

  @Override
  public int getID() {
    return id;
  }

  // TODO: implement use row and col in (next * 5) iteration
  @Override
  public int getGridIDOn(int row, int col, Direction direction) {
    return surroundingMaps.get(direction);
  }

  @Override
  public void setGridIDOn(int row, int col, Direction direction, int gridID) {
    surroundingMaps.put(direction, gridID);
  }

  @Override
  public void loadGrid(int level) {
    loadGrid(this.id, level);
  }
}
