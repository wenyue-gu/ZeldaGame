package ooga.controller.gamecontrol;

import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movables.Movable1D;
import ooga.view.game_view.game_state.state2d.GameState2DView;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface PlayerControlInterface{
  void keyInput(KeyCode key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
  void setMyPlayer(Movable1D player);
  void setKeyCodeMap(Map<KeyCode, String> map);
  void setID();
  int getID();
  void keyReleased();
  void updateKey();
  void setView(GameState2DView view);

    void setNewKeyMap(Map<Integer, String> map);

  Movable1D getPlayer();
}
