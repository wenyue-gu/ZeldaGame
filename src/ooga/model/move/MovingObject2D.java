package ooga.model.move;

import ooga.model.interfaces.movables.Movable2D;
import ooga.model.move.MovingObject1D;

public class MovingObject2D extends MovingObject1D implements Movable2D {
  public static final int DEFAULT_Y_SPEED = 5;
  public static final int DEFAULT_Y = 0;

  protected double y;
  protected double ySpeed;
  protected double diagonalSpeed;

  protected boolean canMoveDiagonally;

  public MovingObject2D() {
    this(DEFAULT_X, DEFAULT_Y);
  }

  public MovingObject2D(double x, double y) {
    this(x, y, DEFAULT_X_SPEED, DEFAULT_Y_SPEED);
  }

  public MovingObject2D(double x, double y, double xSpeed, double ySpeed) {
    super(x, xSpeed);
    this.y = y;
    this.ySpeed = ySpeed;
    canMoveDiagonally = false;
    diagonalSpeed = 0;
  }

  @Override
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public void moveInY(double deltaY) {
    y += deltaY;
  }

  @Override
  public void setYSpeed(double ySpeed) {
    this.ySpeed = ySpeed;
  }

  @Override
  public double getYSpeed() {
    return ySpeed;
  }

  @Override
  public void setSpeed(double xSpeed, double ySpeed) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  @Override
  public void set2DPos(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void enableDiagonalMove() {
    canMoveDiagonally = true;
  }

  @Override
  public void disableDiagonalMove() {
    canMoveDiagonally = false;
  }

  @Override
  public void setsDiagonalSpeed(double dSpeed) {
    diagonalSpeed = dSpeed;
  }

  @Override
  public double getDiagonalSpeed() {
    return diagonalSpeed;
  }

  @Override
  public boolean isDiagonalMove() {
    return canMoveDiagonally;
  }
}
