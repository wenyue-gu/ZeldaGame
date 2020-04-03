package ooga.controller;

import ooga.controller.player.MainPlayerControll;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.movement.Movable1D;

public class MainController {
  private ModelInterface myModel;
  private MainPlayerControll myMainPlayerController;
  //private MainView myView;

  //public MainController(ModelInterface model){
  public MainController(){
    //myModel = model;
    //myView = new MainView(this, model);
    myMainPlayerController = new MainPlayerControll();
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
