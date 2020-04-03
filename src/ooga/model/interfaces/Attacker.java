package ooga.model.interfaces;

import ooga.model.enums.Direction;

/**
 * Objects that implement this interface are allowed to attack other objects in different ways.
 * Usually this object should also implement {@link Alive}
 *
 * @author cady
 * @see Alive
 */
public interface Weapon {

  /**
   * Sets weapon this object is holding
   * @param weapon  an int corresponds to the weapon
   */
  void setWeapon(int weapon);

  /**
   * Gets the weapon this object is holding
   * @return  an int corresponds to this weapon
   */
  int getWeapon();

  /**
   * Sets attack this weapon is performing
   * @param attack  an int corresponds to this attack of the current weapon
   */
  void setAttack(int attack);

  /**
   * Gets attack this weapon is performing
   * @return  an int corresponds to current attack of the current weapon
   */
  int getAttack();

  /**
   * Sets
   * @param direction
   */
  void setFiringDirection(Direction direction);

  /**
   * Sets this weapon to fire
   */
  void fire();
}
