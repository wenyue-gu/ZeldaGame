package ooga.model.characters;

import ooga.model.enums.MovingState;
import ooga.model.interfaces.movables.Jumpable;

public class Player1DJumpable extends Movable1D implements Jumpable {

  public static final int DEFAULT_Z_SPEED = 5;
  protected double zSpeed;

  public Player1DJumpable() {
    this(DEFAULT_X, DEFAULT_Z_SPEED);
  }

  public Player1DJumpable(double x, double zSpeed) {
    this(x, DEFAULT_X_SPEED, zSpeed);
  }

  public Player1DJumpable(double x, double xSpeed, double zSpeed) {
    super(x, xSpeed);
    this.zSpeed = zSpeed;
    movingState = MovingState.STOP;
  }


  @Override
  public void setZSpeed(double zSpeed) {
    this.zSpeed = zSpeed;
  }

  @Override
  public double getZSpeed(double zSpeed) {
    return zSpeed;
  }

  @Override
  public void jump() {
    setToJump();
    notifyAnimation();
  }

  private void notifyAnimation() {
  }

  @Override
  public void setToJump() {
    movingState = MovingState.JUMP;
  }

  @Override
  public void setNotToJump() {
    movingState = MovingState.STOP;
  }
}
