package ooga.model.enums;

public enum MovingState {
  STOP(0),
  WALK(1),
  RUN(2),
  JUMP(3),
  MELEE_ATTACK(5),
  RANGE_ATTACK(6),
  DEAD(7),
  ;

  private final int index;
  MovingState(int index) {
    this.index = index;
  }

  public int getIndex() {
    return this.index;
  }
}
