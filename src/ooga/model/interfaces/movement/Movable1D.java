package ooga.model.interfaces.movement;

public interface Movable1D {

  void setX(double x);

  /**
   * Note that this command uses the Cartesian coordinate system. For example, move right for 5
   * should call moveInX(5)
   *
   * @param deltaX
   */
  void moveInX(double deltaX);

  double getX();

  void setXSpeed(double xSpeed);

  double getXSpeed();
}
