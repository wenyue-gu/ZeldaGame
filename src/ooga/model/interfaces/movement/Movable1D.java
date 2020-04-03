package ooga.model.interfaces.movement;

/**
 * This interface can be used by objects that move on a 1D line.
 *
 * @author cady
 */
public interface Movable1D {

  /**
   * Sets the x position of this object
   *
   * @param x the x position of this object
   */
  void setX(double x);

  /**
   * Moves this object on the line for a certain distance. Note that this command uses the Cartesian
   * coordinate system. For example, move right for 5 should call moveInX(5)
   *
   * @param deltaX the distance in which the object moves in the x direction
   */
  void moveInX(double deltaX);

  /**
   * Gets the x position of this object
   *
   * @return the x position of this object
   */
  double getX();

  /**
   * Sets the speed this object moves in the x direction
   *
   * @param xSpeed the speed this object moves in the x direction
   */
  void setXSpeed(double xSpeed);

  /**
   * Gets the speed this object moves in the x direction
   *
   * @return the speed this object moves in the x direction
   */
  double getXSpeed();

  /**
   * Gets if the frontend is busy animating the last movement. If yes, backend states will not be
   * changed
   *
   * @return if frontend is animating
   */
  boolean isBusy();
}
