package controller;

import controller.gamecontrol.GameController;
import interfaces.view.game_menu.GameMenuView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.Model;
import ooga.model.interfaces.ModelInterface;


public class WindowControl {

  //private MenuView myMenuView;
  private Button myStartButton;
  private Button myExitButton;
  private GameController myGameController;
  private Stage myStage;
  private GameMenuView myMenuView;
  private ModelInterface myModel;

  public WindowControl(Stage currentStage){
    myMenuView = new GameMenuView();
    myStartButton = myMenuView.getNewGameButton();
    myStartButton.setOnAction(e->startGame(currentStage));
    myExitButton = myMenuView.getExitGameButton();
    myExitButton.setOnAction(e->currentStage.close());
  }

  public Button getMyStartButton() {
    return myStartButton;
  }

  public Button getMyExitButton() {
    return myExitButton;
  }

  public Scene getScene(){
    return null;
    //return myMenuView.getMenuView();
  }

  private void startGame(Stage currentStage) {
    myModel = new Model();
    //myGameControl = new GameController(myModel);
    myGameController = new GameController();
    Scene myScene = myGameController.getScene();

    myScene.setOnKeyPressed(e -> myGameController.keyInput(e.getCode()));
    currentStage.setScene(myScene);
    currentStage.show();
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        myGameController.update();
      }
    };
    timer.start();
  }
}
