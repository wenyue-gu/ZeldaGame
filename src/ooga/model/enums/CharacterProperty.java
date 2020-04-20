package ooga.model.enums;

import ooga.model.characters.ZeldaCharacter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static ooga.model.map.GameGridInMap.ID_NOT_DEFINED;

public enum  CharacterProperty {
  X_POS(0),
  Y_POS(1),
  Z_POS(2),
  X_SPEED(3),
  Y_SPEED(5),
  Z_SPEED(6),
  WEAPON(7),
  ATTACK(8),
  HP(9) {
    @Override
    public int getProperty(ZeldaCharacter zeldaCharacter) {
      try {
        Method methodcall = ZeldaCharacter.class.getDeclaredMethod(
                "get" + this.toString().substring(0, 1) + this.toString().substring(1));
        return (int) methodcall.invoke(zeldaCharacter);
      }  catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        e.printStackTrace();
      }
      return ID_NOT_DEFINED;
    }
  },
  SCORE(10),
  ;

  private final int index;
  CharacterProperty(int index) {
    this.index = index;
  }

  public int getIndex() {
    return this.index;
  }
  public int getProperty(ZeldaCharacter zeldaCharacter) {
    try {
      Method methodcall = ZeldaCharacter.class.getDeclaredMethod(
            "get" + this.toString().substring(0, 1) + this.toString().substring(1).toLowerCase());
      return (int) methodcall.invoke(zeldaCharacter);
    }  catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
    }
    return ID_NOT_DEFINED;
  }
}
