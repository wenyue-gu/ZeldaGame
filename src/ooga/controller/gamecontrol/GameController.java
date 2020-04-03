package ooga.controller.gamecontrol;

import ooga.controller.gamecontrol.player.MainPlayerControl;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.movables.Movable1D;
import ooga.view.game_view.game_state.AbstractGameStateController;
import ooga.view.game_view.game_state.GameStateController;

public class GameController {

  private ModelInterface myModel;
  private MainPlayerControl myMainPlayerController;
  //private MainView myView;
  GameStateController myGameStateController;

//public GameController(ModelInterface model){
  public GameController(ModelInterface myModel){
    this.myModel = myModel;
    myGameStateController = new AbstractGameStateController();
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
    myMainPlayerController.update(); // update back-end
    myGameStateController.update(); // update front-end
  }

  public Scene getScene(){
    return null;
    //return myView.getGameView();
  }

}
