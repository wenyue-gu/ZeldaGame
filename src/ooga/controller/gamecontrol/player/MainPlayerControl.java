package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movables.Movable1D;
import ooga.view.game_view.game_state.state2d.GameState2DView;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MainPlayerControl implements PlayerControlInterface {
  private PlayerControlInterface myPlayerControl;
  private PlayerControlFactory myPlayerControlFactory;

  public MainPlayerControl(){
    myPlayerControlFactory = new PlayerControlFactory();
  }

  public void setControl(int gameType){
    myPlayerControl= myPlayerControlFactory.selectControl(gameType);
  }

  @Override
  public void keyInput(KeyCode key) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    myPlayerControl.keyInput(key);
  }

  @Override
  public void setMyPlayer(Movable1D player) {
    myPlayerControl.setMyPlayer(player);
  }

  @Override
  public void setKeyCodeMap(Map<KeyCode, String> map) {
    myPlayerControl.setKeyCodeMap(map);
  }

  @Override
  public void setNewKeyMap(Map<Integer, String> map){
    myPlayerControl.setNewKeyMap(map);
  }

  @Override
  public Movable1D getPlayer() {
    return myPlayerControl.getPlayer();
  }

  @Override
  public boolean checkScore(int score) {
    return myPlayerControl.checkScore(score);
  }

  @Override
  public void setID() {
    myPlayerControl.setID();
  }

  @Override
  public int getID() {
    return myPlayerControl.getID();
  }

  @Override
  public void keyReleased() {
    myPlayerControl.keyReleased();
  }

  @Override
  public void updateKey(){
    myPlayerControl.updateKey();
  }

  @Override
  public void setView(GameState2DView view) {
    myPlayerControl.setView(view);
  }
}