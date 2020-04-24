package ooga.model.enums;

public enum  MovingState {
  IDLE(0),
  WALK(1),
  SPRINT(2),
  JUMP(3),
  DEATH(4),
  ATTACK1(5),
  ATTACK3(7),
  ;

  private final int index;
  MovingState(int index) {
    this.index = index;
  }

  public int getIndex() {
    return this.index;
  }
}
