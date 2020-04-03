package ooga.controller;

import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movement.Movable1D;

public interface PlayerControllInterface extends ZeldaControllInterface {
  void keyInput(KeyCode key);
  void setMyPlayer(Movable1D player);
}
