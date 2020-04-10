package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movables.Movable1D;

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
  public void keyInput(KeyCode key){
    myPlayerControl.keyInput(key);
  }

  @Override
  public void setMyPlayer(Movable1D player) {
    myPlayerControl.setMyPlayer(player);
  }

  @Override
  public void setKeyCodeMap(Map<String, KeyCode> map) {
    myPlayerControl.setKeyCodeMap( map);
  }

  public void setID(int id){myPlayerControl.setID(id);}

}