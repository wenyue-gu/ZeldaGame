package controller;

import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movement.Movable1D;

public interface PlayerControlInterface extends ZeldaControlInterface {
  void keyInput(KeyCode key);
  void setMyPlayer(Movable1D player);
}
