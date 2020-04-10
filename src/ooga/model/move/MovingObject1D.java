package ooga.model.characters;

import ooga.model.enums.MovingState;

public class Movable1D implements ooga.model.interfaces.movables.Movable1D {

  public static final int DEFAULT_X_SPEED = 5;
  public static final int DEFAULT_X = 0;

  protected double x;
  protected double xSpeed;
  protected MovingState movingState;

  public Movable1D() {
    this(DEFAULT_X);
  }

  public Movable1D(double x) {
    this(x, DEFAULT_X_SPEED);
  }

  public Movable1D(double x, double xSpeed) {
    this.x = x;
    this.xSpeed = xSpeed;
    movingState = MovingState.STOP;
  }

  @Override
  public void setX(double x) {
    this.x = x;
  }

  @Override
  public void moveInX(double deltaX) {
    x += deltaX;
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public void setXSpeed(double xSpeed) {
    this.xSpeed = xSpeed;
  }

  @Override
  public double getXSpeed() {
    return xSpeed;
  }

  // TODO: implement this
  @Override
  public boolean isBusy() {
    return false;
  }

  @Override
  public void setState(MovingState movingState) {
    this.movingState = movingState;
  }

  @Override
  public MovingState getState() {
    return movingState;
  }
}
