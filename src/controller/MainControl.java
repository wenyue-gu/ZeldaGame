package controller;

import controller.player.MainPlayerControl;
import javafx.scene.input.KeyCode;

public class MainControl{
  private MainPlayerControl myMainPlayerController;

  public MainControl(){
    myMainPlayerController = new MainPlayerControl();
  }

  public void keyInput(KeyCode code) {
    myMainPlayerController.keyInput(code);
  }

  public void setGameType(String gameType){
    myMainPlayerController.setControl(gameType);
  }

  public void update(){
    myMainPlayerController.update();
  }

}
