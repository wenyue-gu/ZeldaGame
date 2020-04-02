package ooga.model.interfaces.map;

/**
 * This interface allows multiple grids
 */
public interface GridInMap extends SingleGrid {
  /**
   * Sets the id of this grid
   * @param id  the id of this grid
   */
  void setID(int id);

  /**
   * Gets the id of this grid
   * @return  the id of this grid
   */
  int getID();


}
