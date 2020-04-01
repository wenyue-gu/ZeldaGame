package ooga.model.interfaces;

import ooga.model.enums.Direction;

public interface Weapon {

  void setFiringDirection(Direction direction);
  void fire();
}
