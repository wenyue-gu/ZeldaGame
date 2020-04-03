package ooga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.controller.gamecontrol.player.MarioPlayerControl;
import ooga.view.game_view.game_state.AbstractGameView;
import ooga.view.game_view.game_state.GameStateView;

public class usercase4 extends Application {

  private MarioPlayerControl myMarioPlayerControl;
  private GameStateView myGameView;

  @Override
  public void start(Stage primaryStage) throws Exception {

    //normally this will be created through WindowControl -> GameController -> MainPlayerControl -> ZeldaPlayerControl
    myMarioPlayerControl = new MarioPlayerControl();

    //normally game view will be created in WindowControl and set scene will happen there as well
    myGameView = new AbstractGameView();
    Scene scene = myGameView.getGameStateView().getScene();
    scene.setOnKeyPressed(e->myMarioPlayerControl.keyInput(e.getCode()));
    primaryStage.setScene(scene);
    primaryStage.show();

    //When user presses key RIGHT
    //MarioPlayerControl.keyInput is called
    //In switch cases, ZeldaPlayerControl calls "right(1)"
    //inside right(1) backEndPlayer.moveInX(1), moving player to the right by 1
    //front end uses listener to detect change and update its view

  }
}
