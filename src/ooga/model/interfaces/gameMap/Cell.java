package ooga.model.interfaces.gameMap;

/**
 * This interface stores the information of a single cell.
 *
 * @author cady
 */
public interface Cell {

  void setState(int state);

  int getState();
}
