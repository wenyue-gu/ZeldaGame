package ooga.model.interfaces.movement;

public interface Jumpable{

  void setZSpeed(double zSpeed);

  double getZSpeed(double zSpeed);

  void jump();

  void setToJump();

  void setNotToJump();

  boolean isJumping();
}
