package ooga.model.interfaces;

/**
 * Objects that implement this interface have certain HP values. HP can be deducted under certain
 * circumstances (for example, being attacked). This object "dies" if HP is equal to 0. HP cannot be smaller than 0.
 *
 * @author cady
 */
public interface Alive {

  int getId();

  void setId(int id);

  /**
   * Gets the HP of this object
   * @return  the HP of this object
   */
  int getHP();

  /**
   * Sets the HP of this object
   * @param hp  the HP to set
   */
  void setHP(int hp);

  /**
   * Adds HP by a certain amount
   * @param deltaHP the HP to add
   */
  void addHP(int deltaHP);

  /**
   * Subtracts HP by a certain amount
   * @param deltaHP   the HP to subtract
   */
  void subtractHP(int deltaHP);

  /**
   * Gets if this object is still alive
   * @return  if the HP is larger than 0
   */
  boolean isAlive();
}
