package ooga.controller.player;

import javafx.scene.input.KeyCode;
import ooga.controller.PlayerControllInterface;
import ooga.model.interfaces.movement.Movable1D;

public class MainPlayerControll implements PlayerControlInterface {
  private PlayerControlInterface myPlayerControl;
  private PlayerControlFactory myPlayerControlFactory;

  public MainPlayerControll(){
    myPlayerControlFactory = new PlayerControlFactory();
  }

  public void setControl(String gameType){
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
  public void update(){

  }

}