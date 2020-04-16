package ooga.controller.gamecontrol;

import ooga.controller.ZeldaControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movables.Movable1D;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface PlayerControlInterface {
  void keyInput(KeyCode key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
  void setMyPlayer(Movable1D player);
  void setKeyCodeMap(Map<KeyCode, String> map);
  void setID();
  int getID();
  void keyReleased();
}
