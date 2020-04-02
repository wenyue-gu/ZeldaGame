package controller.player;

import controller.PlayerControlInterface;
import javafx.scene.input.KeyCode;

public class MainPlayerControl implements  PlayerControlInterface{
  private PlayerControlInterface myPlayerControl;
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
  public void update(){

  }

}