package ooga.model.enums;

public enum CharacterState {
  STOP(0),
  RUN(1),
  JUMP_UP(2),
  JUMP_FALL(3),
  MELEE_ATTACK(4),
  RANGE_ATTACK(5),
  DEAD(6),
  ;

  private final int index;
  CharacterState(int index) {
    this.index = index;
  }

  public int getIndex() {
    return this.index;
  }
}
