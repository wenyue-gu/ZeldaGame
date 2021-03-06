package ooga.model.interfaces.gameMap;

/**
 * This interface stores the information of a single cell.
 *
 * @author cady
 * @author Guangyu
 */
public interface Cell {
  /**
   *
   * @param state the state value of the cell
   */
  void setState(int state);

  /**
   *
   * @return the state value of the cell
   */
  int getState();

  /**
   *
   * @return the index of image
   */
  int getImage();

  /**
   *
   * @param imageIndex index of the image
   */
  void setImage(int imageIndex);

}
