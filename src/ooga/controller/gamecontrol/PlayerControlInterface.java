package ooga.controller.gamecontrol;

import ooga.controller.ZeldaControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movables.Movable1D;

public interface PlayerControlInterface {
  void keyInput(KeyCode key);
  void setMyPlayer(Movable1D player);
}
