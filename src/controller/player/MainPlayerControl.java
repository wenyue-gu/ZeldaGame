package controller.player;

import controller.PlayerControlInterfaceInterface;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movement.Movable1D;

public class MainPlayerControl implements PlayerControlInterfaceInterface {
  private PlayerControlInterfaceInterface myPlayerControl;
  private PlayerControlFactory myPlayerControlFactory;

  public MainPlayerControl(){
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