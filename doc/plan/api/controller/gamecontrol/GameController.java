package ooga.controller.gamecontrol;

import ooga.controller.gamecontrol.player.MainPlayerControl;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.movables.Movable1D;
import ooga.view.game_view.game_state.AbstractGameStateController;
import ooga.view.game_view.game_state.GameStateController;

import java.util.List;

public class GameController {

  private ModelInterface myModel;
  private List<MainPlayerControl> myMainPlayerController;
  //private MainView myView;
  GameStateController myGameStateController;

public GameController(ModelInterface model){
  //public GameController(){
    myModel = model;
    myGameStateController = new AbstractGameStateController();
    setGameType("Zelda");
    //setGameType(myModel.getGameType(), myModel.getPlayer());
  }

  public void keyInput(KeyCode code) {
    for(MainPlayerControl mpc:myMainPlayerController) mpc.keyInput(code);
  }

  private void setGameType(String gameType){
    for(Object player:myModel.getPlayers()) {
      MainPlayerControl curControl = new MainPlayerControl();
      curControl.setControl(gameType);
      curControl.setMyPlayer((Movable1D)player);
      myMainPlayerController.add(curControl);
    }
  }

  public void update(){
    for(MainPlayerControl mpc: myMainPlayerController) mpc.update(); // update back-end
    myGameStateController.update(); // update front-end
  }

  public Scene getScene(){
    return null;
    //return myView.getGameView();
  }

}
