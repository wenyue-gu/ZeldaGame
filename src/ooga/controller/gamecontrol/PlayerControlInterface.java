package ooga.controller.gamecontrol;

import ooga.controller.ZeldaControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movables.Movable1D;

import java.util.Map;

public interface PlayerControlInterface {
  void keyInput(KeyCode key);
  void setMyPlayer(Movable1D player);
  void setKeyCodeMap(Map<String, KeyCode> map);
}
