package ooga.model.enums.backend;

public enum CharacterType {
  PLAYER(0),
  BIGBOY(1),
  ENGINEERBOT(3),
  LOADSOLDIER(2),
  SHIELD(4),
  TURRET(5),
  WATCHERBOT(6),
  UNKNOWN(7);

  private final int index;
  CharacterType(int index) {
    this.index = index;
  }

  public int getIndex() {
    return this.index;
  }

  public static CharacterType byIndex(int index) {
    for (CharacterType c: CharacterType.values()) {
      if (c.getIndex() == index) {
        return c;
      }
    }
    return null;
  }

}
