package ooga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.controller.gamecontrol.player.ZeldaPlayerControl;
import ooga.view.game_view.game_state.AbstractGameView;
import ooga.view.game_view.game_state.GameStateView;

public class usercase5 extends Application {

  private ZeldaPlayerControl myZeldaPlayerControl;
  private GameStateView myGameView;

  @Override
  public void start(Stage primaryStage) throws Exception {

    //normally this will be created through WindowControl -> GameController -> MainPlayerControl -> ZeldaPlayerControl
    myZeldaPlayerControl = new ZeldaPlayerControl();

    //normally game view will be created in WindowControl and set scene will happen there as well
    myGameView = new AbstractGameView();
    Scene scene = myGameView.getGameStateView().getScene();
    scene.setOnKeyPressed(e->myZeldaPlayerControl.keyInput(e.getCode()));
    primaryStage.setScene(scene);
    primaryStage.show();

    // When the user presses Q to attack,
    // the main game loop in the controller detects the keycode event,
    // it asks the model to update the state of the player
    // the listener in the front-end trigger the update of the front-end to display attack animation
    // front-end then determines whether the attack is successful
    // if the attack is successful, it then calls the controller to update the status of the enemies (life value, ...)
  }
}
