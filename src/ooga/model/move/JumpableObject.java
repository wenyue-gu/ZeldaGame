package ooga.model.move;

import ooga.model.enums.MovingState;
import ooga.model.interfaces.movables.Jumpable;
import ooga.model.move.MovingObject1D;

public class JumpableObject extends MovingObject1D implements Jumpable {

  public static final int DEFAULT_Z_SPEED = 5;
  protected double zSpeed;

  public JumpableObject() {
    this(DEFAULT_X, DEFAULT_Z_SPEED);
  }

  public JumpableObject(double x, double zSpeed) {
    this(x, DEFAULT_X_SPEED, zSpeed);
  }

  public JumpableObject(double x, double xSpeed, double zSpeed) {
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

  // TODO
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
