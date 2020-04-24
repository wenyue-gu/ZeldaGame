package ooga.controller.gamecontrol;

import javafx.scene.input.KeyCode;
import ooga.game.GameZelda2DSingle;
import ooga.model.interfaces.movables.Movable1D;

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
  void setView(GameZelda2DSingle view);

    void setNewKeyMap(Map<Integer, String> map);

  Movable1D getPlayer();

  boolean checkScore(int score);
}
