package controller.gamecontrol;

import controller.gamecontrol.player.MainPlayerControl;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.movables.Movable1D;

public class GameController {

  private ModelInterface myModel;
  private MainPlayerControl myMainPlayerController;
  //private MainView myView;

//public GameController(ModelInterface model){
  public GameController(){
    //myModel = model;
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

  public Scene getScene(){
    return null;
    //return myView.getGameView();
  }

}
