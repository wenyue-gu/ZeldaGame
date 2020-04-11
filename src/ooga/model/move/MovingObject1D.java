package ooga.model.move;

import ooga.model.enums.Direction;
import ooga.model.enums.MovingState;

public abstract class MovingObject1D implements ooga.model.interfaces.movables.Movable1D {

  public static final int DEFAULT_X_SPEED = 5;
  public static final int DEFAULT_X = 0;

  protected double x;
  protected double xSpeed;
  protected MovingState movingState;
  protected Direction movingDirection;

  public MovingObject1D() {
    this(DEFAULT_X);
  }

  public MovingObject1D(double x) {
    this(x, DEFAULT_X_SPEED);
  }

  public MovingObject1D(double x, double xSpeed) {
    this.x = x;
    this.xSpeed = xSpeed;
    movingState = MovingState.IDLE;
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
    MovingState oldState = this.movingState;
    this.movingState = movingState;

    notifyStateChange(oldState, movingState);
  }

  protected abstract void notifyStateChange(MovingState oldState,
      MovingState newState);

  @Override
  public MovingState getState() {
    return movingState;
  }

  @Override
  public void setDirection(Direction direction) {
    this.movingDirection = direction;
  }

  @Override
  public Direction getDirection() {
    return movingDirection;
  }


}
