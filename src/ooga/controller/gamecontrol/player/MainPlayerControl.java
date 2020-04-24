package ooga.controller.gamecontrol.player;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ooga.controller.gamecontrol.PlayerControlInterface;
import ooga.game.GameZelda2DSingle;
import ooga.model.interfaces.movables.Movable1D;

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
  public boolean update() {
    myPlayerControl.update();
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
  public void setView(GameZelda2DSingle view) {
    myPlayerControl.setView(view);
  }

  @Override
  public boolean hasWon() {
    return myPlayerControl.hasWon();
  }
}