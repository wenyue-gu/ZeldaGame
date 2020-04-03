package controller;

import controller.player.MainPlayerControl;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.movement.Movable1D;

public class MainControl{
  //private Model myModel;
  private MainPlayerControl myMainPlayerController;
  //private MainView myView;

  //public MainControl(Model model){
  public MainControl(){
    //myModel = model;
    //myView = new MainView(this, model);
    myMainPlayerController = new MainPlayerControl();
    //setGameType(myModel.getGameType(), myModel.getPlayer());
  }

  public void keyInput(KeyCode code) {
    myMainPlayerController.keyInput(code);
  }

  private void setGameType(String gameType, Movable1D player){
    myMainPlayerController.setControl(gameType);
    //myMainPlayerController.setMyPlayer(myModel.getPlayer());
  }

  public void update(){
    myMainPlayerController.update();
  }

}
