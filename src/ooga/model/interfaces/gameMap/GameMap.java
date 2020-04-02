package ooga.model.interfaces.gameMap;

import java.util.List;
import java.util.Map;

/**
 * GameMap is the whole map for the game. It consists many {@link GridInMap} and talk to other parts
 * of the project.
 *
 * @author cady
 * @see GridInMap
 */
public interface GameMap {

  /**
   * Gets the game map representation in a map.
   *
   * @return the game map representation in a map. The key of this map is the id of the {@link
   * GridInMap}; the value is the {@link GridInMap} in a 2D List.
   */
  Map<Integer, List<List<?>>> getGameMap();

  /**
   * Gets the cell state at a specific location
   *
   * @param gridID the grid in which the cell is located at
   * @param row    the row of this cell
   * @param col    the col of this cell
   * @return the state of this cell
   */
  int getGameMap(int gridID, int row, int col);

}
