package ooga.model;

import ooga.model.interfaces.movement.Jumpable;
import ooga.model.interfaces.movement.Movable1D;

public class MarioCharacter implements Movable1D, Jumpable {

  @Override
  public void setZSpeed(double zSpeed) {

  }

  @Override
  public double getZSpeed(double zSpeed) {
    return 0;
  }

  @Override
  public void jump() {

  }

  @Override
  public void setToJump() {

  }

  @Override
  public void setNotToJump() {

  }

  @Override
  public boolean isJumping() {
    return false;
  }

  @Override
  public void setX(double x) {

  }

  @Override
  public void moveInX(double deltaX) {

  }

  @Override
  public double getX() {
    return 0;
  }

  @Override
  public void setXSpeed(double xSpeed) {

  }

  @Override
  public double getXSpeed() {
    return 0;
  }

}
