package controller;

import controller.gamecontrol.GameController;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MenuControl {

  //private MenuView myMenuView;
  private Button myStartButton;
  private Button myExitButton;
  private GameController myGameController;
  private Stage myStage;

  public MenuControl(Stage currentStage){
    //myMenuView = new MenuView();
    myStartButton = myMenuView.getNewGameButton();
    myStartButton.setOnAction(e->startGame(currentStage, myGameControl));
    myExitButton = myMenuView.getExitGameButton();
    //Scene myScene = myMenuView.getScene();
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
    //myModel = new ModelInterface();
    //myGameControl = new GameController(myModel);
    myGameControl = new GameController();
    Scene myScene = myGameControl.getScene();

    myScene.setOnKeyPressed(e -> myGameControl.keyInput(e.getCode()));
    currentStage.setScene(myScene);
    currentStage.show();
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        myGameControl.update();
      }
    };
    timer.start();
  }
}
