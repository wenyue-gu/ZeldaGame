package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movables.Movable1D;

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


}