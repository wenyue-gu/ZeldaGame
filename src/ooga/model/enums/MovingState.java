package ooga.model.enums;

public enum MovingState {
  STOP(0),
  RUN(1),
  JUMP_UP(2),
  JUMP_FALL(3),
  MELEE_ATTACK(4),
  RANGE_ATTACK(5),
  DEAD(6),
  ;

  private final int index;
  MovingState(int index) {
    this.index = index;
  }

  public int getIndex() {
    return this.index;
  }
}
