package ooga.model.interfaces.movement;

public interface Movable2D extends Movable1D {

  void setY(double y);

  double getY();

  /**
   * Note that this command uses the Cartesian coordinate system. For example, move up for 5 should
   * call moveInY(5)
   *
   * @param deltaY
   */
  void moveInY(double deltaY);

  void setYSpeed(double ySpeed);

  double getYSpeed();

  void setSpeed(double xSpeed, double ySpeed);

  void set2DPos(double x, double y);

  /**
   * allows the object to move diagonally
   */
  void enableDiagonalMove();

  void disableDiagonalMove();

  boolean isDiagonalMove();

}
